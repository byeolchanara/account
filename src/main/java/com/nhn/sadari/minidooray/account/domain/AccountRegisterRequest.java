package com.nhn.sadari.minidooray.account.domain;


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

}
