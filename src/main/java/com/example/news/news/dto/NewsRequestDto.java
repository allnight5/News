package com.example.news.news.dto;

import lombok.Getter;
@Getter
public class NewsRequestDto {
    private String newsTitle;
    private String reporter;
    private String content;
    private String category;
    private String mainImageUrl;
}
