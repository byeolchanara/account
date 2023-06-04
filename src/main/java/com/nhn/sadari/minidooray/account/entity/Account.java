package com.nhn.sadari.minidooray.account.entity;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    private String email;

    private String name;

    @OneToOne
    @JoinColumn(name = "status_id")
    MemberStatus memberStatus;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    Authority authority;


}
