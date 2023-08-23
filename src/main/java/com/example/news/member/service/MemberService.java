package com.example.news.member.service;

import com.example.news.member.dto.LoginRequestDto;
import com.example.news.member.dto.LogoutRequestDto;
import com.example.news.member.dto.LoginResponseDto;
import com.example.news.member.dto.RestrictionResponseDto;
import com.example.news.member.entity.BlackList;
import com.example.news.member.entity.Member;
import com.example.news.member.entity.Restriction;

import java.util.List;

public interface MemberService {
    String signup(Member member);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    String logout(LogoutRequestDto logoutRequestDto);
    List<Member> findAllMember();
    RestrictionResponseDto restrictionList();
}
