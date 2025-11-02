package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class LottoRankTest {

    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "3, false, FIFTH",
            "2, false, NONE"
    })
    @DisplayName("로또 등수 계산 성공")
    void calculateRankParamTest(int matchCount, boolean bonus, LottoRank expected) {
        // when
        LottoRank result = LottoRank.calculateRank(matchCount, bonus);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
