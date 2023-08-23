package com.example.news.member.controller;

import com.example.news.member.entity.BlackList;
import com.example.news.member.service.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class BlackListController {
    private final BlackListService blackListService;

    @PostMapping("/add-blacklist")
    public String processRestrictions(@RequestParam("selectedRestrictions") List<String> selectedRestrictions,
                                      @RequestParam("memberId") Long memberId,
                                      @RequestParam("plusDateTime") Long plusDateTime
    ) {
        // selectedRestrictions 리스트에 선택된 제한들이 들어 있음
        // memberId로 해당 멤버를 식별하여 처리하는 로직을 구현
        blackListService.addOrUpdateBlackList(selectedRestrictions, memberId, plusDateTime);
        // 처리 로직 후 리다이렉트 등을 수행
        return "redirect:/member/get-all-blacklist";
    }

    @GetMapping("/get-all-blacklist")
    public String getAllBlackList(Model model){
        List<BlackList> responseDto =  blackListService.getAllBlackList();
        model.addAttribute("getBlackListResponse",responseDto);
        return "black_ist_member";
    }
}
