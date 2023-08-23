package com.example.news.member.dto;

import com.example.news.member.entity.Restriction;
import lombok.Getter;

import java.util.List;

@Getter
public class LoginResponseDto {
    private int id;
    private String nickName;
    private String outPassword;
    private List<Restriction> restriction ;
}
