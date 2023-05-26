package com.example.news.mockito.controller;

import com.example.news.news.controller.NewsController;
import com.example.news.news.dto.NewsRequestDto;
import com.example.news.news.dto.NewsResponseDto;
import com.example.news.news.entity.News;
import com.example.news.news.service.NewsService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(NewsController.class)
public class NewsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NewsService newsService;
//
//    @Test
//    @DisplayName("news createNews_success")
//    void createNews_success() throws Exception {
//        // given
//        NewsRequestDto requestDto =
//                new NewsRequestDto("testTitle","reporter",
//                        "content","category",
//                        "imageUrl");
//
//        News news = requestDto.toEntity(1L);
//        news.testSetId(1L);
//
//        NewsResponseDto responseDto = null;
//        try {
//            responseDto = newsService.createNews(requestDto);
//        } catch (NullPointerException e) {
//            System.out.println("NullPointerException 없다고");
//        }
//        when(newsService.createNews(requestDto)).thenReturn(responseDto);
//
//        // when
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/news")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(requestDto)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
//
//        // then
//        verify(newsService, times(1)).createNews(requestDto);
//    }

    @Test
    @DisplayName("news createNews_success")
    void getNewsDefault_success() throws Exception {
        // given
        List<NewsResponseDto> newsList = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            // 20개의 더미 MovieResponseDto 객체를 생성하여 movieList에 추가
            NewsResponseDto news = new NewsResponseDto();
            newsList.add(news);
        }

        when(newsService.getNewsMain()).thenReturn(newsList);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/news")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(20));

        // then
        verify(newsService, times(1)).getNewsMain();
    }

    private String asJsonString(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
