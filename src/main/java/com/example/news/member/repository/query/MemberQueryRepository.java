package com.example.news.member.repository.query;

import com.example.news.member.entity.Member;
import com.example.news.news.dto.NewsResponseDto;

import java.util.List;

public interface MemberQueryRepository {

    List<Member> getMemberList(int start, int endPoint);
}
