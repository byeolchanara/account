package com.nhn.sadari.minidooray.account.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public interface AccountGroup {
    Long getId();
    String getName();
    String getEmail();
}
