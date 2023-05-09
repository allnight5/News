package com.example.news.news.service;

import com.example.news.news.dto.NewsRequestDto;
import com.example.news.news.dto.NewsResponseDto;
import com.example.news.news.dto.NewsSearchCond;
import com.example.news.news.dto.NewsUpdateRequestDto;
import com.example.news.news.entity.News;

import java.util.List;

public interface NewsService {
    List<NewsResponseDto> getNewsMain();
    NewsResponseDto getNewsById(Long newsId);
    NewsResponseDto createNews(NewsRequestDto newsRequestDto);
    List<NewsResponseDto> getNewsPaging(Long pageNumber);
    List<NewsResponseDto> getNewsBySearchCond(NewsSearchCond newsSearchCond);
    void updateNews(Long newsId, NewsUpdateRequestDto requestDto);
    void lockNews(Long newsId);
    void unLockNews(Long newsId);
    void softDeleteNews(Long newsId);
    void deleteNews(Long newsId);
}
