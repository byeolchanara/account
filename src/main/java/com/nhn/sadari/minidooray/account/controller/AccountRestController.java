package com.nhn.sadari.minidooray.account.controller;

import com.nhn.sadari.minidooray.account.domain.AccountModifyRequest;
import com.nhn.sadari.minidooray.account.domain.AccountRegisterRequest;
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
    @PutMapping(value = "/{accountId}")
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
    public ResponseEntity<IdResponse> deleteProject(@PathVariable("accountId") Long accountId) {

        Long responseId = accountService.deleteAccount(accountId);
        IdResponse response = new IdResponse(responseId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //프로젝트 멤버 등록



    //프로젝트 멤버 수정

    //프로젝트 멤버 삭제

}
