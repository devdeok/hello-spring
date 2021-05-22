package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    /* TDD(Test-driven Development) : 테스트 주도 개발, 테스트를 먼저 만들고
       class를 만들어서 돌려봄
       이번 코드에서는 그냥 test만 해본 거임*/
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        // 순서가 끝날 때마다 data를 지워줌, test는 서로 의존관계없이 설계되어야함
        // 하나의 test가 끝날 때마다 저장소나 공용데이터를 지워주어야 함
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, null); // ctrl + p로 parameter확인
        assertThat(member).isEqualTo(result); // member가 result와 같다
        // alt + enter를 통해 static import 했음
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        //복붙했을 때 shift+F6을 통해 rename가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        
        // ctrl+alt+V를 통해 Optional<member> 객체 추가
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    // method의 실행순서는 보장이 되지 않는다.
    // test를 끝내면 data를 clear해줘야 한다.
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
    
}
