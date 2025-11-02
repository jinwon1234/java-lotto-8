package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static lotto.message.ErrorMessage.DUPLICATE_LOTTO_NUMBERS;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class WinningLottoTest {


    @ParameterizedTest
    @DisplayName("WinningLotto 인스턴스 생성 성공 - 보너스 번호와 로또 번호가 중복되지 않음")
    @CsvSource({
            "'1,2,3,4,5,6', 7",
            "'1,2,3,4,5,6', 8",
            "'1,2,3,4,5,6', 9",
    })
    void getWinningLottoInstanceSuccess(String winningNumbersInput, int bonusNumber) {

        // given
        List<Integer> winningNumbers = Arrays.stream(winningNumbersInput.split(",")).map(Integer::parseInt).toList();


        Lotto lotto = new Lotto(winningNumbers);

        // when
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        // then
        assertSoftly(softly -> {
            softly.assertThat(winningLotto.getLotto()).isEqualTo(lotto);
            softly.assertThat(winningLotto.getBonusNumber()).isEqualTo(bonusNumber);
        });

    }

    @ParameterizedTest
    @DisplayName("WinningLotto 인스턴스 생성 실패 - 보너스 번호와 로또 번호가 중복됨")
    @CsvSource({
            "'1,2,3,4,5,6', 6",
            "'1,2,3,4,5,6', 4",
            "'1,2,3,4,5,6', 3",
    })
    void getWinningLottoInstanceFail(String winningNumbersInput, int bonusNumber) {

        // given
        List<Integer> winningNumbers = Arrays.stream(winningNumbersInput.split(",")).map(Integer::parseInt).toList();

        Lotto lotto = new Lotto(winningNumbers);

        // when & then
        assertThatThrownBy(()->new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATE_LOTTO_NUMBERS);

    }


}
