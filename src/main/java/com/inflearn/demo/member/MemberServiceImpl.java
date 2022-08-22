package com.inflearn.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // 컴포넌트 스캔 대상 (빈 등록)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired  // 자동 의존관계주입 = ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용 (싱글톤 패턴이 깨지는지)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
