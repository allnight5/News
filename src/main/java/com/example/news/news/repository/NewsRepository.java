package com.example.news.news.repository;

import com.example.news.news.entity.News;
import com.example.news.news.repository.query.NewsQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long>, NewsQueryRepository {


}
