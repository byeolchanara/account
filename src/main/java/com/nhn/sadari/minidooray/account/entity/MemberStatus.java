package com.nhn.sadari.minidooray.account.entity;

import javax.persistence.*;

@Entity
@Table(name = "MemberStatus")
public class MemberStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;
}
