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

@Component
public class Common {

    @Value("${imagePath}")
    private String imgPath;
    @Value("${file.upload.directory}")
    private String imgUploadPath;

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

    public String getServerUrlImage(String image) {
        if (image == null) return null;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + imgPath + image;
    }

    public boolean uploadImageFile(MultipartFile imageFile) {
        if (imageFile == null) return false;
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
}
