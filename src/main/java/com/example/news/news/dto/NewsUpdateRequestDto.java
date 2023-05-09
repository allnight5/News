package com.example.news.news.dto;

import lombok.Getter;
@Getter
public class NewsUpdateRequestDto {
    private String newsTitle;
    private String reporter;
    private String content;
    private String category;
    private String mainImageUrl;
}
