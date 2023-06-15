package com.nhn.sadari.minidooray.account.service;

import com.nhn.sadari.minidooray.account.domain.*;
import com.nhn.sadari.minidooray.account.entity.Account;
import com.nhn.sadari.minidooray.account.entity.MemberStatus;
import com.nhn.sadari.minidooray.account.enumclass.MemberStatusType;
import com.nhn.sadari.minidooray.account.repository.AccountRepository;
import com.nhn.sadari.minidooray.account.repository.MemberStatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private MemberStatusRepository memberStatusRepository;
    @Mock
    private AccountModifyRequest accountModifyRequest;
    @Mock
    private LoginRequest loginRequest;
    @Mock
    private AccountGroup accountGroup;
    @InjectMocks
    private AccountServiceImpl accountService;

    public AccountServiceTest() {
        initMocks(this);
    }

    //계정 생성 테스트
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

    //계정 수정 테스트
    @Test
    void modifyAccount() {
        String testId = "testId";
        String testPassword = "testPassword";
        String testEmail = "testEmail";
        String testName = "testName";
        Long expectedId = 1L;

        MemberStatusType memberStatusType = accountModifyRequest.getStatus();

        AccountModifyRequest accountModifyRequest = new AccountModifyRequest(testId, testPassword, testEmail, testName, memberStatusType);

        MemberStatus memberStatus = new MemberStatus(
                1L, MemberStatusType.가입);

        Account account = Account.builder()
                .id(expectedId)
                .loginId(accountModifyRequest.getLoginId())
                .email(accountModifyRequest.getEmail())
                .password(accountModifyRequest.getPassword())
                .name(accountModifyRequest.getName())
                .memberStatus(memberStatus)
                .createdAt(LocalDateTime.now())
                .build();

        given(accountRepository.findById(expectedId)).willReturn(Optional.ofNullable(account));
        given(accountRepository.save(any(Account.class))).willReturn(account);

        Long actual = accountService.modifyAccount(expectedId, accountModifyRequest);

        assertThat(account.getId()).isEqualTo(expectedId);
        assertThat(actual).isEqualTo(expectedId);
    }


    //계정 삭제 테스트
    @Test
    void deleteAccount() {
        Long expectedId = 1L;

        Account account = new Account(expectedId, "test", "test", "test@gmail.com", "Testname", LocalDateTime.now(), memberStatusRepository.findByStatus(MemberStatusType.가입));

        given(accountRepository.findById(expectedId)).willReturn(Optional.of(account));

        Long actual = accountService.deleteAccount(expectedId);

        assertThat(actual).isEqualTo(expectedId);
    }

    //계정 조회 테스트
    @Test
    void getAccountModify() {
        String testId = "testId";
        String testPassword = "testPassword";
        String testEmail = "testEmail";
        String testName = "testName";
        Long expectedId = 1L;

        MemberStatusType memberStatusType = accountModifyRequest.getStatus();
        AccountModifyRequest accountModifyRequest = new AccountModifyRequest(testId, testPassword, testEmail, testName, memberStatusType);

        MemberStatus memberStatus = new MemberStatus(
                1L, MemberStatusType.가입);

        Account account = Account.builder()
                .id(expectedId)
                .loginId(accountModifyRequest.getLoginId())
                .email(accountModifyRequest.getEmail())
                .password(accountModifyRequest.getPassword())
                .name(accountModifyRequest.getName())
                .memberStatus(new MemberStatus(1L, memberStatusType))
                .createdAt(LocalDateTime.now())
                .build();

        given(accountRepository.findById(expectedId)).willReturn(Optional.of(account));

        AccountModifyRequest result = accountService.getAccountModify(expectedId);


        assertThat(result.getLoginId()).isEqualTo(accountModifyRequest.getLoginId());
        assertThat(result.getPassword()).isEqualTo(accountModifyRequest.getPassword());
        assertThat(result.getEmail()).isEqualTo(accountModifyRequest.getEmail());
        assertThat(result.getName()).isEqualTo(accountModifyRequest.getName());
        assertThat(result.getStatus()).isEqualTo(accountModifyRequest.getStatus());
    }

    @Test
    void getLoginInfo() {
        String testId = "testId";
        String testPassword = "testPassword";

        LoginRequest loginRequest = new LoginRequest(testId, testPassword);

        Account account = Account.builder()
                .loginId(testId)
                .password(testPassword)
                .build();

        given(accountRepository.getAccountByLoginId(testId)).willReturn(Optional.of(account));

        LoginRequest actual = accountService.getLoginInfo(testId);

        assertThat(actual.getLoginId()).isEqualTo(testId);
        assertThat(actual.getPassword()).isEqualTo(testPassword);
    }

    @Test
    void getAccountInfo() {
        Long testaccountId = 2L;
        String testId = "testId";
        String testName = "testName";
        Long expectedId = 1L;

        MemberStatusType memberStatusType = accountModifyRequest.getStatus();
        AccountInfo accountInfo = new AccountInfo(testaccountId, testId, testName, memberStatusType);
    }

    @Test
    void getAccountGroups() {
        Account account1 = Account.builder()
                .id(1L)
                .password("testPassword1")
                .email("testEmail1")
                .name("testName1")
                .build();
        Account account2 = Account.builder()
                .id(2L)
                .password("testPassword2")
                .email("testEmail2")
                .name("testName2")
                .build();

        List<Account> accounts = Arrays.asList(account1, account2);

        given(accountRepository.findAllBy()).willReturn(accounts);

        List<AccountGroup> result = accountService.getAccountGroups();

        Assertions.assertEquals(result.size(), 2);

        AccountGroup expectedAccountGroup1 = AccountGroup.builder()
                .id(1L)
                .name("testName1")
                .email("testEmail1")
                .build();
        AccountGroup expectedAccountGroup2 = AccountGroup.builder()
                .id(2L)
                .name("testName2")
                .email("testEmail2")
                .build();

        assertThat(account1).isEqualTo(expectedAccountGroup1);
        assertThat(account2).isEqualTo(expectedAccountGroup2);

    }
}