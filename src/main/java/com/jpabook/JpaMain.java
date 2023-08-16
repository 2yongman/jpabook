package com.jpabook;

import com.jpabook.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(EntityManager em){
        Long memberID = 1L;
        Member member = Member.builder()
                .id(memberID)
                .username("지한")
                .age(2)
                .build();

        em.persist(member);

        //수정
        member.updateAge(5);

        Member findMember = em.find(Member.class, memberID);

        //목록
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        em.remove(member);

    }
}
