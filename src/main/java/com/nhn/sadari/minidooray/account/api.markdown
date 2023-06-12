# GET /api/accounts/{id}
### 아이디 비밀번호 요청

---
# POST /api/accounts
### 계정 생성 요청
## Response
#### body
```
{
  "loginId": "{loginId}",
  "password": "{password}",
  "email": "{email}",
  "name": "{name}"
}
```

---
# GET /api/login?loginId=test
### 로그인
## Response
#### body
```
{
  "loginId": "{loginId}",
  "password": "{password}"
}
```

---
# GET /api/auth/test
### 로그인후 요청

---
# GET /api/accounts/modify/6
### 수정할 계정 조회

---
# PUT /api/accounts/modify/6
### 계정 수정 요청
## Response
#### body
```
{
  "loginId": "{loginId}",
  "password": "{password}",
  "email": "{email}",
  "name": "{name}",
  "status": "{status}"
}
```

---
# DELETE /api/accounts/{id}
### 계정 삭제 요청


---
***
# POST /api/projects
### 프로젝트 등록



