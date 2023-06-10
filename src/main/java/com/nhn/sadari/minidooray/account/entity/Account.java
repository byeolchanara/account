package com.nhn.sadari.minidooray.account.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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
    //@NotBlank(message = "비밀번호를 입력해주세요.")
    //@Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String password;

    @NotNull
    //@Email
    //@NotBlank(message = "이메일 주소를 입력해주세요.")
    private String email;

    @NotNull
    //@NotBlank(message = "닉네임을 입력해주세요.")
    private String name;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "status_id")
    MemberStatus memberStatus;

}
