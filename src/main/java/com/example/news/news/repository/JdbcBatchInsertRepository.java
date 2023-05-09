package com.example.news.news.repository;

import com.example.news.news.entity.News;
import com.example.news.news.entity.NewsImage;
import com.example.news.news.entity.NewsVideo;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcBatchInsertRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcBatchInsertRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void batchInsertNews(List<News> newsList){
        String sql = "INSERT INTO news (news_title, reporter, category, content, main_image_url, lock ,alive_news )" +
                "VALUES(?,?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException{
                News news = newsList.get(i);
                ps.setString(1, news.getNewsTitle());
                ps.setString(2, news.getReporter());
                ps.setString(3, news.getCategory());
                ps.setString(4, news.getContent());
                ps.setString(5, news.getMainImageUrl());
                ps.setBoolean(6, news.getLock());
                ps.setBoolean(7, news.getAliveNews());
            }
            @Override
            public int getBatchSize() {
                return newsList.size();
            }
        });
    }

    public void batchInsertNewsImage(List<NewsImage> newsImages) {

        String sql = "INSERT INTO news_image (image_url, news_id) VALUES (?,?)";
        jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                NewsImage newsImage = newsImages.get(i);
                ps.setString(1, newsImage.getImageUrl());
                ps.setLong(2,newsImage.getNews().getId());
            }

            @Override
            public int getBatchSize() {
                return newsImages.size();
            }
        });
    }

    public void batchInsertNewsVideo(List<NewsVideo> newsVideos) {
        String sql = "INSERT INTO news_video (video_url, news_id) VALUES (?,?)";
        jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                NewsVideo newsVideo = newsVideos.get(i);
                ps.setString(1,newsVideo.getVideoUrl());
                ps.setLong(2,newsVideo.getNews().getId());
            }
            @Override
            public int getBatchSize() {
                return newsVideos.size();
            }
        });
    }
}
