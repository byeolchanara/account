package com.nhn.sadari.minidooray.account.service;

import com.nhn.sadari.minidooray.account.domain.AccountModifyRequest;
import com.nhn.sadari.minidooray.account.domain.AccountRegisterRequest;
import com.nhn.sadari.minidooray.account.entity.Account;
import com.nhn.sadari.minidooray.account.entity.MemberStatus;
import com.nhn.sadari.minidooray.account.enumclass.MemberStatusType;
import com.nhn.sadari.minidooray.account.exception.AccountNotFoundException;
import com.nhn.sadari.minidooray.account.repository.AccountRepository;
import com.nhn.sadari.minidooray.account.repository.MemberStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
        Account account = new Account();
        account.setLoginId(accountRegisterRequest.getLoginId());
        account.setPassword(accountRegisterRequest.getPassword());
        account.setEmail(accountRegisterRequest.getEmail());
        account.setName(accountRegisterRequest.getName());

        account.setCreatedAt(LocalDateTime.now());

        MemberStatus memberStatus = memberStatusRepository.findByStatus(MemberStatusType.가입);
        account.setMemberStatus(memberStatus);

        accountRepository.save(account);

        return account.getId();
    }

    //계정 수정
    @Override
    @Transactional
    public Long modifyAccount(Long accountId, AccountModifyRequest accountModifyRequest) {
        Account account = getAccount(accountId);
        account.setLoginId(accountModifyRequest.getLoginId());
        account.setPassword(accountModifyRequest.getPassword());
        account.setEmail(accountModifyRequest.getEmail());
        account.setName(accountModifyRequest.getName());

        MemberStatusType memberStatusType = accountModifyRequest.getStatus();
        MemberStatus memberStatus = memberStatusRepository.findByStatus(memberStatusType);
        account.setMemberStatus(memberStatus);

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


}
