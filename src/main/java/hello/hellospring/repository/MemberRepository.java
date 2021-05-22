package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원이 저장소로 저장됨
    Optional<Member> findById(Long id); // 저장소에서 id로 찾음
    Optional<Member> findByName(String name); // 저장소에서 name으로 찾음
    List<Member> findAll(); // 저장소에 저장된 회원의 list를 모두 반환함
}