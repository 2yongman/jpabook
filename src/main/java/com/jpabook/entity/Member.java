package com.jpabook.entity;

import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.*;

@Getter(AccessLevel.PROTECTED)
@Table(name = "MEMBER")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "NAME")
    private String username;

    private int age;

}
