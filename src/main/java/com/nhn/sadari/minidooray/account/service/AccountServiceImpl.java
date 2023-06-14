package com.nhn.sadari.minidooray.account.service;

import com.nhn.sadari.minidooray.account.domain.*;
import com.nhn.sadari.minidooray.account.entity.Account;
import com.nhn.sadari.minidooray.account.entity.MemberStatus;
import com.nhn.sadari.minidooray.account.enumclass.MemberStatusType;
import com.nhn.sadari.minidooray.account.exception.AccountAlreadyExistException;
import com.nhn.sadari.minidooray.account.exception.AccountNotFoundException;
import com.nhn.sadari.minidooray.account.exception.LoginNotFoundException;
import com.nhn.sadari.minidooray.account.repository.AccountRepository;
import com.nhn.sadari.minidooray.account.repository.MemberStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("accountService")
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final MemberStatusRepository memberStatusRepository;

    private Account getAccount(long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
        return account;
    }

    //계정 생성
    @Override
    @Transactional
    public Long createAccount(AccountRegisterRequest accountRegisterRequest) {

        accountRepository.getAccountByLoginId(accountRegisterRequest.getLoginId()).ifPresent(account -> {
            throw new AccountAlreadyExistException(accountRegisterRequest.getLoginId());
        });

        MemberStatus memberStatus = memberStatusRepository.findByStatus(MemberStatusType.가입);

        Account account = Account.builder()
                .name(accountRegisterRequest.getName())
                .loginId(accountRegisterRequest.getLoginId())
                .password(accountRegisterRequest.getPassword())
                .email(accountRegisterRequest.getEmail())
                .memberStatus(memberStatus)
                .createdAt(LocalDateTime.now())
                .build();


        accountRepository.save(account);

        return account.getId();
    }

    //계정 수정
    @Override
    @Transactional
    public Long modifyAccount(Long accountId, AccountModifyRequest accountModifyRequest) {
        Account account = getAccount(accountId);

        MemberStatusType memberStatusType = accountModifyRequest.getStatus();
        MemberStatus memberStatus = memberStatusRepository.findByStatus(memberStatusType);

        account.modify(accountModifyRequest.getLoginId(), accountModifyRequest.getPassword(), accountModifyRequest.getEmail(),
                accountModifyRequest.getName(), LocalDateTime.now(), memberStatus);


        accountRepository.save(account);

        return account.getId();
    }

    //계정 삭제
    @Override
    @Transactional
    public Long deleteAccount(Long accountId) {

        Account account = getAccount(accountId);
        accountRepository.delete(account);

        return account.getId();
    }

    //
    @Override
    public AccountModifyRequest getAccountModify(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new AccountNotFoundException(accountId)
        );
        return new AccountModifyRequest(
                account.getLoginId(),
                account.getPassword(),
                account.getEmail(),
                account.getName(),
                account.getMemberStatus().getStatus()
        );
    }

    @Override
    public LoginRequest getLoginInfo(String loginId) {
        Account account = accountRepository.getAccountByLoginId(loginId).orElseThrow(
                () -> new LoginNotFoundException(loginId)
        );

        return new LoginRequest(
                account.getLoginId(),
                account.getPassword()
        );
    }

    @Override
    public AccountInfo getAccountInfo(String loginId) {
        Account account = accountRepository.getAccountByLoginId(loginId).orElseThrow(
                () -> new LoginNotFoundException(loginId)
        );

        return new AccountInfo(
                account.getId(),
                account.getLoginId(),
                account.getName(),
                account.getMemberStatus().getStatus()
        );
    }

    @Override
    public List<AccountGroup> getAccountGroups() {
        List<AccountGroup> accountGroups = accountRepository.findAllBy();

        return accountGroups;
    }


}
