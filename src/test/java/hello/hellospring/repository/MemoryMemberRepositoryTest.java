package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest{
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트가 끝날때 마다 repository 를 지워주는 코드
    @AfterEach // 뜻 : 밑에 있는 메서드 들의 실행이 끝날때 마다 동작을 하는 것이다.
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
        // System.out.println("result = " + (result == member));
    }
    // 그 다음 테스트 해 봐야 하는 것은
    // repository 패키지의 MemoryMemberRepository의 findByName() 메소드 이다.
    // 이름을 통해서 객체를 찾는 것이다.
    @Test
    public void findByName(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    // 그다음에는 findAll()메소드를 테스트 해 보겠다.
    @Test
    public void findAll(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}

















