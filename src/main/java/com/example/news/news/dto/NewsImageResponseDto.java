package com.example.news.news.dto;

import com.example.news.news.entity.NewsImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class NewsImageResponseDto {

    private Long id;
    private String imageUrl;
    private Long newsId;
    private NewsImageResponseDto(NewsImage newsImage){
        this.id = newsImage.getId();
        this.imageUrl= newsImage.getImageUrl();
        this.newsId=newsImage.getNews().getId();
    }
    public static NewsImageResponseDto from(NewsImage newsImage){
        return new NewsImageResponseDto(newsImage);
    }
}
