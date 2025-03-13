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
## 기능 확인 방법 1 - Swagger에서 확인
### 사전준비
1. 브라우저에서 http://localhost:8080/ 를 연다. (실행에 필요한 데이타 입력용)
2. 브라우저 주소를 http://localhost:8080/swagger-ui.html 로 변경해서 연다.
### 기능확인
#### 1000원 적립
1. Swagger에서 (POST) /users/{userId}/points 엔드포인트를 선택한다.
2. Try it out버튼을 클릭한다.
3. 이하를 입력한다.

userId  : hong
Request body
   ```
   {
      "initialpoints" : 1000,
      "usablepoints" : 1000,
      "etc" : "1000원 적립한다",
      "expiredate" : "2025-12-31T23:59:59",
      "usersDto" : {
         "id": "hong"
      }
 }
   ```
  4. [Execute]버튼을 클릭한다.

#### 500원 적립
1. Swagger에서 (POST) /users/{userId}/points 엔드포인트를 선택한다.
2. Try it out버튼을 클릭한다.
3. 이하를 입력한다.

userId  : hong
Request body
   ```
   {
      "initialpoints" : 500,
      "usablepoints" : 500,
      "etc" : "500원 적립한다",
      "expiredate" : "2025-12-31T23:59:59",
      "usersDto" : {
         "id": "hong"
      }
 }
   ```
  4. [Execute]버튼을 클릭한다.

#### 1200원 사용
1. Swagger에서 (PUT) /users/{userId}/points 엔드포인트를 선택한다.
2. Try it out버튼을 클릭한다.
3. 이하를 입력한다.

userId  : hong
Request body
   ```
   {
      "initialpoints" : -1200,
      "etc" : "1200원 사용한다",
      "expiredate" : "2025-12-31T23:59:59",
      "usersDto" : {
         "id": "hong"
      },
      "ordersDto" : {
         "id": 1
      },
      "relatedPointkeys" : "1,2"
 }
   ```
  4. [Execute]버튼을 클릭한다.

#### 1100원 부분 사용 취소
1. Swagger에서 (PUT) /users/{userId}/points/cancel 엔드포인트를 선택한다.
2. Try it out버튼을 클릭한다.
3. 이하를 입력한다.
   relatedPointkeys는 사용취소하고 싶은 포인트 키를 입력합니다.

userId  : hong
Request body
   ```
   {
      "initialpoints" : 1100,
      "etc" : "1100원을 부분 사용취소",
      "expiredate" : "2025-12-31T23:59:59",
      "usersDto" : {
         "id": "hong"
      },
      "relatedPointkeys" : 3
 }
   ```
  4. [Execute]버튼을 클릭한다.

#### 포인트 이력 조회
1. Swagger에서 (GET) /users/{userId}/points/history 엔드포인트를 선택한다.
2. Try it out버튼을 클릭한다.
3. 이하를 입력한다.

userId  : hong


## 기능 확인 방법 2 - 브라우저에서 확인
1. 브라우저에서 http://localhost:8080/ 를 연다.
2. 1000원 적립한다 (총 잔액 0 -> 1000 원) [적립]버튼을 클릭한다.
3. 500원 적립한다 (총 잔액 1000 -> 1500 원) [적립]버튼을 클릭한다.
4. 주문번호 A1234 에서 1200원 사용한다 (총 잔액 1500 -> 300 원) [사용]버튼을 클릭한다.
5. C의 사용금액 1200원 중 1100원을 부분 사용취소 한다 (총 잔액 300 -> 1400 원) [취소]버튼을 클릭한다.
