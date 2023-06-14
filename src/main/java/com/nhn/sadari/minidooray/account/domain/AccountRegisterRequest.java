package com.nhn.sadari.minidooray.account.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRegisterRequest {
    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

}
