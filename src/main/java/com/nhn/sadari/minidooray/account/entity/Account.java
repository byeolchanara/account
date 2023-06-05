package com.nhn.sadari.minidooray.account.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String name;

    @OneToOne
    @JoinColumn(name = "status_id")
    MemberStatus memberStatus;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    Authority authority;


}
