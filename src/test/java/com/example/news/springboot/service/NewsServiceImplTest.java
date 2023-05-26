package com.example.news.springboot.service;

import com.example.news.news.dto.NewsResponseDto;
import com.example.news.news.repository.NewsRepository;
import com.example.news.news.service.NewsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NewsServiceImplTest {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsServiceImpl newsServiceImpl;
    //----------------------------------------------------
//    @Before
//    @Transactional
//    public void setUp() {
//        //given
//        for (int i = 0; i < 3; i++) {
//            News news = News.builder()
//                    .newsTitle("News_" + i)
//                    .category("NewsCategory_" + i)
//                    .content("content_" + i)
//                    .mainImageUrl("imageUrl_" + i)
//                    .reporter("reporter_name_" + i)
//                    .build();
//            newsRepository.save(news);
//        }
//    }


    @Test
    @DisplayName("news_Page_Search")
    @Transactional
    public void getNewsMainTest(){
        //given
//        NewsRequestDto newsRequestDto =
//                new NewsRequestDto("News","content","reporter_name","NewsCategory", "content");
//        newsService.createNews(newsRequestDto);
        //when
        List<NewsResponseDto> newsList = newsServiceImpl.getNewsPaging(1L);
        //then
        assertEquals(10, newsList.size());
    }
}
