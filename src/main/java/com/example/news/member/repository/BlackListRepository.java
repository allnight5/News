package com.example.news.member.repository;

import com.example.news.member.entity.BlackList;
import com.example.news.member.repository.query.BlackListQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlackListRepository extends JpaRepository<BlackList, Long>, BlackListQueryRepository {
    List<BlackList> findAll();
    boolean existsByEmail(String email);
    BlackList findByEmail(String email);
}
