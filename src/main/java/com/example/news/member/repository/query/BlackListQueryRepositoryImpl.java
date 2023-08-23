package com.example.news.member.repository.query;

import com.example.news.member.entity.BlackList;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static com.example.news.member.entity.QBlackList.blackList;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BlackListQueryRepositoryImpl implements BlackListQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public BlackListQueryRepositoryImpl(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<BlackList> findExpiredRestrictions() {
        LocalDateTime today = LocalDateTime.now();

        return jpaQueryFactory.selectFrom(blackList)
                .where(blackList.restrictionEndDateTime.before(today))
                .fetch();
    }
}