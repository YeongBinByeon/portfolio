package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // 영속성 컨텍스트는 JPA를 이해하는데 가장 중요한 용어이다.
    // 엔티티를 영구 저장하는 환경 이라는 뜻
    // EntityManager.persist(entity); 으로 영속성 컨텍스트를 통해서 엔티티를 영속화 한다는 뜻이다.
    // 정홛히 말하면 persist() 시점에는 영속성 컨텍스트에 저장한다. DB 저장은 이후이다.
    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }


    // createQuery
    // 테이블이 아닌 객체를 대상으로 검색하는 객체지향 쿼리이다.
    // Native SQL으로 작성하면 SELECT * FROM 테이블명
    // JPQL으로 작성하면 SELECT m from Member m;
    // JPQL은 객체지향 쿼리이기 때문에 엔티티 클래스를 기반으로 쿼리를 작성해야 한다.
    // SELECT m from 테이블명 m; 으로 작성하면 JPQL로 작성된 쿼리는 DB에 질의할 때 SQL로 변환되기 때문에
    // 위 쿼리처럼 쓰면 테이블명으로 적힌 엔티티 객체를 찾을 수 없어서 에러가 발생한다.
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
