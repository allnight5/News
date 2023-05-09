package com.example.news.news.service;

import com.example.news.news.dto.NewsRequestDto;
import com.example.news.news.dto.NewsResponseDto;
import com.example.news.news.dto.NewsSearchCond;
import com.example.news.news.dto.NewsUpdateRequestDto;
import com.example.news.news.entity.News;
import com.example.news.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{
    private final NewsRepository newsRepository;


    @Override
    public List<NewsResponseDto> getNewsMain(){
        return newsRepository.getNewsMain();
    }

    @Override
    @Transactional
    public NewsResponseDto createNews(NewsRequestDto requestDto) {
        if(newsRepository.existsByNewsTitle(requestDto.getNewsTitle())){
            throw new DataIntegrityViolationException("중복된 뉴스제목이 존재합니다.");
        }

        News news = News.builder()
                .newsTitle(requestDto.getNewsTitle())
                .category(requestDto.getCategory())
                .content(requestDto.getContent())
                .mainImageUrl(requestDto.getMainImageUrl())
                .reporter(requestDto.getReporter())
                .build();

        newsRepository.save(news);
        return NewsResponseDto.from(news);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsResponseDto> getNewsPaging(Long pageNumber) {
        return newsRepository.getNewsMainPaging(pageNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsResponseDto> getNewsBySearchCond(NewsSearchCond newsSearchCond) {
        return newsRepository.getNewsMainSearch(newsSearchCond);
    }

    @Override
    @Transactional(readOnly = true)
    public NewsResponseDto getNewsById(Long newsId){
        News news = getNews(newsId);
        return NewsResponseDto.from(news);
    }

    @Override
    @Transactional
    public void updateNews(Long newsId, NewsUpdateRequestDto requestDto) {
        News news = getNews(newsId);
        news.updateNews(requestDto);
    }
    @Override
    @Transactional
    public void lockNews(Long newsId) {
        News news = getNews(newsId);
        news.lockNews();
    }

    @Override
    @Transactional
    public void unLockNews(Long newsId) {
        News news = getNews(newsId);
        news.unLockNews();
    }

    @Override
    @Transactional
    public void softDeleteNews(Long newsId){
        News news = getNews(newsId);
        news.softDeleteNews();
    }

    private News getNews(Long newsId) {
        return newsRepository.findByIdAndAliveNewsIsTrue(newsId)
                .orElseThrow(
                    () -> new NoSuchElementException("해당 뉴스가 존재하지 않습니다.")
                );
    }

    @Override
    @Transactional
    public void deleteNews(Long newsId){
        newsRepository.deleteNewsById(newsId);
    }
}
