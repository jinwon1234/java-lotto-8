package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성

    @ParameterizedTest
    @DisplayName("Lotto 인스턴스 생성 성공")
    @ValueSource(strings = {"1,2,3,4,5,6", "4,5,6,8,9,12", "24,25,29,30,44,45"})
    void generateLottoInstanceSuccess(String input) {

        // given
        List<Integer> numbers = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();

        // when
        Lotto myLotto = new Lotto(numbers);

        // then
        assertThat(myLotto.getNumbers()).isEqualTo(numbers);
    }

    @ParameterizedTest
    @DisplayName("LottoResult 등수 계산 성공 성공")
    @CsvSource({
            "'1,2,3,4,5,6', '1,2,3,4,5,6', 7, FIRST",
            "'1,2,3,4,5,6', '1,2,3,4,5,8', 6, SECOND",
            "'1,2,3,4,5,6', '1,2,3,4,5,11', 7, THIRD",
            "'1,2,3,4,5,6', '1,2,3,4,10,11', 6, FOURTH",
            "'1,2,3,4,5,6', '1,2,3,10,11,12', 6, FIFTH",
            "'1,2,3,4,5,6', '10,11,12,13,15,16', 6, NONE",
    })
    void getLottoResultForFirstSuccess(String myNumbersInput, String winningNumbersInput,
                                       int bonusNumber, LottoRank lottoRank) {
        // given
        List<Integer> myNumbers = Arrays.stream(myNumbersInput.split(",")).map(Integer::parseInt).toList();
        List<Integer> winningNumbers = Arrays.stream(winningNumbersInput.split(",")).map(Integer::parseInt).toList();

        Lotto myLotto = new Lotto(myNumbers);
        Lotto winningLotto = new Lotto(winningNumbers);
        WinningLotto winningLottoWithBonus = new WinningLotto(winningLotto, bonusNumber);

        // when
        LottoRank rank = myLotto.getRank(winningLottoWithBonus);

        // then
        assertThat(rank).isEqualTo(lottoRank);

    }
}
