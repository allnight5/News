package com.example.news.news.repository.query;

import com.example.news.news.dto.NewsResponseDto;
import com.example.news.news.dto.NewsSearchCond;
import com.example.news.news.entity.News;

import java.util.List;
import java.util.Optional;

public interface NewsQueryRepository {

    List<NewsResponseDto> getNewsMain();
    List<NewsResponseDto> getNewsMainPaging(Long pageNumber);
    List<NewsResponseDto> getNewsMainSearch(NewsSearchCond newsSearchCond);
    boolean existsByNewsId(Long newsId);
    boolean existsByNewsTitle(String newsTitle);

    Optional<News> findByIdAndAliveNewsIsTrue(Long newsId);
    void deleteNewsById(Long newsId);

}
