package com.nhn.sadari.minidooray.account.controller;

import com.nhn.sadari.minidooray.account.domain.IdResponse;
import com.nhn.sadari.minidooray.account.domain.AccountModifyRequest;
import com.nhn.sadari.minidooray.account.domain.AccountRegisterRequest;
import com.nhn.sadari.minidooray.account.enumclass.AuthorityType;
import com.nhn.sadari.minidooray.account.enumclass.MemberStatusType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountRestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("계정 생성")
    @Order(1)
    void testCreateProject() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        AccountRegisterRequest accountRegisterRequest = new AccountRegisterRequest("test1", "test2", "test3", "test4", AuthorityType.멤버);
        HttpEntity<AccountRegisterRequest> request = new HttpEntity<>(accountRegisterRequest, headers);

        ResponseEntity<IdResponse> result = testRestTemplate.postForEntity(
            "/api/accounts",
            request,
            IdResponse.class);

        IdResponse response = new IdResponse(1L);

        Assertions.assertThat(result.getBody()).isEqualTo(response);
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("계정 수정")
    @Order(2)
    void testModifyProject() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        AccountModifyRequest accountModifyRequest = new AccountModifyRequest("test1", "test2", "test3", "test4", MemberStatusType.휴면, AuthorityType.멤버);
        HttpEntity<AccountModifyRequest> request = new HttpEntity<>(accountModifyRequest, headers);

        ResponseEntity<IdResponse> result = testRestTemplate.exchange(
            "/api/accounts/{accountId}",
            HttpMethod.PUT,
            request,
            IdResponse.class,
            1L);

        IdResponse response = new IdResponse(1L);

        Assertions.assertThat(result.getBody()).isEqualTo(response);
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("계정 삭제")
    @Order(3)
    void testDeleteProject() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<IdResponse> result = testRestTemplate.exchange(
            "/api/accounts/{accountId}",
            HttpMethod.DELETE,
            requestEntity,
            IdResponse.class,
            1L
        );

        IdResponse response = new IdResponse(1L);

        Assertions.assertThat(result.getBody()).isEqualTo(response);
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}