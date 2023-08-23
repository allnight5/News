package com.example.news.member.dto;


import lombok.Getter;

@Getter
public class LogoutRequestDto {
    private final int id;
    private final String password;

    public LogoutRequestDto(int id,
                            String password){
        this.id = id;
        this.password = password;
    }
}
