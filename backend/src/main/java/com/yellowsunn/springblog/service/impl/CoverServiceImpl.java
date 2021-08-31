package com.yellowsunn.springblog.service.impl;

import com.querydsl.core.Tuple;
import com.yellowsunn.springblog.domain.dto.*;
import com.yellowsunn.springblog.domain.entity.Category;
import com.yellowsunn.springblog.domain.entity.Cover;
import com.yellowsunn.springblog.domain.entity.Image;
import com.yellowsunn.springblog.repository.ArticleRepository;
import com.yellowsunn.springblog.repository.CategoryRepository;
import com.yellowsunn.springblog.repository.CoverRepository;
import com.yellowsunn.springblog.repository.ImageRepository;
import com.yellowsunn.springblog.service.ArticleService;
import com.yellowsunn.springblog.service.Common;
import com.yellowsunn.springblog.service.CoverService;
import com.yellowsunn.springblog.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yellowsunn.springblog.domain.entity.QCover.cover;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CoverServiceImpl implements CoverService {

    private final Common common;

    private final ArticleService articleService;
    private final ImageService imageService;

    private final CategoryRepository categoryRepository;
    private final CoverRepository coverRepository;
    private final ArticleRepository articleRepository;
    private final ImageRepository imageRepository;

    @Value("${imagePath}")
    private String imgPath;

    @Transactional
    @Override
    public HttpStatus updateHeader(HeaderDto headerDto) {
        List<Cover> coverList = coverRepository.findAll();
        if (coverList.isEmpty()) return HttpStatus.INTERNAL_SERVER_ERROR;
        Cover cover = coverList.get(0);

        cover.changeTitle(headerDto.getTitle());
        cover.changeSlogunTitle(headerDto.getSlogunTitle());
        cover.changeSlogunText(headerDto.getSlogunText());

        return HttpStatus.OK;
    }

    @Override
    public HeaderDto findHeader() {
        Optional<Tuple> tupleOptional = coverRepository.findHeader();
        if (tupleOptional.isEmpty()) return null;

        Tuple tuple = tupleOptional.get();
        return HeaderDto.builder()
                .title(tuple.get(cover.title))
                .slogunTitle(tuple.get(cover.slogunTitle))
                .slogunText(common.replaceNewLine(tuple.get(cover.slogunText)))
                .build();
    }

    @Override
    public MainDto findMainInfo() {

        Optional<Tuple> tupleOptional = coverRepository.findMain();
        if (tupleOptional.isEmpty()) return null;

        Tuple tuple = tupleOptional.get();
        Category coverArticleCategory = tuple.get(cover.coverArticleCategory);
        Category coverCategory = tuple.get(cover.coverCategory);

        MainDto.MainDtoBuilder builder = MainDto.builder();

        // 커버 게시글
        List<Tuple> simpleArticles = articleRepository.findSimpleArticles(coverArticleCategory, 1);
        if (!simpleArticles.isEmpty()) {
            tuple = simpleArticles.get(0);
            ArticleDto articleDto = articleService.changeSimple(categoryRepository, tuple);
            builder.coverArticle(articleDto);
        }

        // 카테고리 목록
        simpleArticles = articleRepository.findSimpleArticles(coverCategory, 3);
        List<ArticleDto> articles = new ArrayList<>();
        for (Tuple t : simpleArticles) {
            ArticleDto articleDto = articleService.changeSimple(categoryRepository, t);
            articles.add(articleDto);
        }

        String parentCategory = null;
        // 부모 카테고리 조회
        if (coverCategory != null) {
            parentCategory = coverCategory.getParentCategory() != null ? coverCategory.getParentCategory().getName() : null;
        }

        CategoryDto categoryDto = CategoryDto.builder()
                .id(coverCategory != null ? coverCategory.getId() : null)
                .category(coverCategory != null ? coverCategory.getName() : null)
                .parentCategory(parentCategory)
                .articles(articles)
                .build();

        return builder.coverCategory(categoryDto).build();
    }

    @Transactional
    @Override
    public HttpStatus updateProfile(ProfileDto profileDto, MultipartFile profileFile) {
        List<Cover> coverList = coverRepository.findAll();
        if (coverList.isEmpty()) return HttpStatus.INTERNAL_SERVER_ERROR;
        Cover cover = coverList.get(0);

        Image profile = null;
        if (profileFile != null) {
            profile = imageService.uploadImage(profileFile);
            if (cover.getProfile() != null) { // 기존 프로필이 있으면 파일 삭제
                common.removeImageFile(cover.getProfile().getName());
                imageRepository.delete(cover.getProfile());
            }
        }

        if (profile != null) cover.changeProfile(profile);
        cover.changeProfileText(profileDto.getProfileText());

        return HttpStatus.OK;
    }

    @Override
    public ProfileDto findProfile() {
        Optional<Tuple> tupleOptional = coverRepository.findProfile();
        if (tupleOptional.isEmpty()) return null;

        Tuple tuple = tupleOptional.get();
        String profileName = tuple.get(cover.profile.name);
        return ProfileDto.builder()
                .profileImage(profileName != null ? imgPath + profileName : null)
                .profileText(tuple.get(cover.profileText))
                .build();
    }

    @Override
    public CoverCategoryIdDto findCoverCategoryId() {
        Optional<Tuple> tupleOptional = coverRepository.findCoverCategory();
        if (tupleOptional.isEmpty()) return null;

        Tuple tuple = tupleOptional.get();
        return CoverCategoryIdDto.builder()
                .articleCategoryId(tuple.get(cover.coverArticleCategory.id))
                .categoryId(tuple.get(cover.coverCategory.id))
                .build();
    }

    @Transactional
    @Override
    public HttpStatus updateCoverCategoryId(CoverCategoryIdDto categoryIdDto) {
        List<Cover> coverList = coverRepository.findAll();
        if (coverList.isEmpty()) return HttpStatus.INTERNAL_SERVER_ERROR;
        Cover cover = coverList.get(0);

        Category articleCategory = null;
        Category category = null;
        if (categoryIdDto.getArticleCategoryId() != null) {
            Optional<Category> articleCategoryOptional = categoryRepository.findById(categoryIdDto.getArticleCategoryId());
            if (articleCategoryOptional.isEmpty()) return HttpStatus.INTERNAL_SERVER_ERROR;
            articleCategory = articleCategoryOptional.get();
        }

        if (categoryIdDto.getCategoryId() != null) {
            Optional<Category> categoryOptional = categoryRepository.findById(categoryIdDto.getCategoryId());
            if (categoryOptional.isEmpty()) return HttpStatus.INTERNAL_SERVER_ERROR;
            category = categoryOptional.get();
        }

        cover.changeArticleCategory(articleCategory);
        cover.changeCategory(category);

        return HttpStatus.OK;
    }
}
