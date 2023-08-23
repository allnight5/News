package com.example.news.member.repository;

import com.example.news.member.entity.BlackList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcBatchDeleteRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcBatchDeleteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void batchDeleteAllNewsImages(List<BlackList> blackLists) {

        String sql = "DELETE FROM black_list WHERE id IN (:ids)";

        // 아래에서 사용할 blackLists에 해당하는 id 목록을 추출
        List<Long> blackListIds = blackLists.stream()
                .map(BlackList::getId)
                .collect(Collectors.toList());

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ids", blackListIds);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
