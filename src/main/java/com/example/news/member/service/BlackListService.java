package com.example.news.member.service;

import com.example.news.member.entity.BlackList;
import com.example.news.member.entity.Member;
import com.example.news.member.repository.BlackListRepository;
import com.example.news.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlackListService {

    private final BlackListRepository blackListRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addOrUpdateBlackList(List<String> blackList, Long id, Long plusDateTime) {
        Member member = memberRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("찾을수 없어여")
        );

        BlackList blackListToUpdate = blackListRepository.findByEmail(member.getEmail());
        if (blackListToUpdate == null) {
            createNewBlackList(member, blackList, plusDateTime);
        } else {
            updateExistingBlackList(blackListToUpdate, blackList);
        }
    }

    private void createNewBlackList(Member member, List<String> blackList, Long plusDateTime) {
        BlackList newBlackList = new BlackList(member, plusDateTime);
        setRestrictions(newBlackList, blackList);
        blackListRepository.save(newBlackList);
    }

    private void updateExistingBlackList(BlackList blackListToUpdate, List<String> blackList) {
        setRestrictions(blackListToUpdate, blackList);
        blackListRepository.save(blackListToUpdate);
    }

    private void setRestrictions(BlackList blackList, List<String> blackListValues) {
        //blackListValues.forEach(value -> blackList.stringValueOfEnumRestriction(value));
        blackListValues.forEach(blackList::stringValueOfEnumRestriction);
    }

    private boolean existBlackList(String email) {
        return blackListRepository.existsByEmail(email);
    }

    public List<BlackList> getAllBlackList() {
        return blackListRepository.findAll();
    }

}
