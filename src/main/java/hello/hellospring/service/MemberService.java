package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;
    
    // 외부에서 memberRepository를 넣어줌 -> Dependency Injection
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */
    public Long join(Member member){
        // 중복 회원 검증 후 저장
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); // 중복 회원 검증
        // result.orElseGet(); //값이 있으면 꺼내고 값이 없으면 여기 있는 메서드 실행
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 리턴값이 optional임
        /* ifPresent는 result에 NULL이 아닌 값이 존재할 경우 동작하게 된다.
         * result가 Optional이기 때문에 가능하다.
         * Optional로 한번 감싸게 되면 Optional 안에 member 객체가 있음
         * 그러므로 여러 메서드를 사용할 수 있게 된다.
         */
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }
    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
