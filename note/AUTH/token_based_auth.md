
# 쿠키 기반 인증 (Cookie-Based Authentication)

쿠키 기반 인증은 서버가 클라이언트에게 세션 쿠키를 발행하고, 이후 모든 요청마다 클라이언트가 이 쿠키를 서버로 전송하여 인증을 받는 방식입니다.

### 쿠키 기반 인증 개요
- **세션 ID**: 서버가 생성하여 클라이언트에 쿠키로 저장하는 고유한 식별자입니다. 서버 측 세션 저장소에 사용자의 상태를 유지합니다.
- **쿠키**: 클라이언트 측에 저장된 작은 데이터 조각으로, 서버와 클라이언트 간의 상태를 유지하는 데 사용됩니다.

### 작동 방식
1. **로그인 요청**: 사용자가 자격 증명을 서버에 전송합니다.
2. **세션 생성 및 쿠키 발행**: 서버는 사용자를 인증한 후, 세션 ID를 생성하고 이를 클라이언트에 쿠키로 전달합니다.
3. **세션 유지**: 클라이언트는 이후 요청마다 쿠키를 서버에 전달하여 세션을 유지합니다.
4. **세션 검증**: 서버는 요청 시 클라이언트의 세션 ID를 확인하여 인증을 처리합니다.

### 장점
1. **상태 유지**: 사용자의 세션 상태를 서버에서 관리할 수 있어 애플리케이션 상태를 쉽게 유지할 수 있습니다.
2. **보안 설정 가능**: Secure, HttpOnly, SameSite 등 쿠키 속성을 사용해 보안을 강화할 수 있습니다.
3. **브라우저 호환성**: 모든 주요 브라우저에서 기본적으로 지원되며, 구현이 상대적으로 쉽습니다.

### 단점
1. **서버 부하**: 서버가 모든 사용자의 세션을 유지해야 하므로, 많은 사용자가 있을 경우 서버 부하가 증가할 수 있습니다.
2. **보안 취약성**: 쿠키가 탈취될 경우 세션 하이재킹 공격에 취약할 수 있습니다.
3. **CORS 문제**: 클라이언트와 서버가 다른 도메인에 있을 경우 CORS 정책에 의해 쿠키 전송이 제한될 수 있습니다.

### 예제
- **쇼핑몰 애플리케이션**: 사용자가 로그인할 때 서버가 세션 ID를 발행하고, 클라이언트는 이 세션 ID를 포함한 쿠키를 서버에 전송하여 로그인 상태를 유지합니다.
