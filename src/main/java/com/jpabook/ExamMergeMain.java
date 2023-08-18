package com.jpabook;

import com.jpabook.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        Member member = createMember(1L, "회원1");
        member.updateUsername("회원명 변경");

        mergeMember(member);
    }

    static Member createMember(Long id, String name){
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = Member.builder()
                .id(id)
                .username(name)
                .build();

        em1.persist(member);
        tx1.commit();
        emf.close();
        return member;

    }

    static void mergeMember(Member member){
        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        tx2.begin();
        Member mergeMember = em2.merge(member);
        tx2.commit();

        //준영속상태
        System.out.println("member = " + member.getUsername()); //회원명 변경

        //영속 상태
        System.out.println("mergeMember = " + mergeMember.getUsername()); //회원명 변경

        System.out.println("em2 contains member = " + em2.contains(member)); //false

        System.out.println("em2 contains mergeMember = " + em2.contains(mergeMember)); //tre

        em2.close();
    }
}
