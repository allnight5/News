package com.example.news.news.repository;

import com.example.news.news.entity.NewsVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface newsVideoRepository extends JpaRepository<NewsVideo, Long> {
}
