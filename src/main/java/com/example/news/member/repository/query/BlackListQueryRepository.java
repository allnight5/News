package com.example.news.member.repository.query;

import com.example.news.member.entity.BlackList;

import java.util.List;

public interface BlackListQueryRepository {
    List<BlackList> findExpiredRestrictions();
}
