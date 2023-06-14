package com.nhn.sadari.minidooray.account.service;

import com.nhn.sadari.minidooray.account.domain.AccountRegisterRequest;
import com.nhn.sadari.minidooray.account.entity.Account;
import com.nhn.sadari.minidooray.account.entity.MemberStatus;
import com.nhn.sadari.minidooray.account.enumclass.MemberStatusType;
import com.nhn.sadari.minidooray.account.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;


class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

    public AccountServiceTest() {
        initMocks(this);
    }

    @Test
    void registerRequestAccount() {
        String testId = "testId";
        String testPassword = "testPassword";
        String testEmail = "testEmail";
        String testName = "testName";
        Long expectedId = 1L;

        AccountRegisterRequest accountRegisterRequest = new AccountRegisterRequest(testId, testPassword, testEmail, testName);

        MemberStatus memberStatus = new MemberStatus(
                1L, MemberStatusType.가입);

        Account account = Account.builder()
                .id(expectedId)
                .loginId(accountRegisterRequest.getLoginId())
                .email(accountRegisterRequest.getEmail())
                .password(accountRegisterRequest.getPassword())
                .name(accountRegisterRequest.getName())
                .memberStatus(memberStatus)
                .createdAt(LocalDateTime.now())
                .build();


        given(accountRepository.save(any(Account.class))).willReturn(account);

        Long actual = accountService.createAccount(accountRegisterRequest);

        assertThat(account.getId()).isEqualTo(expectedId);
        assertThat(actual).isEqualTo(expectedId);

    }


    @Test
    void deleteAccount() {
    }

    @Test
    void getAccountInfo() {
    }

    @Test
    void getAccountList() {
    }

    @Test
    void doAccountUpdate() {
    }

    @Test
    void getAccountRedis() {
    }

    @Test
    void getLoginInfo() {
    }
}