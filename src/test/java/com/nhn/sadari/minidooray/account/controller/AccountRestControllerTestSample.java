package com.nhn.sadari.minidooray.account.controller;

import com.nhn.sadari.minidooray.account.domain.AccountRegisterRequest;
import com.nhn.sadari.minidooray.account.domain.IdResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountRestControllerTestSample {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("계정 생성")
    void testCreateProject() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        AccountRegisterRequest accountRegisterRequest = new AccountRegisterRequest("test11", "test22", "test33@naver.com", "test44");
        HttpEntity<AccountRegisterRequest> request = new HttpEntity<>(accountRegisterRequest, headers);

        ResponseEntity<IdResponse> result = testRestTemplate.postForEntity(
                "/api/accounts",
                request,
                IdResponse.class);

        IdResponse response = new IdResponse(1L);

        Assertions.assertThat(result.getBody()).isEqualTo(response);
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
