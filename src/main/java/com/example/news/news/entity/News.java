package com.example.news.news.entity;

import com.example.news.news.dto.NewsUpdateRequestDto;
import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String newsTitle;

    @Column(nullable = false)
    private String reporter;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String category;

    private String mainImageUrl;

    private boolean lock = false;
    private boolean aliveNews = true;

    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<NewsImage> newsImages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<NewsVideo> newsVideos = new LinkedHashSet<>();

    @Builder
    public News(String newsTitle, String reporter,
                String category, String content,
                String mainImageUrl){
        this.newsTitle = newsTitle;
        this.reporter = reporter;
        this.category =category;
        this.content = content;
        this.mainImageUrl = mainImageUrl;
    }
    public void updateNews(NewsUpdateRequestDto requestDto){
        this.newsTitle = requestDto.getNewsTitle();
        this.reporter = requestDto.getReporter();
        this.category = requestDto.getCategory();
        this.content = requestDto.getContent();
        this.mainImageUrl = requestDto.getMainImageUrl();
    }

    public void softDeleteNews(){
        this.aliveNews = false;
    }
    public void lockNews(){
        this.lock = true;
    }
    public void unLockNews(){
        this.lock = false;
    }

    public boolean getAliveNews(){
        return this.aliveNews;
    }
    public boolean getLock(){
        return this.lock;
    }
}
