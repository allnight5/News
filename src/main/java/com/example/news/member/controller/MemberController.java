package com.example.news.member.controller;

import com.example.news.member.dto.LoginRequestDto;
import com.example.news.member.dto.LogoutRequestDto;
import com.example.news.member.dto.LoginResponseDto;
import com.example.news.member.dto.RestrictionResponseDto;
import com.example.news.member.entity.Member;
import com.example.news.member.service.MemberService;
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
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signup-form")
    public String signupForm(){
        return "signup_member";
    }

    @GetMapping("/login-form")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("nickName") String nickName,
                         Model model){

        Member member = new Member(email, password,nickName);
        try{
            memberService.signup(member);
            model.addAttribute("signupResponse","회원가입성공");
        }catch (Exception ignored){
            model.addAttribute("signupResponse","회원가입실패");
        }
        return "signup_member";
    }


    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model){
        LoginRequestDto requestDto = new LoginRequestDto(email, password);
        LoginResponseDto responseDto =  memberService.login(requestDto);
        model.addAttribute("loginResponse",responseDto);
        return "index.html";
    }

    @PostMapping("/logout")
    public String logout(@RequestParam("id") int id,
                         @RequestParam("password") String password,
                         Model model){
        LogoutRequestDto requestDto = new LogoutRequestDto(id, password);
        String responseDto =  memberService.logout(requestDto);
        model.addAttribute("logoutResponse",responseDto);
        return "redirect:/index.html";
    }


    @GetMapping("/get-all-member")
    public String getAllMember(Model model){
        List<Member> memberListResponse =  memberService.findAllMember();
        RestrictionResponseDto restrictionListResponse =  memberService.restrictionList();
        model.addAttribute("getMemberListResponse",memberListResponse);
        model.addAttribute("restrictionListResponse",restrictionListResponse);
        return "black_list_add";
    }
}
