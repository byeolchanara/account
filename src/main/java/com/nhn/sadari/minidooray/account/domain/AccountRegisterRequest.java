package com.nhn.sadari.minidooray.account.domain;

import com.nhn.sadari.minidooray.account.enumclass.AuthorityType;
import com.nhn.sadari.minidooray.account.enumclass.MemberStatusType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRegisterRequest {
    @NotNull
    private String loginId;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private MemberStatusType memberStatus;

    @NotNull
    private AuthorityType authority;
}
