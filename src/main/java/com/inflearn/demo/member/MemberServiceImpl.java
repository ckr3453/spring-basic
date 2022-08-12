package com.inflearn.demo.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();     // 구현체를 직접 작성(new)하는 과정에서 클라이언트에 의존적임 (DIP 위배)

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
