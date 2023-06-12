package com.nhn.sadari.minidooray.account.advice;

import com.nhn.sadari.minidooray.account.domain.CommonResponse;
import com.nhn.sadari.minidooray.account.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class CommonRestControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    //400 Bad Request
    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentNotValidException.class,
        ValidationFailedException.class})
    public CommonResponse<Void> missingParameter(Exception exception) {

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(false)
                .resultCode(HttpStatus.BAD_REQUEST.value())
                .resultMessage(exception.getMessage())
                .build();

        return CommonResponse.<Void>builder()
                .header(header)
                .result(null)
                .build();
    }

    //404 Not Found
    @ExceptionHandler({NoHandlerFoundException.class, MemberStatusNotFoundException.class, AccountNotFoundException.class, LoginNotFoundException.class})
    public CommonResponse<Void> eventNotFoundException(Exception exception) {

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(false)
                .resultCode(HttpStatus.NOT_FOUND.value())
                .resultMessage(exception.getMessage())
                .build();

        return CommonResponse.<Void>builder()
                .header(header)
                .result(null)
                .build();
    }

    //405 Method Not Allowed
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public CommonResponse<Void> methodNotAllowed(Exception exception, HttpServletRequest request) {

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(false)
                .resultCode(HttpStatus.METHOD_NOT_ALLOWED.value())
                .resultMessage(exception.getMessage())
                .build();

        return CommonResponse.<Void>builder()
                .header(header)
                .result(null)
                .build();
    }


    //500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public CommonResponse<Void> internalServerError(Exception exception) {
        log.info("error : {}", exception);

        CommonResponse.Header header = CommonResponse.Header.builder()
                .isSuccessful(false)
                .resultCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .resultMessage(exception.getMessage())
                .build();

        return CommonResponse.<Void>builder()
                .header(header)
                .result(null)
                .build();
    }

}
