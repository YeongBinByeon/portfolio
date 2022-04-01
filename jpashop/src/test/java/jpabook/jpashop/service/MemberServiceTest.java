package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void  회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");
        member.setAddress(new Address("city", "Street","zipcode"));

        //when
        Long savedId = memberService.join(member);

        //then
        //em.flush();
        Assert.assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        Member member1 = new Member();
        member1.setName("kim");

        //when
        memberService.join(member);
        memberService.join(member1);


        //then
        Assert.fail("예외가 발생해야 한다.");
    }

}