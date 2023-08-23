package com.example.news.member.repository.query;

import com.example.news.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.news.member.entity.QMember.member;
@Repository
public class MemberQueryRepositoryImpl implements MemberQueryRepository{
    private final JPAQueryFactory jpaQueryFactory;

    public MemberQueryRepositoryImpl(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Member> getMemberList(int start, int endPoint) {

        return jpaQueryFactory.select(member)
                .where(member.id.between(start, endPoint))
                .fetch();
    }
}
