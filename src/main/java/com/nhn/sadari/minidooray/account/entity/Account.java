package com.nhn.sadari.minidooray.account.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "status_id")
    MemberStatus memberStatus;


    @Builder
    public Account(Long id, String loginId, String password, String email, String name, LocalDateTime createdAt, MemberStatus memberStatus) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
        this.memberStatus = memberStatus;
    }

    public void modify(String loginId, String password, String email, String name, LocalDateTime createdAt, MemberStatus memberStatus) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
        this.memberStatus = memberStatus;
    }
}
