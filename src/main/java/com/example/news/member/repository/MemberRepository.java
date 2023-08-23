package com.example.news.member.repository;

import com.example.news.member.entity.Member;
import com.example.news.member.repository.query.MemberQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {

    Optional<Member> findByEmail(String email);
}
