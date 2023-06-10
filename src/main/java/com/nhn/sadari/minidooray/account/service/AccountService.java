package com.nhn.sadari.minidooray.account.service;

import com.nhn.sadari.minidooray.account.domain.AccountModifyRequest;
import com.nhn.sadari.minidooray.account.domain.AccountRegisterRequest;
import com.nhn.sadari.minidooray.account.domain.LoginRequest;

public interface AccountService {

    Long createAccount(AccountRegisterRequest accountRegisterRequest);

    Long modifyAccount(Long accountId, AccountModifyRequest accountModifyRequest);

    Long deleteAccount(Long accountId);

    AccountModifyRequest getAccountModify(Long accountId);

    LoginRequest getLoginInfo(String loginId);
}
