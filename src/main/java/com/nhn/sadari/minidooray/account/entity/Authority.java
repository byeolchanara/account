package com.nhn.sadari.minidooray.account.entity;

import javax.persistence.*;
import java.lang.reflect.Member;

@Entity
@Table(name = "Authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;
}
