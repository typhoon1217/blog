# SAML (Security Assertion Markup Language)

SAML은 XML 기반의 개방형 표준으로, 주로 엔터프라이즈 환경에서 싱글 사인온(SSO)을 구현하는 데 사용됩니다. 사용자의 인증 정보를 XML 형식으로 교환하여, 다른 도메인 간에 안전하게 사용자 인증을 처리합니다.

### SAML 개요
- **Identity Provider (IdP)**: 사용자의 신원을 확인하고 인증 정보를 생성하는 엔티티입니다.
- **Service Provider (SP)**: 사용자가 접근하려는 웹 서비스나 애플리케이션을 제공합니다.
- **SAML Assertion**: IdP가 사용자의 인증 정보를 담아 SP에 전달하는 XML 문서입니다.

### 작동 방식
1. **사용자 요청**: 사용자가 SP에 접근을 시도합니다.
2. **IdP 인증**: SP는 IdP로 사용자를 리디렉션하여 인증을 요청합니다.
3. **SAML Assertion 전송**: IdP는 사용자를 인증한 후, SAML Assertion을 SP로 반환합니다.
4. **접근 허용**: SP는 SAML Assertion을 검증하고, 사용자의 접근을 허용합니다.

### 장점
1. **강력한 보안**: XML 디지털 서명 및 암호화를 통해 보안이 강화됩니다.
2. **SSO 지원**: 여러 애플리케이션에서 한 번의 로그인으로 사용자 인증을 처리할 수 있습니다.
3. **기업 환경 적합**: 여러 도메인 간 인증이 필요한 엔터프라이즈 환경에 적합합니다.

### 단점
1. **구현 복잡성**: 설정과 구현이 매우 복잡하며, XML을 기반으로 하기 때문에 더 많은 리소스를 소모합니다.
2. **높은 비용**: 초기 설정 비용이 높고, 유지 관리가 어렵습니다.
3. **인터페이스 호환성 문제**: SAML을 지원하지 않는 애플리케이션과 통합하기가 어렵습니다.

### 예제
- **기업 포털 SSO**: 기업 내 여러 애플리케이션에 대해 하나의 로그인으로 접근을 제공하기 위해 SAML을 사용하여 SSO를 구현합니다.

