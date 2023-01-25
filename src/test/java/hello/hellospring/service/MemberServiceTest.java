package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
// MemberService 클래스(위치: main => java => service)를 테스트 하는 코드
public class MemberServiceTest {

   // MemberService memberService = new MemberService();
   // MemoryMemberRepository memberRepository = new MemoryMemberRepository();

   // Dependency Injection // 의존성 주입
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() throws Exception{
        //* Given *  : 주어진 데이터
        Member member = new Member();
        member.setName("hello");
        //* When *  : 제약조건
        Long saveId = memberService.join(member);
            // memberService객체의 join메소드는
                // 1.) 중복 회원 검증 후  중복회원(O) => 예외처리
                //                     중복회원(X) => 메모리 회원 리포지토리에 저장후 , Member객체가 부여받은 Id값(Long타입) 반환
        //* Then *  : 예상되는 결과
        Member findMember = memberService.findOne(saveId).get(); // Optional의 get()메소드 : Optional안에 있는 객체를 리턴해 준다.
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    @Test
    public void 중복_회원_예외() throws Exception{
        //* Given * : 주어진 데이터
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //* When * : 제약조건
        memberService.join(member1);
     /*   try{
            memberService.join(member2); fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
     */
    //이것때문에 try ~ catch문 넣는 것이 애매하다.
    // 그래서 좋은 문법을 제공한다. 바로 "assertThrows( 예외처리 종류, 람다식 )" 이다.
    // ** assertThrows( 예외처리 종류, 람다식); **
    //   => 람다식이 수행될때, 예외처리 종류를 기대하고 있다.
    //   => 람다식을 실행 시에는 IllegalStateException 예외가 터저야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.join(member2));
    // 그럼 메세지는 어떻게 검증할까요? 메세지는 반환이 됩니다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //* Then * : 기대하는 결과
    }
    @Test
    void validateDuplicateMamber(){

    }
    @Test
    void findMembers(){

    }
    @Test
    void findOne(){

    }
}
