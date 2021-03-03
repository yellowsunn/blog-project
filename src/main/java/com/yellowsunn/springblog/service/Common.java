package com.yellowsunn.springblog.service;

import com.yellowsunn.springblog.domain.entity.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class Common {

    @Value("${imagePath}")
    private String imgPath;

    // 본문 내용 요약처리
    public String getSummary(String content) {
        String summary = removeTag(content);
        if (summary.length() > 200) {
            summary = summary.substring(0, 200);
        }

        return summary;
    }

    private String removeTag(String html) {
        return html.replaceAll("(<([^>]+)>)", "");
    }

    public String getServerUrlImage(String image) {
        if (image == null) return null;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + imgPath + image;
    }
}
