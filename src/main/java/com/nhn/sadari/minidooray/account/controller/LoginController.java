package com.nhn.sadari.minidooray.account.controller;


import com.nhn.sadari.minidooray.account.domain.AccountInfo;
import com.nhn.sadari.minidooray.account.domain.AccountModifyRequest;
import com.nhn.sadari.minidooray.account.domain.CommonResponse;
import com.nhn.sadari.minidooray.account.domain.LoginRequest;
import com.nhn.sadari.minidooray.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;

    //로그인 요청 및 응답
    @GetMapping("/login")
    public CommonResponse<LoginRequest> getLoginRequest(@RequestParam String loginId) {
        LoginRequest loginRequest = accountService.getLoginInfo(loginId);

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(true)
                .resultCode(200)
                .resultMessage("로그인 요청 성공")
                .build();

        return CommonResponse.<LoginRequest>builder()
                .header(header)
                .result(Collections.singletonList(loginRequest))
                .build();
    }


    //인증 성공하면 가져올 계정 정보
    @GetMapping("/auth/{loginId}")
    public CommonResponse<AccountInfo> getAccountInfo(@PathVariable String loginId) {

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(true)
                .resultCode(200)
                .resultMessage("인증 성공 후 보내는 계정 정보")
                .build();

        return CommonResponse.<AccountInfo>builder()
                .header(header)
                .result(Collections.singletonList(accountService.getAccountInfo(loginId)))
                .build();

    }

}
