package com.nhn.sadari.minidooray.account.exception;

public class MemberStatusNotFoundException extends RuntimeException {
    private static final String MESSAGE="멤버 상태가 존재하지 않습니다. memberStatus : ";
    public MemberStatusNotFoundException(String memberStatus){
        super(MESSAGE + memberStatus);
    }
}