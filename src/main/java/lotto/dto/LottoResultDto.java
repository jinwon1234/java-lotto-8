package lotto.dto;

import lotto.domain.LottoRank;

import java.math.BigDecimal;
import java.util.Map;

import static lotto.domain.LottoRank.*;

public record LottoResultDto(
        Map<LottoRank,Integer> rankCountMap,
        BigDecimal yield
) {

    public int getFirstCount() {
        return rankCountMap.getOrDefault(FIRST,0);
    }

    public int getSecondCount() {
        return rankCountMap.getOrDefault(SECOND,0);
    }

    public int getThirdCount() {
        return rankCountMap.getOrDefault(THIRD,0);
    }

    public int getFourthCount() {
        return rankCountMap.getOrDefault(FOURTH,0);
    }

    public int getFifthCount() {
        return rankCountMap.getOrDefault(FIFTH,0);
    }
}
