package com.nhn.sadari.minidooray.account.exception;

public class AccountNotFoundException extends RuntimeException {
    private static final String MESSAGE="계정이 존재하지 않습니다. accountId : ";
    public AccountNotFoundException(long accountId){
        super(MESSAGE + accountId);
    }
}