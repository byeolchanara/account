package com.nhn.sadari.minidooray.account.exception;

public class AccountAlreadyExistException extends RuntimeException {
    private static final String MESSAGE="계정이 이미 존재합니다. loginId : ";
    public AccountAlreadyExistException(String loginId){
        super(MESSAGE + loginId);
    }
}