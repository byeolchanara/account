package com.nhn.sadari.minidooray.account.controller;

import com.nhn.sadari.minidooray.account.domain.*;
import com.nhn.sadari.minidooray.account.exception.ValidationFailedException;
import com.nhn.sadari.minidooray.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@Slf4j
public class AccountRestController {

    private final AccountService accountService;

    //계정 생성
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = {"/", ""})
    public CommonResponse<IdResponse> createAccount(@RequestBody @Valid AccountRegisterRequest accountRegisterRequest,
                                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = accountService.createAccount(accountRegisterRequest);

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(true)
                .resultCode(201)
                .resultMessage("계정 등록 성공")
                .build();

        return CommonResponse.<IdResponse>builder()
                .header(header)
                .result(Collections.singletonList(new IdResponse(responseId)))
                .build();
    }

    //계정 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{accountId}")
    public CommonResponse<IdResponse> modifyAccount(@PathVariable("accountId") Long accountId, @RequestBody @Valid AccountModifyRequest accountModifyRequest,
                                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = accountService.modifyAccount(accountId, accountModifyRequest);

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(true)
                .resultCode(200)
                .resultMessage("계정 수정 성공")
                .build();

        return CommonResponse.<IdResponse>builder()
                .header(header)
                .result(Collections.singletonList(new IdResponse(responseId)))
                .build();
    }

    //계정 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{accountId}")
    public CommonResponse<IdResponse> deleteAccount(@PathVariable("accountId") Long accountId) {

        Long responseId = accountService.deleteAccount(accountId);

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(true)
                .resultCode(200)
                .resultMessage("계정 삭제 성공")
                .build();

        return CommonResponse.<IdResponse>builder()
                .header(header)
                .result(Collections.singletonList(new IdResponse(responseId)))
                .build();
    }

    // 계정 조회
    @GetMapping(value = "/{accountId}")
    public CommonResponse<AccountModifyRequest> getModifyRequest(@PathVariable Long accountId) {
        AccountModifyRequest accountModifyRequest = accountService.getAccountModify(accountId);

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(true)
                .resultCode(200)
                .resultMessage("계정 조회 성공")
                .build();

        return CommonResponse.<AccountModifyRequest>builder()
                .header(header)
                .result(Collections.singletonList(accountModifyRequest))
                .build();
    }


    //로그인아이디로 요청 및 응답
    @GetMapping
    public CommonResponse<LoginRequest> getLoginRequest(@RequestParam String loginId) {
        LoginRequest loginRequest = accountService.getLoginInfo(loginId);

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(true)
                .resultCode(200)
                .resultMessage("로그인을 위한 아이디 비밀번호 조회 성공")
                .build();

        return CommonResponse.<LoginRequest>builder()
                .header(header)
                .result(Collections.singletonList(loginRequest))
                .build();
    }


    //등록된 모든 계정 조회
    @GetMapping(value = "/")
    public CommonResponse<AccountGroup> getAccountGroups() {

        List<AccountGroup> accountGroups = accountService.getAccountGroups();

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(true)
                .resultCode(200)
                .resultMessage("등록된 모든 계정 조회")
                .build();

        return CommonResponse.<AccountGroup>builder()
                .header(header)
                .result(accountGroups)
                .build();
    }

}
