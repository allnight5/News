package com.example.news.news.dto;

import com.example.news.news.entity.News;
import lombok.Getter;
@Getter
public class NewsRequestDto {
    private String newsTitle;
    private String reporter;
    private String content;
    private String category;
    private String mainImageUrl;

    public NewsRequestDto(String newsTitle, String reporter,
                          String content, String category,
                          String mainImageUrl){
        this.newsTitle= newsTitle;
        this.reporter = reporter;
        this.content = content;
        this.category = category;
        this.mainImageUrl = mainImageUrl;
    }

    public News toEntity(Long newsId){

        return News.builder().
                reporterIndex(newsId).
                newsTitle(newsTitle).
                reporter(reporter).
                content(content).
                mainImageUrl(mainImageUrl).
                category(category).
                build();
    }
}
