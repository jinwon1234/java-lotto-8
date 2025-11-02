package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.dto.LottoRequestDto;
import lotto.dto.LottoResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lotto.constant.Constant.LOTTO_COST;
import static lotto.domain.LottoRank.*;
import static org.assertj.core.api.SoftAssertions.*;

class LottoServiceV1Test {

    private LottoServiceV1 lottoServiceV1 = new LottoServiceV1();


    @ParameterizedTest
    @DisplayName("LottoResult 반환 성공 - 1등")
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,3,4,5,7", "24,25,29,30,31,32"})
    void getLottoResultForFirstSuccess(String input) {

        // given
        List<Integer> myNumbers = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();
        Lotto myLotto = new Lotto(myNumbers);
        LottoRequestDto lottoRequestDto = new LottoRequestDto(List.of(myLotto), new WinningLotto(myLotto, 45));

        // when
        LottoResultDto lottoResult = lottoServiceV1.getLottoResult(lottoRequestDto);

        // then
        assertSoftly(softly -> {
            softly.assertThat(lottoResult.getFirstCount()).isEqualTo(1);
            softly.assertThat(lottoResult.getSecondCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getThirdCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getFourthCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getFifthCount()).isEqualTo(0);
            softly.assertThat(lottoResult.yield())
                    .isEqualTo(getWinningAmount(FIRST, lottoRequestDto.myLottos().size()));
        });
    }

    @ParameterizedTest
    @DisplayName("LottoResult 반환 성공 - 2등")
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,3,4,5,7", "24,25,29,30,31,32"})
    void getLottoResultForSecondSuccess(String input) {
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

        LottoRequestDto lottoRequestDto = new LottoRequestDto(List.of(myLotto), new WinningLotto(winningLotto, bonusNumber));

        // when
        LottoResultDto lottoResult = lottoServiceV1.getLottoResult(lottoRequestDto);

        // then
        assertSoftly(softly -> {
            softly.assertThat(lottoResult.getFirstCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getSecondCount()).isEqualTo(1);
            softly.assertThat(lottoResult.getThirdCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getFourthCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getFifthCount()).isEqualTo(0);
            softly.assertThat(lottoResult.yield())
                    .isEqualTo(getWinningAmount(SECOND, lottoRequestDto.myLottos().size()));
        });
    }

    @ParameterizedTest
    @DisplayName("LottoResult 반환 성공 - 3등")
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,3,4,5,7", "24,25,29,30,31,32"})
    void getLottoResultForThirdSuccess(String input) {
        // given
        List<Integer> myNumbers = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .toList();
        Lotto myLotto = new Lotto(myNumbers);

        // 6개 중 마지막 번호를 다른 숫자로 바꿔서 당첨번호 생성
        List<Integer> winningNumbers = new ArrayList<>(myNumbers.subList(0, 5));
        winningNumbers.add(45); // 다른 번호
        Lotto winningLotto = new Lotto(winningNumbers);

        LottoRequestDto lottoRequestDto = new LottoRequestDto(List.of(myLotto), new WinningLotto(winningLotto, 44));

        // when
        LottoResultDto lottoResult = lottoServiceV1.getLottoResult(lottoRequestDto);

        // then
        assertSoftly(softly -> {
            softly.assertThat(lottoResult.getFirstCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getSecondCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getThirdCount()).isEqualTo(1);
            softly.assertThat(lottoResult.getFourthCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getFifthCount()).isEqualTo(0);
            softly.assertThat(lottoResult.yield())
                    .isEqualTo(getWinningAmount(THIRD, lottoRequestDto.myLottos().size()));
        });
    }

    @ParameterizedTest
    @DisplayName("LottoResult 반환 성공 - 4등")
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,3,4,5,7", "24,25,29,30,31,32"})
    void getLottoResultForFourthSuccess(String input) {
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

        LottoRequestDto lottoRequestDto = new LottoRequestDto(List.of(myLotto), new WinningLotto(winningLotto, 43));

        // when
        LottoResultDto lottoResult = lottoServiceV1.getLottoResult(lottoRequestDto);

        // then
        assertSoftly(softly -> {
            softly.assertThat(lottoResult.getFirstCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getSecondCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getThirdCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getFourthCount()).isEqualTo(1);
            softly.assertThat(lottoResult.getFifthCount()).isEqualTo(0);
            softly.assertThat(lottoResult.yield())
                    .isEqualTo(getWinningAmount(FOURTH, lottoRequestDto.myLottos().size()));
        });
    }

    @ParameterizedTest
    @DisplayName("LottoResult 반환 성공 - 5등")
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
        LottoRequestDto lottoRequestDto = new LottoRequestDto(List.of(myLotto), new WinningLotto(winningLotto, 42));

        // when
        LottoResultDto lottoResult = lottoServiceV1.getLottoResult(lottoRequestDto);

        // then
        assertSoftly(softly -> {
            softly.assertThat(lottoResult.getFirstCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getSecondCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getThirdCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getFourthCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getFifthCount()).isEqualTo(1);
            softly.assertThat(lottoResult.yield())
                    .isEqualTo(getWinningAmount(FIFTH, lottoRequestDto.myLottos().size()));
        });
    }


    @ParameterizedTest
    @DisplayName("LottoResult 반환 성공- 등수 없음")
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

        LottoRequestDto lottoRequestDto = new LottoRequestDto(List.of(myLotto), new WinningLotto(winningLotto, 41));

        // when
        LottoResultDto lottoResult = lottoServiceV1.getLottoResult(lottoRequestDto);

        // then
        assertSoftly(softly -> {
            softly.assertThat(lottoResult.getFirstCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getSecondCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getThirdCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getFourthCount()).isEqualTo(0);
            softly.assertThat(lottoResult.getFifthCount()).isEqualTo(0);
            softly.assertThat(lottoResult.yield())
                    .isEqualTo(getWinningAmount(NONE, lottoRequestDto.myLottos().size()));
        });
    }

    private BigDecimal getWinningAmount(LottoRank lottoRank, int size) {
        int totalCost = LOTTO_COST * size;

        return BigDecimal.valueOf(lottoRank.getPrize())
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(totalCost) , 1, RoundingMode.HALF_UP);

    }
}
