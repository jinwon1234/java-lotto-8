package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.dto.LottoRequestDto;
import lotto.dto.LottoResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static lotto.constant.Constant.LOTTO_COST;
import static org.assertj.core.api.Assertions.*;

class LottoServiceV1Test {

    private LottoServiceV1 lottoServiceV1 = new LottoServiceV1();


    @ParameterizedTest
    @DisplayName("LottoResult 반환 성공")
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
        LottoRequestDto lottoRequestDto = new LottoRequestDto(List.of(myLotto), winningLottoWithBonus);

        // when
        LottoResultDto lottoResult = lottoServiceV1.getLottoResult(lottoRequestDto);

        // then
        assertThat(lottoResult.yield())
                    .isEqualTo(getWinningAmount(lottoRank, lottoRequestDto.myLottos().size()));

    }

    private BigDecimal getWinningAmount(LottoRank lottoRank, int size) {
        int totalCost = LOTTO_COST * size;

        return BigDecimal.valueOf(lottoRank.getPrize())
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(totalCost) , 1, RoundingMode.HALF_UP);

    }
}
