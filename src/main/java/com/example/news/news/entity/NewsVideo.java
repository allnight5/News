package com.example.news.news.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewsVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String videoUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    private News news;

    @Builder
    public NewsVideo(String videoUrl, News news){
        this.videoUrl = videoUrl;
        this.news = news;
    }
//    @Builder
//    public static NewsVideo createVideoOf(String videoUrl, News news){
//        NewsVideo newsVideo = new NewsVideo();
//        newsVideo.videoUrl =videoUrl;
//        newsVideo.news = news;
//        return newsVideo;
//    }

}
