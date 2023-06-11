package com.nhn.sadari.minidooray.account.controller;

import com.nhn.sadari.minidooray.account.domain.AccountModifyRequest;
import com.nhn.sadari.minidooray.account.domain.AccountRegisterRequest;
import com.nhn.sadari.minidooray.account.domain.LoginRequest;
import com.nhn.sadari.minidooray.account.service.AccountService;
import com.nhn.sadari.minidooray.account.domain.IdResponse;
import com.nhn.sadari.minidooray.account.exception.ValidationFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@Slf4j
public class AccountRestController {

    private final AccountService accountService;

    //계정 생성
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = {"/", ""})
    public ResponseEntity<IdResponse> createAccount(@RequestBody @Valid AccountRegisterRequest accountRegisterRequest,
                                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = accountService.createAccount(accountRegisterRequest);
        IdResponse response = new IdResponse(responseId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //계정 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/modify/{accountId}")
    public ResponseEntity<IdResponse> modifyAccount(@PathVariable("accountId") Long accountId, @RequestBody @Valid AccountModifyRequest accountModifyRequest,
                                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        Long responseId = accountService.modifyAccount(accountId, accountModifyRequest);
        IdResponse response = new IdResponse(responseId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //계정 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{accountId}")
    public ResponseEntity<IdResponse> deleteAccount(@PathVariable("accountId") Long accountId) {

        Long responseId = accountService.deleteAccount(accountId);
        IdResponse response = new IdResponse(responseId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 프로젝트 멤버 수정 조회
    @GetMapping(value = "/modify/{accountId}")
    public ResponseEntity<AccountModifyRequest> getModifyRequest(@PathVariable Long accountId) {
        AccountModifyRequest accountModifyRequest = accountService.getAccountModify(accountId);

        return new ResponseEntity<>(accountModifyRequest, HttpStatus.OK);
    }


    //로그인아이디로 요청 및 응답
    @GetMapping
    public ResponseEntity<LoginRequest> getLoginRequest(@RequestParam String loginId) {
        LoginRequest loginRequest = accountService.getLoginInfo(loginId);

        return new ResponseEntity<>(loginRequest, HttpStatus.OK);
    }

}
