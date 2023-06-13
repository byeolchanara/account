# POST /api/accounts
### 계정 생성 요청
## Response
#### body
```
{
    "loginId": "",
    "password": "",
    "email": "",
    "name": ""
}
```

---
# GET /api/login?loginId={loginId}
### 로그인
## Response
#### body
```
{
    "loginId": "",
    "password": ""
}
```

---
# GET /api/auth/test
### 로그인후 요청

---
# GET /api/accounts/modify/{accountId}
### 수정할 계정 조회

---
# PUT /api/accounts/modify/{accountId}
### 계정 수정 요청
## Response
#### body
```
{
    "loginId": "",
    "password": "",
    "email": "",
    "name": "",
    "status": ""
}
```

---
# DELETE /api/accounts/{id}
### 계정 삭제 요청

---
# GET /api/accounts/group
### 모든 계정 조회
---
***





---
***
# POST /api/projects
### 프로젝트 등록
## Response
#### body
```
{
    "name": "",
    "description": "",
    "memberId": "",
    "memberName": ""
}
```

---
# PUT /api/projects/{projectId}
### 프로젝트 수정 (기본설정)
## Response
#### body
```
{
    "status": "",
    "name": "",
    "memberId": "",
    "memberName": ""
}
```

---
# DELETE /api/projects/{projectId}
### 프로젝트 삭제

---
# POST /api/projects/{projectId}/members
### 프로젝트 멤버 등록

```
{
    "memberId": "",
    "memberName": "",
    "role": ""
}
```

---
# PUT /api/projects/{projectId}/members/{memberId}
### 프로젝트 멤버 역할 수정

```
{
    "role": ""
}
```

---
# DELETE /api/projects/{projectId}/members/{memberId}
### 프로젝트 멤버 삭제

---
# GET /api/projects/members/{memberId}
### 멤버 아이다로 프로젝트 리스트 조회

---
# GET /api/projects/{projectId}
### 프로젝트 아이디로 프로젝트 조회

---
# GET /api/projects/{projectId}/members
### 프로젝트 아이디로 프로젝트 멤버 조회

---
***
# POST /api/projects/{projectId}/tasks
### 업무 등록
```
{
    "title": "",
    "content": "",
    "writerId": "",
    "endDate": "",
    "memberIds": "",
    "milestoneId": "",
    "tagIds": ""
}
```

---
# PUT /api/projects/{projectId}/tasks/{taskId}
### 업무 수정

```
{
    "title": "",
    "content": "",
    "endDate": "",
    "memberIds": "",
    "milestoneId": "",
    "tagIds": ""
}
```

---
# DELETE api/projects/{projectId}/tasks/{taskId}
### 업무 삭제

---
***
# POST /api/tasks/{tasksId}/comments
### 댓글 등록

```
{
    "writerId": "",
    "contents": ""
}
```

---
# PUT /api/tasks/{tasksId}/comments/{commentId}
### 댓글 수정

```
{
    "contents": ""
}
```

---
# DELETE /api/tasks/{tasksId}/comments/{commentId}
### 댓글 삭제

---
***
# POST /api/projects/{projectId}/milestones
### 마일스톤 등록

```
{
    "name": "",
    "startDate": "",
    "endDate": ""
}
```

---
# PUT /api/projects/{projectId}/milestones/{milestoneId}
### 마일스톤 수정

```
{
    "name": "",
    "startDate": "",
    "endDate": ""
}
```

---
# DELETE /api/projects/{projectId}/milestones/{milestoneId}
### 마일스톤 삭제

---
***
# POST /api/projects/{projectId}/tags
### 태그 등록

```
{
    "name": ""
}
```

---
# PUT /api/projects/{projectId}/tags/{tagId}
### 태그 수정
```
{
    "name": ""
}
```

---
# DELETE /api/projects/{projectId}/tags/{tagId}
### 태그 삭제








