package lotto.domain;


import lotto.global.message.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static lotto.global.message.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class WinningLottoTest {


    @ParameterizedTest
    @DisplayName("WinningLotto 인스턴스 생성 성공 - 보너스 번호와 로또 번호가 중복되지 않음")
    @ValueSource(strings = {"1,2,3,4,5,6,7", "1,2,3,4,5,7,8", "24,25,29,30,31,32,33"})
    void getWinningLottoInstanceSuccess(String input) {

        // given
        List<Integer> numbers = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();

        List<Integer> winningNumbers = numbers.subList(0, 6);
        Integer bonusNumber = numbers.getLast();

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
    @ValueSource(strings = {"1,2,3,4,5,6,6", "1,2,3,4,5,7,7", "24,25,29,30,31,32,32"})
    void getWinningLottoInstanceFail(String input) {

        // given
        List<Integer> numbers = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();

        List<Integer> winningNumbers = numbers.subList(0, 6);
        Integer bonusNumber = numbers.getLast();

        Lotto lotto = new Lotto(winningNumbers);

        // when & then
        assertThatThrownBy(()->new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATE_LOTTO_NUMBERS);

    }


}
