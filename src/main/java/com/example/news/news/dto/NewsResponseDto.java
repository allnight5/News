package com.example.news.news.dto;

import com.example.news.news.entity.News;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class NewsResponseDto {
    private Long id;
    private String newsTitle;
    private String reporter;
    private String content;
    private String category;
    private String mainImageUrl;
    private Set<NewsVideoResponseDto> newsVideos;
    private Set<NewsImageResponseDto> newsImages;

    private NewsResponseDto(News news){
        this.id = news.getId();
        this.reporter = news.getReporter();
        this.newsImages = news.getNewsImages().stream()
                .map(NewsImageResponseDto::from)
                .sorted(Comparator.comparingLong(NewsImageResponseDto::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        this.newsVideos = news.getNewsVideos().stream()
                .map(NewsVideoResponseDto::from)
                .sorted(Comparator.comparingLong(NewsVideoResponseDto::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        this.newsTitle = news.getNewsTitle();
        this.content = news.getContent();
        this.category = news.getCategory();
        this.mainImageUrl = news.getMainImageUrl();

    }
    public static NewsResponseDto from(News news){
        return new NewsResponseDto(news);
    }

    public static List<NewsResponseDto> listFrom(List<News> news){
        return news.stream().map(NewsResponseDto::from).collect(Collectors.toList());
    }
}
