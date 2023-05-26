package com.example.news.mockito.entity;

import com.example.news.news.dto.NewsUpdateRequestDto;
import com.example.news.news.entity.News;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NewsEntityTest {
    @Test
    @DisplayName("News builder Test")
    void newsBuilder(){
        //given&when
        News news = News.builder()
                .reporterIndex(1L)
                .newsTitle("title")
                .content("content!~")
                .category("category")
                .reporter("writer")
                .build();
        //then
        assertThat(news.getNewsTitle()).isEqualTo("title");
        assertThat(news.getReporter()).isEqualTo("writer");
    }

    @Test
    @DisplayName("News update Test")
    void newsUpdate(){
        //given
        News news = News.builder()
                .reporterIndex(1L)
                .newsTitle("title")
                .content("content!~")
                .category("category")
                .reporter("writer")
                .build();

        NewsUpdateRequestDto requestDto =
                new NewsUpdateRequestDto("titles","news","e","e","e");
        //when
        news.updateNews(requestDto);

        //then
        assertThat(news.getNewsTitle()).isEqualTo("titles");
        assertThat(news.getReporter()).isEqualTo("news");
    }
}
