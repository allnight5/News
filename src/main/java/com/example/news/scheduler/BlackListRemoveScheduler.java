package com.example.news.scheduler;

import com.example.news.member.entity.BlackList;
import com.example.news.member.entity.Restriction;
import com.example.news.member.repository.BlackListRepository;
import com.example.news.member.repository.JdbcBatchDeleteRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class BlackListRemoveScheduler {
    private final BlackListRepository blackListRepository;
    private final JdbcBatchDeleteRepository jdbcBatchDeleteRepository;
    public void removeExpiredRestrictions() {
        LocalDate today = LocalDate.now();
        List<BlackList> blackLists = blackListRepository.findExpiredRestrictions();
        if (!blackLists.isEmpty()) {
            jdbcBatchDeleteRepository.batchDeleteAllNewsImages(blackLists);
        }
    }
}
