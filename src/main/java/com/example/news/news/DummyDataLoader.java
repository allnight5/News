package com.example.news.news;

import com.example.news.news.dto.NewsRequestDto;
import com.example.news.news.entity.News;
import com.example.news.news.entity.NewsImage;
import com.example.news.news.entity.NewsVideo;
import com.example.news.news.repository.JdbcBatchInsertRepository;
import com.example.news.news.repository.NewsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DummyDataLoader implements CommandLineRunner {

    private final NewsRepository newsRepository;
    private final JdbcBatchInsertRepository jdbcBatchInsertRepository;

    @Override
    public void run(String... args) throws Exception {
        List<News> news = newsRepository.findAll();
        List<NewsImage> newsImages = new ArrayList<>();

        for(News n : news){
            for(int i=0; i<=3;i++){
                NewsImage newsImage= NewsImage.builder()
                        .news(n)
                        .imageUrl("image_"+i+"_from_news_"+n.getId())
                        .build();
                newsImages.add(newsImage);
            }
        }
        jdbcBatchInsertRepository.batchInsertNewsImage(newsImages);

        List<NewsVideo> newsVideos = new ArrayList<>();
        for(News n : news){
            for(int i=0; i<=3;i++){
                NewsVideo newsVideo= NewsVideo.builder()
                        .news(n)
                        .videoUrl("video_"+i+"_from_news_"+n.getId())
                        .build();
                newsVideos.add(newsVideo);
            }
        }
        jdbcBatchInsertRepository.batchInsertNewsVideo(newsVideos);
    }

    @PostConstruct
    public void beforeRun(){
        List<News> news = IntStream.rangeClosed(1, 30)
                .mapToObj(i -> News.builder()
                        .newsTitle("News_"+i)
                        .category("NewsCategory_"+i)
                        .content("content_"+i)
                        .mainImageUrl("imageUrl_"+i)
                        .reporter("reporter_name_"+i)
                        .build())
                .collect(Collectors.toList());

        jdbcBatchInsertRepository.batchInsertNews(news);
    }
}
