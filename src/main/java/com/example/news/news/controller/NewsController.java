package com.example.news.news.controller;

import com.example.news.news.dto.NewsRequestDto;
import com.example.news.news.dto.NewsResponseDto;
import com.example.news.news.dto.NewsSearchCond;
import com.example.news.news.dto.NewsUpdateRequestDto;
import com.example.news.news.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/api/news")
    public ResponseEntity<List<NewsResponseDto>> getNews(){
        List<NewsResponseDto> newsList = newsService.getNewsMain();
        if(newsList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(newsList);
        }
    }
    @GetMapping("/api/news/{newsId}")
    public ResponseEntity<NewsResponseDto> getNewsById(@PathVariable Long newsId){
        return ResponseEntity.ok(newsService.getNewsById(newsId));
    }
    @GetMapping("/api/news/pages/{pageNumber}")
    public ResponseEntity<List<NewsResponseDto>> getNewsPage(@PathVariable Long pageNumber){
        List<NewsResponseDto> newsList = newsService.getNewsPaging(pageNumber);
        if(newsList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/api/news/search")
    public ResponseEntity<List<NewsResponseDto>> newsBySearchCond(@RequestBody NewsSearchCond searchCond) {
        List<NewsResponseDto> newsList = newsService.getNewsBySearchCond(searchCond);
        if(newsList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(newsList);
    }

    @PostMapping("/api/news")
    public ResponseEntity<Void> CreateNews(@RequestBody @Valid NewsRequestDto requestDto){
        NewsResponseDto news = newsService.createNews(requestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(news.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/api/news/{newsId}")
    public ResponseEntity<Void> updateNews(
            @PathVariable Long newsId,
            @RequestBody NewsUpdateRequestDto requestDto){
        newsService.updateNews(newsId, requestDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/news/{newsId}/lock")
    public ResponseEntity<Void> lockNews(
            @PathVariable Long newsId){
        newsService.lockNews(newsId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/news/{newsId}/unlock")
    public ResponseEntity<Void> unLockNews(
            @PathVariable Long newsId){
        newsService.unLockNews(newsId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/news/{newsId}")
    public ResponseEntity<Void> deleteNews(
            @PathVariable Long newsId) {
        newsService.deleteNews(newsId);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/api/news/soft_delete/{newsId}")
    public ResponseEntity<Void> softDeleteNews(
            @PathVariable Long newsId) {
        newsService.softDeleteNews(newsId);
        return ResponseEntity.ok().build();
    }
}
