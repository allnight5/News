package com.example.news.mockito.service;

import com.example.news.error.exception.ExceptionAdvice;
import com.example.news.news.dto.NewsRequestDto;
import com.example.news.news.dto.NewsResponseDto;
import com.example.news.news.dto.NewsUpdateRequestDto;
import com.example.news.news.entity.News;
import com.example.news.news.repository.NewsRepository;
import com.example.news.news.service.NewsServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class NewsServiceTest {
    @Mock
    NewsRepository newsRepository;

    @InjectMocks
    NewsServiceImpl newsServiceImpl;

    @Test
    @DisplayName("news_create_success1")
    public void createNews_success1(){
        //given
        NewsRequestDto newsRequestDto = mock(NewsRequestDto.class);
        News news = mock(News.class);
        when(newsRequestDto.toEntity(1L))
                .thenReturn(news);
        //when
        newsServiceImpl.createNews(newsRequestDto);
        //then
        verify(newsRepository, times(1)).save(news);
    }
    @Test
    @DisplayName("news_create_success2")
    public void createNews_success2(){
        //given
        NewsRequestDto newsRequestDto = new NewsRequestDto(
                "title","reporter","content","category","url");

        when(newsRepository.save(any(News.class)))
                .thenReturn(newsRequestDto.toEntity(1L));

        //when
        newsServiceImpl.createNews(newsRequestDto);
        //then
        verify(newsRepository, times(1)).save(any(News.class));
    }

    @Test
    @DisplayName("News select_one_success")
    void getSelectedNews_success(){
        //given

        NewsRequestDto newsRequestDto = new NewsRequestDto(
                "title","reporter","content","category","url");
        News news = newsRequestDto.toEntity(1L);

        when(newsRepository.findByIdAndAliveNewsIsTrue(anyLong()))
                .thenReturn(Optional.of(news));
        //when
        NewsResponseDto responseDto = newsServiceImpl.getNewsById(anyLong());

        //then
        assertThat(responseDto.getNewsTitle()).isEqualTo(news.getNewsTitle());
        assertThat(responseDto.getReporter()).isEqualTo(news.getReporter());
        verify(newsRepository).findByIdAndAliveNewsIsTrue(anyLong());
    }

    @Test
    @DisplayName("News select_one_fail(newsIsEmpty)")
    void getSelectNews_throw(){
        //given
        when(newsRepository.findByIdAndAliveNewsIsTrue(anyLong()))
                .thenReturn(Optional.empty());
        //when&then
        assertThrows(NoSuchElementException.class, ()->{
           newsServiceImpl.getNewsById(1L);
        });
    }

    @Test
    @DisplayName("News update_success")
    void updateNews_success(){
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

        news.updateNews(requestDto);

        when(newsRepository.findByIdAndAliveNewsIsTrue(anyLong()))
                .thenReturn(Optional.of(news));

        //when
        newsServiceImpl.updateNews(1L, requestDto);

        //then
        assertThat(news.getNewsTitle()).isEqualTo("titles");
        assertThat(news.getReporter()).isEqualTo("news");
        assertThat(news.getCategory()).isEqualTo("e");
        //update시에 @Transactional어노테이션을 통한 자동 save를 적용해두었으나
        //mock에서는 이것을 잡아내지 못하기때문에 검증할수없다.
        //변경사항을 동작하였기 때문에 이것으로 종료하며 확인하고 싶다면
        //해당메서드에 save()를 넣어주면된다.
        //update에 @Transactional을 빼지않고 넣어준이유는
        // update시 오류가 발생했다면 save하지말고 빠져나와야하는데
        //@Transactional이 없다면 전에 것을 그냥 save할 확률이 존재하기 때문에
        //@Transactional어노테이션을 붙여줘서 롤백하도록 하며 예외가
        //발생했음을 알려주기 위해서이다.ㅔ
//        verify(newsRepository, times(1)).save(any(News.class));
    }


}
