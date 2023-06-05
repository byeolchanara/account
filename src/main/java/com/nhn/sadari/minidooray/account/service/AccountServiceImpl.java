package com.nhn.sadari.minidooray.account.service;

import com.nhn.sadari.minidooray.account.domain.AccountModifyRequest;
import com.nhn.sadari.minidooray.account.domain.AccountRegisterRequest;
import com.nhn.sadari.minidooray.account.entity.Account;
import com.nhn.sadari.minidooray.account.entity.Authority;
import com.nhn.sadari.minidooray.account.entity.MemberStatus;
import com.nhn.sadari.minidooray.account.enumclass.AuthorityType;
import com.nhn.sadari.minidooray.account.enumclass.MemberStatusType;
import com.nhn.sadari.minidooray.account.exception.AccountNotFoundException;
import com.nhn.sadari.minidooray.account.repository.AccountRepository;
import com.nhn.sadari.minidooray.account.repository.AuthorityRepository;
import com.nhn.sadari.minidooray.account.repository.MemberStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final MemberStatusRepository memberStatusRepository;

    private Account getAccount(long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
        return account;
    }

    @Override
    @Transactional
    public Long createAccount(AccountRegisterRequest accountRegisterRequest) {
        Account account = new Account();
        account.setLoginId(accountRegisterRequest.getLoginId());
        account.setPassword(accountRegisterRequest.getPassword());
        account.setEmail(accountRegisterRequest.getEmail());
        account.setName(accountRegisterRequest.getName());

        MemberStatus memberStatus = memberStatusRepository.findByStatus(MemberStatusType.가입);
        account.setMemberStatus(memberStatus);

        accountRepository.save(account);

        Authority authority = new Authority();
        authority.setAuthority(AuthorityType.관리자);

        authorityRepository.save(authority);

        return account.getId();
    }

    @Override
    @Transactional
    public Long modifyAccount(Long accountId, AccountModifyRequest accountModifyRequest) {
        Account account = getAccount(accountId);
        account.setLoginId(accountModifyRequest.getLoginId());
        account.setPassword(accountModifyRequest.getPassword());
        account.setEmail(accountModifyRequest.getEmail());
        account.setName(accountModifyRequest.getName());

        MemberStatusType memberStatusType = accountModifyRequest.getMemberStatus();
        MemberStatus memberStatus = memberStatusRepository.findByStatus(memberStatusType);
        account.setMemberStatus(memberStatus);

        accountRepository.save(account);

        return account.getId();
    }

    @Override
    @Transactional
    public Long deleteAccount(Long accountId) {

        Account account = getAccount(accountId);
        accountRepository.delete(account);

        return account.getId();
    }

}
