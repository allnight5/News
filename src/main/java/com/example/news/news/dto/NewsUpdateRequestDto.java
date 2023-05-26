package com.example.news.news.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class NewsUpdateRequestDto {
    private String newsTitle;
    private String reporter;
    private String content;
    private String category;
    private String mainImageUrl;

    public NewsUpdateRequestDto(String newsTitle, String reporter,
                                String category, String content,
                                String mainImageUrl){
        this.newsTitle = newsTitle;
        this.reporter = reporter;
        this.category =category;
        this.content = content;
        this.mainImageUrl = mainImageUrl;

    }
}
