package com.nhn.sadari.minidooray.account.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccountGroup {
    Long id;
    String name;
    String email;

    @Builder
    public AccountGroup(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

