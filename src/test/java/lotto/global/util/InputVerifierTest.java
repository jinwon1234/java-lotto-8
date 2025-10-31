package lotto.global.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.global.constant.Constant.*;
import static lotto.global.message.ErrorMessage.NOT_NUMBER_ERROR;
import static lotto.global.message.ErrorMessage.NOT_ZERO_REMAINDER_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputVerifierTest {

    @ParameterizedTest
    @ValueSource(strings = {"      ", ""})
    @DisplayName("비정상적인(빈값) 입력값 들어오면 IllegalArgumentException 반환")
    void blankInputFail(String input) {

        // when & then
        assertThatThrownBy(()->InputVerifier.safeParseInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_NUMBER_ERROR);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5", "10", "10403123"})
    @DisplayName("숫자 형태의 입력값이 들어오면 int 형태로 변환 후 반환")
    void safeParseIntSuccess(String input) {

        // when
        int result = InputVerifier.safeParseInt(input);

        // then
        assertThat(result).isEqualTo(Integer.parseInt(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5안녕", "하세요10", "1040이진원입니다.3123, -1, 0"})
    @DisplayName("숫자 형태의 입력값이 안들어오면 IllegalArgumentException 반환")
    void safeParseIntFail(String input) {

        // when & then
        assertThatThrownBy(()->InputVerifier.safeParseInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_NUMBER_ERROR);
    }

    @ParameterizedTest
    @ValueSource(ints =  {1000, 2000,3000})
    @DisplayName("LOTTO_COST로 나누었을 때 나머지가 0이면 성공")
    void safeDivideSuccess(int input) {

        // when
        int result = InputVerifier.safeDivide(input, LOTTO_COST);

        // then
        assertThat(result).isEqualTo(input /  LOTTO_COST);

    }

    @ParameterizedTest
    @ValueSource(ints =  {1001, 2100,3040})
    @DisplayName("LOTTO_COST로 나누었을 때 나머지가 0이 아니면 실패")
    void safeDivideFail(int input) {

        // when & then
        Assertions.assertThatThrownBy(()->InputVerifier.safeDivide(input, LOTTO_COST))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ZERO_REMAINDER_ERROR);

    }
}
