package com.example.news.member.dto;

import com.example.news.member.entity.Restriction;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class RestrictionResponseDto {
    List<Restriction> restrictionList;
    public RestrictionResponseDto(){
        this.restrictionList =
                Arrays.asList(
                        Restriction.BLOCK_ALL,
                        Restriction.CHAT,
                        Restriction.WRITE_POST,
                        Restriction.COMPLETE_CHAT_VIEW
                );
    }
}
