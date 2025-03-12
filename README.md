# Point App Spring Boot REST API
이 프로젝트는 포인트 적립, 사용, 사용취소를 하는 REST API 입니다.

## 전제조건
이하가 인스톨되어 있어야 한다.
- Java21 (없으면 https://www.oracle.com/jp/java/technologies/downloads/ 에서 다운로드)
- Maven (없으면 https://maven.apache.org/download.cgi 에서 다운로드)
- Git (없으면 https://git-scm.com/downloads 에서 다운로드)

## 실행순서
1. 리포지토리에서 소스코드 습득
   ```
   git clone https://github.com/byonbyon/PointApp.git
   ```

2. 프로젝트 디렉토리로 이동
   ```
   cd PointApp
   ```

3. 의존관계를 인스톨
   ```
   mvn clean install
   ```

4. 실행
   ```
   mvn spring-boot:run
   ```

5. 실행확인
브라우저에서 이하를 열고 에러가 발생하지 않으면 ok.  
http://localhost:8080/


# 과제설명
