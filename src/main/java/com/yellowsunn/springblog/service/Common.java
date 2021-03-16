package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.entity.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Common {

    @Value("${imagePath}")
    private String imgPath;
    @Value("${file.upload.directory}")
    private String imgUploadPath;

    private final String UUID_REGEX = "([\\w\\d]{8}-[\\w\\d]{4}-[\\w\\d]{4}-[\\w\\d]{4}-[\\w\\d]{12}.\\w+)";

    // 본문 내용 요약처리
    public String getSummary(String content) {
        String summary = removeTag(content);
        if (summary.length() > 200) {
            summary = summary.substring(0, 200);
        }

        return summary;
    }

    public String removeTag(String html) {
        html = html.replaceAll("<br[/]?>", " ");
        html = html.replaceAll("\\s+", " ");
        return html.replaceAll("(<([^>]+)>)", "");
    }

    public String getServerUrlImage() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + imgPath;
    }

    // 콘텐츠 이미지 서버주소 prefix로 추가
    public String addServerUrlContent(String content) {
        Pattern pattern = Pattern.compile("src=\"" + UUID_REGEX + "\"");
        Matcher matcher = pattern.matcher(content);
        List<String> imageFiles = new ArrayList<>();
        while (matcher.find()) {
            imageFiles.add(matcher.group(1));
        }

        for (String imageFile : imageFiles) {
            content = content.replace(imageFile, getServerUrlImage() + imageFile);
        }

        return content;
    }

    // 콘텐츠 이미지 서버주소 prefix에서 삭제
    public String removeServerUrlContent(String content) {
        Pattern pattern = Pattern.compile("src=\"(" + getServerUrlImage() + UUID_REGEX + ")\"");
        Matcher matcher = pattern.matcher(content);
        Map<String, String> imageMap = new HashMap<>();
        while (matcher.find()) {
            imageMap.put(matcher.group(1), matcher.group(2));
        }

        for (Map.Entry<String, String> entry : imageMap.entrySet()) {
            String urlImage = entry.getKey(), image = entry.getValue();
            content = content.replace(urlImage, image);
        }

        return content;
    }

    public boolean uploadImageFile(MultipartFile imageFile) {
        if (imageFile == null) return true;
        File path = new File(imgUploadPath);

        // 디렉토리가 없으면 디렉토리 생성
        if (!path.exists()) {
            boolean makeDir = path.mkdirs();
            if (!makeDir) {
                return false;
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(imgUploadPath + imageFile.getOriginalFilename());
            fos.write(imageFile.getBytes());
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeImageFile(String imageName) {
        File file = new File(imgUploadPath + imageName);

        if (file.exists()) {
            return file.delete();
        } else {
            return true;
        }
    }

    public String replaceNewLine(String word) {
        word = word.replaceAll("(<([^>]+)>)", "");
        return word.replaceAll("\\n", "<br>");
    }
}
