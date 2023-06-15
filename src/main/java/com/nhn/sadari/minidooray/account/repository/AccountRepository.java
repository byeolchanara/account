package com.nhn.sadari.minidooray.account.repository;

import com.nhn.sadari.minidooray.account.domain.AccountGroup;
import com.nhn.sadari.minidooray.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> getAccountByLoginId(String loginId);

    List<Account> findAllBy();
}
