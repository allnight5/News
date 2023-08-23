package com.example.news.member.entity;

import com.example.news.share.Timestamped;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String email;
    private String password;
    private String nickName;
    private boolean isDeleted;

    public Member(String email, String password,
                  String nickName) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.isDeleted = true;
    }

    public void flipDeletedFlag(){
        this.isDeleted = !this.isDeleted;
    }
}
