package com.nhn.sadari.minidooray.account.repository;

import com.nhn.sadari.minidooray.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
