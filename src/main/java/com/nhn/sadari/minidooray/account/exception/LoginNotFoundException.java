package com.nhn.sadari.minidooray.account.exception;

public class LoginNotFoundException extends RuntimeException {
    private static final String MESSAGE="계정이 존재하지 않습니다. loginId : ";
    public LoginNotFoundException(String loginId){
        super(MESSAGE + loginId);
    }
}