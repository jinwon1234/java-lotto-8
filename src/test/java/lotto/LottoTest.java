package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lotto.domain.LottoRank.*;
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
    @DisplayName("Lotto 등수 계산 성공 - 1등")
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,3,4,5,7", "24,25,29,30,31,32"})
    void getRankFirstSuccess(String input) {

        // given
        List<Integer> myNumbers = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();
        Lotto myLotto = new Lotto(myNumbers);

        // when
        LottoRank rank = myLotto.getRank(new WinningLotto(myLotto,45));

        // then
        assertThat(rank).isEqualTo(FIRST);
    }

    @ParameterizedTest
    @DisplayName("Lotto 등수 계산 성공 - 2등")
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,3,4,5,7", "24,25,29,30,31,32"})
    void getRankSecondSuccess(String input) {
        // given
        List<Integer> myNumbers = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .toList();
        Lotto myLotto = new Lotto(myNumbers);

        // 6개 중 마지막 번호를 다른 숫자로 바꿔서 당첨번호 생성
        List<Integer> winningNumbers = new ArrayList<>(myNumbers.subList(0, 5));
        winningNumbers.add(45); // 다른 번호
        Lotto winningLotto = new Lotto(winningNumbers);

        int bonusNumber = myNumbers.getLast(); // 내 로또의 마지막 번호를 보너스로 설정

        // when
        LottoRank rank = myLotto.getRank(new WinningLotto(winningLotto, bonusNumber));

        // then
        assertThat(rank).isEqualTo(SECOND);
    }

    @ParameterizedTest
    @DisplayName("Lotto 등수 계산 성공 - 3등")
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,3,4,5,7", "24,25,29,30,31,32"})
    void getRankThirdSuccess(String input) {
        // given
        List<Integer> myNumbers = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .toList();
        Lotto myLotto = new Lotto(myNumbers);

        // 6개 중 마지막 번호를 다른 숫자로 바꿔서 당첨번호 생성
        List<Integer> winningNumbers = new ArrayList<>(myNumbers.subList(0, 5));
        winningNumbers.add(45); // 다른 번호
        Lotto winningLotto = new Lotto(winningNumbers);

        // when
        LottoRank rank = myLotto.getRank(new WinningLotto(winningLotto, 44));

        // then
        assertThat(rank).isEqualTo(THIRD);
    }

    @ParameterizedTest
    @DisplayName("Lotto 등수 계산 성공 - 4등")
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,3,4,5,7", "24,25,29,30,31,32"})
    void getRankFourthSuccess(String input) {
        // given
        List<Integer> myNumbers = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .toList();
        Lotto myLotto = new Lotto(myNumbers);

        // 6개 중 마지막 번호를 다른 숫자로 바꿔서 당첨번호 생성
        List<Integer> winningNumbers = new ArrayList<>(myNumbers.subList(0, 4));
        winningNumbers.add(45); // 다른 번호
        winningNumbers.add(44);
        Lotto winningLotto = new Lotto(winningNumbers);

        // when
        LottoRank rank = myLotto.getRank(new WinningLotto(winningLotto, 43));

        // then
        assertThat(rank).isEqualTo(FOURTH);
    }

    @ParameterizedTest
    @DisplayName("Lotto 등수 계산 성공 - 5등")
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,3,4,5,7", "24,25,29,30,31,32"})
    void getRankFifthSuccess(String input) {
        // given
        List<Integer> myNumbers = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .toList();
        Lotto myLotto = new Lotto(myNumbers);

        // 6개 중 마지막 번호를 다른 숫자로 바꿔서 당첨번호 생성
        List<Integer> winningNumbers = new ArrayList<>(myNumbers.subList(0, 3));
        winningNumbers.add(45); // 다른 번호
        winningNumbers.add(44);
        winningNumbers.add(43);
        Lotto winningLotto = new Lotto(winningNumbers);

        // when
        LottoRank rank = myLotto.getRank(new WinningLotto(winningLotto, 42));

        // then
        assertThat(rank).isEqualTo(FIFTH);
    }


    @ParameterizedTest
    @DisplayName("Lotto 등수 계산 성공 - 등수 없음")
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,3,4,5,7", "24,25,29,30,31,32"})
    void getRankNoneSuccess(String input) {
        // given
        List<Integer> myNumbers = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .toList();
        Lotto myLotto = new Lotto(myNumbers);

        // 6개 중 마지막 번호를 다른 숫자로 바꿔서 당첨번호 생성
        List<Integer> winningNumbers = new ArrayList<>(myNumbers.subList(0, 2));
        winningNumbers.add(45); // 다른 번호
        winningNumbers.add(44);
        winningNumbers.add(43);
        winningNumbers.add(42);
        Lotto winningLotto = new Lotto(winningNumbers);

        // when
        LottoRank rank = myLotto.getRank(new WinningLotto(winningLotto, 41));

        // then
        assertThat(rank).isEqualTo(NONE);
    }
}
