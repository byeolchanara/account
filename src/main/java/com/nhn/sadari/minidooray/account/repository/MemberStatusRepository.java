package com.nhn.sadari.minidooray.account.repository;

import com.nhn.sadari.minidooray.account.entity.MemberStatus;
import com.nhn.sadari.minidooray.account.enumclass.MemberStatusType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStatusRepository extends JpaRepository<MemberStatus, Long> {
    MemberStatus findByStatus(MemberStatusType memberStatusType);
}
