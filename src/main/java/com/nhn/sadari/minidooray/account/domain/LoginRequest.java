package com.nhn.sadari.minidooray.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {
    String loginId;
    String password;

}
