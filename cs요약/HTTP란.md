# HTTP와 RESTful API

## HTTP란?

HTTP(Hypertext Transfer Protocol)는 웹 상에서 클라이언트와 서버 간 데이터를 주고받는 데 사용되는 통신 규약입니다.

* **요청-응답 방식**: 클라이언트가 서버에 요청을 보내고, 서버가 이에 대한 응답을 반환하는 방식으로 동작합니다.
* **비연결성(Stateless)**: 한 번의 요청-응답이 끝나면 연결이 종료됩니다.
* **전송 방식**: TCP 연결을 통해 데이터를 안전하게 주고받습니다.
* **데이터 포맷**: HTML, JSON 등 다양한 포맷을 지원합니다.
* **구성 요소**: URL 경로, HTTP 메서드, 상태 코드, 헤더 등을 포함합니다.

## HTTPS란?

HTTPS(Hypertext Transfer Protocol Secure)는 HTTP에 TLS/SSL 프로토콜을 적용하여 데이터를 암호화하여 전송합니다.

* **보안 강화**: 민감한 데이터를 안전하게 보호합니다.
* **암호화 통신**: 서버 인증과 데이터 무결성 보장.

## RESTful API란?

RESTful API는 REST(Representational State Transfer) 아키텍처 스타일을 준수하여 설계된 API입니다. REST는 웹 리소스를 클라이언트와 서버가 일관된 방식으로 처리할 수 있도록 하는 설계 원칙입니다.

### REST의 핵심 원칙

1. **클라이언트-서버 분리**

   * 클라이언트와 서버의 역할을 분리하여 독립적인 개발과 운영이 가능하게 합니다.

2. **무상태성 (Stateless)**

   * 각 요청은 독립적으로 처리되며, 서버는 클라이언트의 상태를 저장하지 않습니다.

3. **일관된 인터페이스 (Uniform Interface)**

   * 고유한 URI로 리소스를 식별하고, HTTP 메서드를 통해 행위를 정의합니다.
   * 예: `GET /users` → 사용자 목록 조회

4. **캐시 가능성**

   * 서버의 응답은 캐시될 수 있으며, 이를 통해 성능과 효율성을 향상시킬 수 있습니다.

## RESTful API 예시

* `GET /articles` → 기사 목록 조회
* `POST /articles` → 새 기사 작성
* `GET /articles/1` → ID가 1인 기사 조회
* `PUT /articles/1` → ID가 1인 기사 수정
* `DELETE /articles/1` → ID가 1인 기사 삭제
