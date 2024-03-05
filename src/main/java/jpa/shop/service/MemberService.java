package jpa.shop.service;

import jakarta.transaction.Transactional;
import jpa.shop.entity.Member;
import jpa.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public Member saveMember(Member member){
        valiateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void valiateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember !=null){
            throw new IllegalStateException("이미 가입된 회원입니다!");
        }
    }
}
