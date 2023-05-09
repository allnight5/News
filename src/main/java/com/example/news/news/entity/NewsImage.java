package com.example.news.news.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewsImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private News news;

    @Builder
    public NewsImage(String imageUrl, News news) {
        this.imageUrl = imageUrl;
        this.news = news;
    }

    public void updateImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public void setNews(News news){
        this.news = news;
    }

//    @Builder
//    public static NewsImage createImageOf(String imageUrl, News news) {
//        NewsImage newsImage = new NewsImage();
//        newsImage.imageUrl = imageUrl;
//        newsImage.news = news;
//        return newsImage;
//    }

}
