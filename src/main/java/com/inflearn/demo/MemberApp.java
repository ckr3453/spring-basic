package com.inflearn.demo;

import com.inflearn.demo.member.Grade;
import com.inflearn.demo.member.Member;
import com.inflearn.demo.member.MemberService;
import com.inflearn.demo.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        //        MemberService memberService = new MemberServiceImpl();
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
