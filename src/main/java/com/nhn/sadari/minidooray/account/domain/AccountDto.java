package com.nhn.sadari.minidooray.account.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public interface AccountDto {
    Long getId();

    String getLoginId();

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    String getPassword();
}
