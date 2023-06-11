package com.nhn.sadari.minidooray.account.controller;


import com.nhn.sadari.minidooray.account.domain.AccountInfo;
import com.nhn.sadari.minidooray.account.domain.LoginRequest;
import com.nhn.sadari.minidooray.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;

    //로그인 요청 및 응답
    @GetMapping("/login")
    public ResponseEntity<LoginRequest> getLoginRequest(@RequestParam String loginId) {
        LoginRequest loginRequest = accountService.getLoginInfo(loginId);

        return new ResponseEntity<>(loginRequest, HttpStatus.OK);
    }


    //인증 성공하면 가져올 계정 정보
    @GetMapping("/auth/{loginId}")
    public ResponseEntity<AccountInfo> getAccountInfo(@PathVariable String loginId) {

       return new ResponseEntity<>(accountService.getAccountInfo(loginId), HttpStatus.OK);
    }

}
