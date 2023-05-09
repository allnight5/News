package com.example.news.news.dto;

import com.example.news.news.entity.NewsVideo;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class NewsVideoResponseDto {

    private Long id;
    private String videoUrl;
    private Long newsId;
    private NewsVideoResponseDto(NewsVideo newsVideo){
        this.id = newsVideo.getId();
        this.videoUrl= newsVideo.getVideoUrl();
        this.newsId=newsVideo.getNews().getId();
    }
    public static NewsVideoResponseDto from(NewsVideo newsVideo){
        return new NewsVideoResponseDto(newsVideo);
    }
}
