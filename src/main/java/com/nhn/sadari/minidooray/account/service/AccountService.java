package com.nhn.sadari.minidooray.account.service;

import com.nhn.sadari.minidooray.account.domain.*;

import java.util.List;

public interface AccountService {

    Long createAccount(AccountRegisterRequest accountRegisterRequest);

    Long modifyAccount(Long accountId, AccountModifyRequest accountModifyRequest);

    Long deleteAccount(Long accountId);

    AccountModifyRequest getAccountModify(Long accountId);

    LoginRequest getLoginInfo(String loginId);

    AccountInfo getAccountInfo(String loginId);

    List<AccountGroup> getAccountGroups();
}
