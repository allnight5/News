package com.example.news.member.service;

import com.example.news.member.dto.LoginRequestDto;
import com.example.news.member.dto.LogoutRequestDto;
import com.example.news.member.dto.LoginResponseDto;
import com.example.news.member.dto.RestrictionResponseDto;
import com.example.news.member.entity.BlackList;
import com.example.news.member.entity.Member;
import com.example.news.member.entity.Restriction;
import com.example.news.member.repository.BlackListRepository;
import com.example.news.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public String signup(Member member) {
        memberRepository.save(member);
        return "성공";
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }

    @Override
    public String logout(LogoutRequestDto logoutRequestDto) {
        return null;
    }

    @Override
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    public RestrictionResponseDto restrictionList(){
        return new RestrictionResponseDto();
    }


}
