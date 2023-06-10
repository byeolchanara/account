package com.nhn.sadari.minidooray.account.entity;

import com.nhn.sadari.minidooray.account.enumclass.MemberStatusType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "member_status")
public class MemberStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MemberStatusType status;

}
