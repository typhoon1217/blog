# Typhoon's Blog REST API 명세서
<!--toc:start-->
- [Auth](#auth)
  - [로그인](#로그인)
  - [회원가입](#회원가입)
- [Board](#board)
  - [최신 게시물 리스트](#최신-게시물-리스트)
  - [주간 상위 3 게시물 리스트](#주간-상위-3-게시물-리스트)
  - [검색 게시물 리스트](#검색-게시물-리스트)
  - [특정 유저 게시물 리스트](#특정-유저-게시물-리스트)
  - [게시물 상세](#게시물-상세)
  - [좋아요 리스트](#좋아요-리스트)
  - [댓글 리스트](#댓글-리스트)
  - [게시물 작성](#게시물-작성)
  - [댓글 작성](#댓글-작성)
  - [게시물 수정](#게시물-수정)
  - [좋아요 기능](#좋아요-기능)
  - [게시물 삭제](#게시물-삭제)
- [Search](#search)
  - [인기 검색어 리스트](#인기-검색어-리스트)
  - [관련 검색어 리스트](#관련-검색어-리스트)
- [User](#user)
  - [유저 정보](#유저-정보)
  - [로그인 유저 정보](#로그인-유저-정보)
  - [닉네임 수정](#닉네임-수정)
  - [프로필 이미지 수정](#프로필-이미지-수정)
<!--toc:end-->
# Auth

---

## 로그인

### URL

POST /api/v1/auth/sign-in

### Header

### Requset

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| email | string / String | * | 이메일 |
| password | string / String | * | 패스워드 |

**Example**

```json
{
	"email": "email@email.com",
	"password": "P!ssw0rd"
}
```

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| token | string / String | * | JWT |
| expirationTime | number / int | * | 만료 시간 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
    "expirationTime": 3600
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request)

{
    "code": "VF",
    "message": "Validation failed."
}
```

```json
Http Status - 401 (Unauthorized)

{
    "code": "SF",
    "message": "Login information mismatch."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 회원가입

### URL

POST /api/v1/auth/sign-up

### Header

### Requset

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| email | string / String | * | 이메일 |
| password | string / String | * | 패스워드, 길이 8 ~ 20 |
| nickname | string / String | * | 닉네임 |
| telNumber | string / string | * | 휴대전화번호 (숫자로만 이루어) |
| address | string / String | * | 주소 |
| addressDetail | string | null / String |  | 상세주소 |
| agreedPersonal | boolean / Boolean | * | 개인정보동의여부 (true) |

**Example**

```json
{
	"email": "email@email.com",
	"password": "P!ssw0rd",
  "nickname": "typhoon",
  "telNumber": "01012345678",
  "address": "서울 특별시  역삼동 822-22 ",
  "addressDetail": "233동 1222호 ",
  "agreedPersonal": true
}
```

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success."
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request)

{
    "code": "VF",
    "message": "Validation failed."
}
```

```json
Http Status - 400 (Bad Request) 

{
    "code": "DE",
    "message": "Duplicate email."
}
```

```json
Http Status - 400 (Bad Request) 

{
    "code": "DN",
    "message": "Duplicate nickname."
}
```

```json
Http Status - 400 (Bad Request) 

{
    "code": "DT",
    "message": "Duplicate telephone number."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

# Board

---

## 최신 게시물 리스트

### URL

GET /api/v1/board/latest-list

### Header

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| latestList | BoardListItem[] | * | 게시물 리스트 아이템 |

BoardListItem

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| boardNumber | number / int | * | 게시물 번호 |
| title | string / String | * | 제목 |
| content | string / String | * | 내용 |
| boardTitleImage | string | null / String |  | 게시물 이미지 |
| favoriteCount | number / int | * | 좋아요 수 |
| commentCount | number / int | * | 댓글 수 |
| viewCount | number / int | * | 조회 수 |
| writeDatetime | string / String | * | 작성 날짜 및 시간 |
| writerNickname | string / String | * | 작성자 닉네임 |
| writerProfileImage | string | null / String |  | 작성자 프로필 이미지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
    "latestList": [
		  {
			  "boardNumber": 1,
			  "title": "간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집 추천 부탁 간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집",
			  "content": "우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 오늘 점심을 뭐먹을 지 너무 고민이 되는 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 ...",
			  "boardTitleImage": null,
			  "favoriteCount": 0,
			  "commentCount": 0,
			  "viewCount": 0,
			  "writeDatetime": "2023.08.18. 00:54:27",
			  "writerNickname": "안녕하세요나는타이푼",
			  "writerProfileImage": null,
			}
    ]
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 주간 상위 3 게시물 리스트

### URL

GET /api/v1/board/top-3

### Header

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| top3List | BoardListItem[] | * | 게시물 리스트 아이템 |

BoardListItem

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| boardNumber | number / int | * | 게시물 번호 |
| title | string / String | * | 제목 |
| content | string / String | * | 내용 |
| boardTitleImage | string | null / String |  | 게시물 이미지 |
| favoriteCount | number / int | * | 좋아요 수 |
| commentCount | number / int | * | 댓글 수 |
| viewCount | number / int | * | 조회 수 |
| writeDatetime | string / String | * | 작성 날짜 및 시간 |
| writerNickname | string / String | * | 작성자 닉네임 |
| writerProfileImage | string | null / String |  | 작성자 프로필 이미지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
    "top3List": [
		  {
			  "boardNumber": 1,
			  "title": "간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집 추천 부탁 간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집",
			  "content": "우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 오늘 점심을 뭐먹을 지 너무 고민이 되는 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 ...",
			  "boardTitleImage": null,
			  "favoriteCount": 0,
			  "commentCount": 0,
			  "viewCount": 0,
			  "writeDatetime": "2023.08.18. 00:54:27",
			  "writerNickname": "안녕하세요나는타이푼",
			  "writerProfileImage": null,
			}
    ]
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 검색 게시물 리스트

### URL

GET /api/v1/board/search-list/{searchWord}

GET /api/v1/board/search-list/{searchWord}/{preSearchWord}

### Header

### Request

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| searchList | BoardListItem[] | * | 게시물 리스트 아이템 |

BoardListItem

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| boardNumber | number / int | * | 게시물 번호 |
| title | string / String | * | 제목 |
| content | string / String | * | 내용 |
| boardTitleImage | string | null / String |  | 게시물 이미지 |
| favoriteCount | number / int | * | 좋아요 수 |
| commentCount | number / int | * | 댓글 수 |
| viewCount | number / int | * | 조회 수 |
| writeDatetime | string / String | * | 작성 날짜 및 시간 |
| writerNickname | string / String | * | 작성자 닉네임 |
| writerProfileImage | string | null / String |  | 작성자 프로필 이미지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
    "searchList": [
		  {
			  "boardNumber": 1,
			  "title": "간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집 추천 부탁 간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집",
			  "content": "우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 오늘 점심을 뭐먹을 지 너무 고민이 되는 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 ...",
			  "boardTitleImage": null,
			  "favoriteCount": 0,
			  "commentCount": 0,
			  "viewCount": 0,
			  "writeDatetime": "2023.08.18. 00:54:27",
			  "writerNickname": "안녕하세요나는타이푼",
			  "writerProfileImage": null,
			}
    ]
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 특정 유저 게시물 리스트

### URL

GET /api/v1/board/user-board-list/{email}

### Header

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| userBoardList | BoardListItem[] | * | 게시물 리스트 아이템 |

BoardListItem

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| boardNumber | number / int | * | 게시물 번호 |
| title | string / String | * | 제목 |
| content | string / String | * | 내용 |
| boardTitleImage | string | null / String |  | 게시물 이미지 |
| favoriteCount | number / int | * | 좋아요 수 |
| commentCount | number / int | * | 댓글 수 |
| viewCount | number / int | * | 조회 수 |
| writeDatetime | string / String | * | 작성 날짜 및 시간 |
| writerNickname | string / String | * | 작성자 닉네임 |
| writerProfileImage | string | null / String |  | 작성자 프로필 이미지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
    "userBoardList": [
		  {
			  "boardNumber": 1,
			  "title": "간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집 추천 부탁 간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집",
			  "content": "우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 오늘 점심을 뭐먹을 지 너무 고민이 되는 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 ...",
			  "boardTitleImage": null,
			  "favoriteCount": 0,
			  "commentCount": 0,
			  "viewCount": 0,
			  "writeDatetime": "2023.08.18. 00:54:27",
			  "writerNickname": "안녕하세요나는타이푼",
			  "writerProfileImage": null,
			}
    ]
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request)

{
    "code": "NU",
    "message": "This user does not exist."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 게시물 상세

### URL

GET /api/v1/board/{boardNumber}

### Header

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| boardNumber | number / int | * | 게시물 번호 |
| title | string / String | * | 제목 |
| content | string / String | * | 내용 |
| boardImageList | string[] / String[] | * | 게시물 이미지 리스트 |
| writeDatetime | string / String | * | 작성 날짜 및 시간 |
| writerEmail | string / String | * | 작성자 이메일 |
| writerNickname | string / String | * | 작성자 닉네임 |
| writerProfileImage | string | null / String |  | 작성자 프로필 이미지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
		"boardNumber": 1,
		"title": "간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집 추천 부탁 간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집",
		"content": "우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 오늘 점심을 뭐먹을 지 너무 고민이 되는 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 ...",
		"boardImageList": [],
		"writeDatetime": "2023.08.18. 00:54:27",
    "writerEmail": "email@email.com",
		"writerNickname": "안녕하세요나는타이푼",
		"writerProfileImage": null
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request)

{
    "code": "NB",
    "message": "This board does not exist."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 좋아요 리스트

### URL

GET /api/v1/board/{boardNumber}/favorite-list

### Header

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| favoriteList | FavoriteListItem[] | * | 게시물 리스트 아이템 |

FavoriteListItem

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| email | string / String | * | 유저 이메일 |
| nickname | string / String | * | 유저 닉네임 |
| profileImage | string | null / String |  | 프로필 이미지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
    "favoriteList": [
		  {
			  "email": "email@email.com",
			  "nickname": "안녕하세요나는타이푼",
			  "profileImage": null
			}
    ]
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request) 

{
    "code": "NB",
    "message": "This board does not exist."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 댓글 리스트

### URL

GET /api/v1/board/{boardNumber}/comment-list

### Header

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| commentList | CommentListItem[] | * | 게시물 리스트 아이템 |

CommentListItem

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| nickname | string / String | * | 유저 닉네임 |
| profileImage | string | null / String |  | 프로필 이미지 |
| writeDatetime | string / String | * | 작성 날짜 및 시 |
| content | string / String | * | 내 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
    "commentList": [
		  {
			  "nickname": "안녕하세요나는타이푼",
			  "profileImage": null,
        "writeDatetime": "2023.08.18. 01:13:12",
        "conetent": "우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 오늘 점심을 뭐먹을 지 너무 고민이 되는 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 ..."
			}
    ]
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request) 

{
    code: "NB",
    "message": "This board does not exist."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 게시물 작성

### URL

POST /api/v1/board

### Header

| Name | Value |
| --- | --- |
| Authorization | Bearer Token |

### Requset

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| title | string / String | * | 제목 |
| content | string / String | * | 내용 |
| boardImageList | string[] / String[] | * | 게시물 이미지 리스트 |

**Example**

```json
{
	"title": "간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집 추천 부탁 간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집",
	"content": "우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 오늘 점심을 뭐먹을 지 너무 고민이 되는 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 ...",
  "boardImageList": []
}
```

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success."
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request)

{
    "code": "VF",
    "message": "Validation failed."
}
```

```json
Http Status - 401 (Bad Request) 

{
    code: "NU",
    message: "This user does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    code: "AF",
    message: "Authorization Failed."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 댓글 작성

### URL

POST /api/v1/board/{boardNumber}/comment

### Header

| Name | Value |
| --- | --- |
| Authorization | Bearer Token |

### Requset

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| content | string / String | * | 내용 |

**Example**

```json
{
	"content": "우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 오늘 점심을 뭐먹을 지 너무 고민이 되는 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 ...",
}
```

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success."
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request)

{
    "code": "VF",
    "message": "Validation failed."
}
```

```json
Http Status - 400 (Bad Request) 

{
    "code": "NB",
    "message": "This board does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    "code": "NU",
    "message": "This user does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    "code": "AF",
    "message": "Authorization Failed."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 게시물 수정

### URL

PATCH /api/v1/board/{boardNumber}

### Header

| Name | Value |
| /--- | --- |
| Authorization | Bearer Token |

### Request

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| title | string / String | * | 제목 |
| content | string / String | * | 내용 |
| boardImageList | string[] / String[] | * | 게시물 이미지 리스트 |

**Example**

```json
{
	"title": "간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집 추천 부탁 간짜장 맛집을 찾고 있어요 꾸덕한 간짜장에 계란 후라이 올려주는 맛집",
	"content": "우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 오늘 점심을 뭐먹을 지 너무 고민이 되는 우리 동네에는 간짜장 맛집이 없어서 슬퍼요 간짜장을 먹고 싶어요 계란 후라이 올라간 꾸덕한 간짜장 나 점심때 ...",
  "boardImageList": []
}
```

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success."
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request)

{
    "code": "VF",
    "message": "Validation failed."
}
```

```json
Http Status - 400 (Bad Request) 

{
    "code": "NB",
    "message": "This board does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    "code": "NU",
    "message": "This user does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    "code": "AF",
    "message": "Authorization Failed."
}
```

```json
Http Status - 403 (Forbidden) 

{
    "code": "NP",
    "message": "Do not have permission."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 좋아요 기능

### URL

PUT /api/v1/board/{boardNumber}/favorite

### Header

| Name | Value |
| --- | --- |
| Authorization | Bearer Token |

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success."
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request)

{
    "code": "VF",
    "message": "Validation failed."
}
```

```json
Http Status - 400 (Bad Request) 

{
    "code": "NB",
    "message": "This board does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    "code": "NU",
    "message": "This user does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    "code": "AF",
    "message": "Authorization Failed."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 게시물 삭제

### URL

DELETE /api/v1/board/{boardNumber}

### Header

| Name | Value |
| --- | --- |
| Authorization | Bearer Token |

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success."
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request)

{
    "code": "VF",
    "message": "Validation failed."
}
```

```json
Http Status - 400 (Bad Request) 

{
    "code": "NB",
    "message": "This board does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
t    "code": "NU",
    "message": "This user does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    "code": "AF",
    "message": "Authorization Failed."
}
```

```json
Http Status - 403 (Forbidden) 

{
    "code": "NP",
    "message": "Do not have permission."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

# Search

---

## 인기 검색어 리스트

### URL

GET /api/v1/search/popular-list

### Header

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| popularWordList | string[] / String[] | * | 인기 검색어 리스트 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
    "popularWordList": ["아침", "점심", "저녁"]
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 관련 검색어 리스트

### URL

GET /api/v1/search/{searchWord}/relation-list

### Header

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| relativeWordList | string[] / String[] | * | 인기 검색어 리스트 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
    "relativeWordList": ["아침", "점심", "저녁"]
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

# User

---

## 유저 정보

### URL

GET /api/v1/user/{email}

### Header

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| email | string / String | * | 인기 검색어 리스트 |
| nickname | string / String | * | 닉네임 |
| profileImage | string |  null / String |  | 프로필 이미지 사진 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
    "email": "email@email.com",
    "nickname": "typhoon",
    "profileImage": null
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request) 

{
    code: "NU",
    message: "This user does not exist."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 로그인 유저 정보

### URL

GET /api/v1/user

### Header

| Name | Value |
| --- | --- |
| Authorization | Bearer Token |

### Requset

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |
| email | string / String | * | 인기 검색어 리스트 |
| nickname | string / String | * | 닉네임 |
| profileImage | string |  null / String |  | 프로필 이미지 사진 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success.",
    "email": "email@email.com",
    "nickname": "typhoon",
    "profileImage": null
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 401 (Unauthorized) 

{
    code: "NU",
    message: "This user does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    code: "AF",
    message: "Authorization Failed."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 닉네임 수정

### URL

PATCH /api/v1/user/nickname

### Header

| Name | Value |
| --- | --- |
| Authorization | Bearer Token |

### Requset

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| nickname | string / String | * | 닉네임 |

**Example**

```json
{
	"nickname": "아이셔"
}:
```

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success."
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 400 (Bad Request)

{
    "code": "VF",
    "message": "Validation failed."
}
```

```json
Http Status - 400 (Bad Request) 

{
    "code": "DN",
    "message": "Duplicated nickname."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    "code": "NU",
    "message": "This user does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    "code": "AF",
    "message": "Authorization Failed."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```

---

## 프로필 이미지 수정

### URL

PATCH /api/v1/user/profile-image

### Header

| Name | Value |
| --- | --- |
| Authorization | Bearer Token |

### Requset

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| profileImage | string | null / String |  | 프로필 이미지 |

**Example**

```json
{
	"profileImage": "https://i.namu.wiki/i/zxcfnA-iMbIFkXzfOn6W0tcWxgAEPpnbgdbRKKMD1xszloZ1DpeNdw7smhqna8YPeHxUikrEXd9cgTOEnx2UEl-gpUkUjgb4oSzIasnMrYnKGpm0txDfTn11JVfFeAnqPygXqtWgK9JNXEi6Z9IB3A.webp"
}
```

### Response

**Success**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 200 (OK)

{
    "code": "SU",
    "message": "Success."
}
```

**Fail**

| Name | Type | Required | Description |
| --- | --- | --- | --- |
| code | string / String | * | 코드 |
| message | string / String | * | 메세지 |

**Example**

```json
Http Status - 401 (Unauthorized) 

{
    "code": "NU",
    "message": "This user does not exist."
}
```

```json
Http Status - 401 (Unauthorized) 

{
    "code": "AF",
    "message": "Authorization Failed."
}
```

```json
Http Status - 500 (Internal Server Error)

{
    "code": "DBE",
    "message": "Database error."
}
```
