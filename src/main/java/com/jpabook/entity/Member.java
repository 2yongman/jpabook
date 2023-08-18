package com.jpabook.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicUpdate
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "NAME")
    private String username;

    private int age;

    @Builder
    public Member(Long id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public void updateAge(int age){
        this.age = age;
    }

    public void updateUsername(String name){
        this.username = name;
    }
}
