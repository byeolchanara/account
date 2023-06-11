package com.nhn.sadari.minidooray.account.domain;

import com.nhn.sadari.minidooray.account.enumclass.MemberStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {
    String loginId;
    String password;

}
