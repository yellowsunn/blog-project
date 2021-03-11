package com.yellowsunn.springblog.service.impl;

import com.querydsl.core.Tuple;
import com.yellowsunn.springblog.domain.dto.ArticleDto;
import com.yellowsunn.springblog.domain.dto.AsideArticlesDto;
import com.yellowsunn.springblog.domain.entity.Article;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.domain.entity.Image;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CategoryRepository;
import com.yellowsunn.springblog.repository.ImageRepository;
import com.yellowsunn.springblog.service.ArticleService;
import com.yellowsunn.springblog.service.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yellowsunn.springblog.domain.entity.QArticle.article;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final Common common;

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;

    @Transactional
    @Override
    public ResponseEntity<Long> createArticle(ArticleDto articleDto, MultipartFile thumbnailFile, List<MultipartFile> imageFiles) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof UsernamePasswordAuthenticationToken)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Optional<Category> categoryOptional = categoryRepository.findById(articleDto.getCategoryId());
        if (categoryOptional.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Image thumbnail = null;
        try {
            thumbnail = uploadThumbnail(thumbnailFile);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Article article = Article.builder()
                .writer(auth.getName())
                .category(categoryOptional.get())
                .thumbnail(thumbnail)
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .build();

        Article saveArticle = articleRepository.save(article);
        HttpStatus status = uploadImage(saveArticle, imageFiles);
        if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new IllegalStateException("failed to create a article due to image upload error");
        }

        return new ResponseEntity<>(saveArticle.getId(), status);
    }

    @Transactional
    @Override
    public HttpStatus updateArticle(ArticleDto articleDto, MultipartFile thumbnailFile, List<MultipartFile> imageFiles) {
        Optional<Article> articleOptional = articleRepository.findById(articleDto.getId());
        if (articleOptional.isEmpty()) return HttpStatus.BAD_REQUEST;

        Optional<Category> categoryOptional = categoryRepository.findById(articleDto.getCategoryId());
        if (categoryOptional.isEmpty()) return HttpStatus.BAD_REQUEST;

        Article article = articleOptional.get();

        Image thumbnail = null;
        if (thumbnailFile != null) {
            try {
                thumbnail = uploadThumbnail(thumbnailFile);
                if (article.getThumbnail() != null) { // 기존 섬네일이 있으면 파일 삭제
                    common.removeImageFile(article.getThumbnail().getName());
                    imageRepository.delete(article.getThumbnail());
                }
            } catch (IllegalStateException e) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        article.changeCategory(categoryOptional.get());
        if (thumbnail != null) article.changeThumbnail(thumbnail);
        article.changeTitle(articleDto.getTitle());
        article.changeContent(common.removeServerUrlContent(articleDto.getContent()));

        HttpStatus status = uploadImage(article, imageFiles);
        if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new IllegalStateException("failed to create a article due to image upload error");
        }
        return status;
    }

    @Transactional
    @Override
    public ArticleDto findArticle(Long articleId, String sessionId) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isEmpty()) return null;

        Article article = articleOptional.get();
        article.updateHit(); // 조회수 증가

        ArticleDto.ArticleDtoBuilder builder = ArticleDto.builder()
                .categoryId(article.getCategory().getId())
                .category(article.getCategory().getName())
                .id(article.getId())
                .thumbnail(article.getThumbnail() != null ? common.getServerUrlImage() + article.getThumbnail().getName() : null)
                .writer(article.getWriter())
                .title(article.getTitle())
                .content(common.addServerUrlContent(article.getContent()))
                .like(article.getLike())
                .isAlreadyLike(sessionId != null && sessionId.equals(article.getLikeId()))
                .date(article.getDate());

        // 부모 카테고리
        Category parentCategory = article.getCategory().getParentCategory();
        if (parentCategory != null) {
            builder.parentCategory(parentCategory.getName());
        }

        // 페이지
        // - 게시글 Id -> 특정 카테고리내의 사이즈 1인 페이지의 번호로 변환
        long idx = articleRepository.findIdxByCategoryAndId(article.getCategory(), articleId);
        PageRequest pageRequest = PageRequest.of((int) idx, 1);
        Page<Long> idPage = articleRepository.findIdByCategory(article.getCategory(), pageRequest);

        builder.totalElements(idPage.getTotalElements())
                .totalPages(idPage.getTotalPages())
                .pageNumber(idPage.getNumber())
                .isFirst(idPage.isFirst())
                .isLast(idPage.isLast())
                .hasNext(idPage.hasNext())
                .hasPrevious(idPage.hasPrevious());

        // 이전 게시글
        if (idPage.hasPrevious()) {
            PageRequest previous = pageRequest.previous();
            Page<Article> prevPage = articleRepository.findByCategory(article.getCategory(), previous);
            Article prevArticle = prevPage.getContent().get(0);
            builder.before(ArticleDto.builder().id(prevArticle.getId()).title(prevArticle.getTitle()).build());
        }

        // 다음 게시글
        if (idPage.hasNext()) {
            Pageable next = pageRequest.next();
            Page<Article> nextPage = articleRepository.findByCategory(article.getCategory(), next);
            Article nextArticle = nextPage.getContent().get(0);
            builder.after(ArticleDto.builder().id(nextArticle.getId()).title(nextArticle.getTitle()).build());
        }

        return builder.build();
    }

    @Transactional
    @Override
    public HttpStatus deleteArticle(Long articleId) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isEmpty()) return HttpStatus.BAD_REQUEST;
        Article article = articleOptional.get();

        if (article.getThumbnail() != null) {
            boolean isDeleted = common.removeImageFile(article.getThumbnail().getName());
            if (!isDeleted) return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if (article.getImages() != null) {
            for (Image image : article.getImages()) {
                boolean isDelete = common.removeImageFile(image.getName());
                if (!isDelete) return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        articleRepository.delete(article);
        return HttpStatus.OK;
    }

    @Override
    public Long findArticleIdByPage(Long categoryId, int pageNumber) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) return null;

        // 특정 카테고리내의 사이즈 1인  페이지 번호 -> 게시글 Id로 변환
        PageRequest pageRequest = PageRequest.of(pageNumber, 1);
        Page<Long> idPage = articleRepository.findIdByCategory(categoryOptional.get(), pageRequest);

        List<Long> content = idPage.getContent();
        return !content.isEmpty() ? content.get(0) : null;
    }

    @Override
    public AsideArticlesDto findAsideArticles() {
        List<Tuple> recentTuples = articleRepository.findVerySimpleArticles(false);
        List<Tuple> popularTuples = articleRepository.findVerySimpleArticles(true);

        List<ArticleDto> recent = new ArrayList<>();
        for (Tuple recentTuple : recentTuples) {
            recent.add(changeVerySimple(recentTuple));
        }
        List<ArticleDto> popular = new ArrayList<>();
        for (Tuple popularTuple : popularTuples) {
            popular.add(changeVerySimple(popularTuple));
        }

        return AsideArticlesDto.builder()
                .recentArticles(recent)
                .popularArticles(popular)
                .build();
    }

    @Transactional
    @Override
    public HttpStatus updateLike(Long articleId, String sessionId) {
        if (sessionId == null) return HttpStatus.UNAUTHORIZED;

        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isEmpty()) return HttpStatus.NOT_FOUND;

        Article article = articleOptional.get();

        // 이미 같은 세션아이디로 LIKE를 누른 경우
        if (sessionId.equals(article.getLikeId())) {
            article.rollBackLike();
        } else {
            article.updateLike(sessionId);
        }
        return HttpStatus.OK;
    }

    @Override
    public ArticleDto changeSimple(CategoryRepository categoryRepository, Tuple tuple) {
        String thumbnail = tuple.get(article.thumbnail.name);

        ArticleDto.ArticleDtoBuilder builder = ArticleDto.builder()
                .id(tuple.get(article.id))
                .title(tuple.get(article.title))
                .summary(common.getSummary(tuple.get(article.content)))
                .commentCount(tuple.get(6, Long.class))
                .thumbnail(thumbnail != null ? common.getServerUrlImage() + thumbnail : null)
                .simpleDate(tuple.get(article.date));

        Long id = tuple.get(article.category.id);
        if (id != null) {
            categoryRepository.findById(id).ifPresent(c ->
                    builder.categoryId(c.getId())
                            .category(c.getName())
                            .parentCategory(c.getParentCategory() != null ? c.getParentCategory().getName() : null)
            );
        }

        return builder.build();
    }

    public ArticleDto changeVerySimple(Tuple tuple) {
        String thumbnail = tuple.get(article.thumbnail.name);
        return ArticleDto.builder()
                .id(tuple.get(article.id))
                .title(tuple.get(article.title))
                .simpleDate(tuple.get(article.date))
                .thumbnail(thumbnail != null ? common.getServerUrlImage() + thumbnail : null)
                .build();
    }

    private Image uploadThumbnail(MultipartFile thumbnailFile) {
        if (thumbnailFile == null) return null;

        Image thumbnailImage = Image.builder()
                .name(thumbnailFile.getOriginalFilename())
                .build();
        Image saveImage = imageRepository.save(thumbnailImage);

        boolean isUpload = common.uploadImageFile(thumbnailFile);
        if (!isUpload) {
            throw new IllegalStateException();
        }

        return saveImage;
    }

    private HttpStatus uploadImage(Article article, List<MultipartFile> imageFiles) {
        if (imageFiles != null) {
            for (MultipartFile imageFile : imageFiles) {
                Image image = Image.builder()
                        .name(imageFile.getOriginalFilename())
                        .article(article)
                        .build();
                imageRepository.save(image);

                boolean isUpload = common.uploadImageFile(imageFile);
                if (!isUpload) return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return HttpStatus.OK;
    }
}
