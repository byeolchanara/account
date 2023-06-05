package com.nhn.sadari.minidooray.account.repository;

import com.nhn.sadari.minidooray.account.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
