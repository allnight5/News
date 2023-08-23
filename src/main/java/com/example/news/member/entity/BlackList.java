package com.example.news.member.entity;

import com.example.news.share.Timestamped;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@Entity

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlackList{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String accessor;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime restrictionEndDateTime;
    private Set<Restriction> restriction = new HashSet<>();

    public BlackList(Member member,
                     Long plusDateTime){
        // 현재 시간
        LocalDateTime now = LocalDateTime.now();
        // 제한일 지정
        LocalDateTime after3Days = now.plus(plusDateTime, ChronoUnit.DAYS);
        this.accessor = member.getNickName();
        this.email = member.getEmail();
        this.restrictionEndDateTime = after3Days;
    }
    public void addRestriction(Restriction restriction){
        this.restriction.add(restriction);
    }

    public void stringValueOfEnumRestriction(String restriction){
        this.restriction.add(Enum.valueOf(Restriction.class, restriction));
    }
    public void removeRestriction(Restriction restriction){
        this.restriction.remove(restriction);
    }
}
