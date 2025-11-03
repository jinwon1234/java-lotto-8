# 프리코스 3주차 미션 - 로또

간단한 로또 발매기를 구현한다.

---

## 디렉토리 구조
```
src
┣ main
┃ ┗ java
┃ ┃ ┗ lotto
┃ ┃ ┃ ┣ config
┃ ┃ ┃ ┃ ┗ AppConfig.java
┃ ┃ ┃ ┣ constant
┃ ┃ ┃ ┃ ┗ Constant.java
┃ ┃ ┃ ┣ domain
┃ ┃ ┃ ┃ ┣ Lotto.java
┃ ┃ ┃ ┃ ┣ LottoRank.java
┃ ┃ ┃ ┃ ┗ WinningLotto.java
┃ ┃ ┃ ┣ dto
┃ ┃ ┃ ┃ ┣ LottoRequestDto.java
┃ ┃ ┃ ┃ ┗ LottoResultDto.java
┃ ┃ ┃ ┣ inputhandler
┃ ┃ ┃ ┃ ┣ ConsoleInputHandler.java
┃ ┃ ┃ ┃ ┗ InputHandler.java
┃ ┃ ┃ ┣ message
┃ ┃ ┃ ┃ ┣ ErrorMessage.java
┃ ┃ ┃ ┃ ┗ Message.java
┃ ┃ ┃ ┣ outputhandler
┃ ┃ ┃ ┃ ┣ ConsoleOutputHandler.java
┃ ┃ ┃ ┃ ┗ OutputHandler.java
┃ ┃ ┃ ┣ service
┃ ┃ ┃ ┃ ┣ LottoService.java
┃ ┃ ┃ ┃ ┗ LottoServiceV1.java
┃ ┃ ┃ ┣ util
┃ ┃ ┃ ┃ ┣ InputVerifier.java
┃ ┃ ┃ ┃ ┣ LottoGenerator.java
┃ ┃ ┃ ┃ ┗ RetryHandler.java
┃ ┃ ┃ ┣ Application.java
┃ ┃ ┃ ┗ LottoApplicationRunner.java
┗ test
┃ ┗ java
┃ ┃ ┗ lotto
┃ ┃ ┃ ┣ domain
┃ ┃ ┃ ┃ ┣ LottoRankTest.java
┃ ┃ ┃ ┃ ┗ WinningLottoTest.java
┃ ┃ ┃ ┣ service
┃ ┃ ┃ ┃ ┗ LottoServiceV1Test.java
┃ ┃ ┃ ┣ util
┃ ┃ ┃ ┃ ┗ InputVerifierTest.java
┃ ┃ ┃ ┣ ApplicationTest.java
┃ ┃ ┃ ┣ LottoTest.java
┃ ┃ ┃ ┗ TestConstant.java
```

## 기능 요구사항 정리

### 1. 로또 구입 금액 입력
- 로또 구입 금액을 입력 받는다. 구입 금액은 `1,000원` 단위로 입력 받는다. 구입 금액을 `1,000원`으로 나눈 몫만큼 로또가 발행된다.
- 예외 상황
    - 빈 값을 입력하거나 숫자가 아닌 값을 입력한 경우
    - `1,000원`으로 나누어 떨어지지 않는 경우

### 2. 구매한 로또 번호 출력
- 발행한 로또 수량 및 번호를 출력한다. 로또 번호는 오름차순으로 정렬하여 보여준다.

### 3. 당첨번호 입력
- 당첨 번호 6개를 입력받는다. 번호는 `쉼표(,)`를 기준으로 한다.
- 예외 상황
    - `쉼표(,)`를 기준으로 숫자를 분리했을 때, 빈 값을 입력하거나 숫자가 아닌 값을 입력한 경우
    - `6개의 숫자`를 입력하지 않은 경우
    - `1~45` 범위에 벗어난 숫자를 입력한 경우
    - 중복된 숫자를 입력한 경우

### 4. 보너스 번호 입력
- 보너스 번호 1개를 입력받는다.
- 예외 상황
    - 빈 값을 입력하거나 숫자가 아닌 값을 입력한 경우
    - `1~45` 범위에 벗어난 숫자를 입력한 경우
    - 보너스 번호가 당첨번호와 중복된 경우

### 5. 당첨 내역 출력
- 당첨 내역, 수익률을 출력한다.
- 수익률은 소수점 둘째 자리에서 반올림한다. (ex. 100.0%, 51.5%, 1,000,000.0%)

### 공통
- 예외 상황 시 에러 문구를 출력해야 한다. 단, 에러 문구는 `[ERROR]`로 시작해야 한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시키고, `[ERROR]`로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.

## 프로그램 구조

### 실행 흐름
```
Application
        ↓
LottoApplicationRunner
        ↓
     AppConfig
        ↓
InputHandler → LottoService → OutputHandler
```

### 계층간 책임
| 계층                | 클래스                      | 주요 역할                                                                      |
| ----------------- | ------------------------ |----------------------------------------------------------------------------|
| **Application**   | `Application`            | 프로그램의 진입점 (`main` 메서드 실행) — `LottoApplicationRunner.run()` 호출을 통해 전체 실행 시작 |
| **Runner**        | `LottoApplicationRunner` | 프로그램 전체 흐름 제어 (요청 입력 → 비즈니스 로직 수행 → 결과 출력)                                 |
| **Configuration** | `AppConfig`              | 객체 생성 관리 (싱글톤)                                                             |
| **Input Layer**   | `ConsoleInputHandler`    | 사용자 입력 처리 (구입 금액, 당첨 번호, 보너스 번호 입력)                                        |
| **Service Layer** | `LottoServiceV1`         | 비즈니스 로직 수행 (등수 및 수익률 계산)                                                   |
| **Output Layer**  | `ConsoleOutputHandler`   | 결과 출력 및 포맷팅                                                                |



