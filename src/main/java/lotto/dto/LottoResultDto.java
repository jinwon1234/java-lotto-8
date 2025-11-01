package lotto.dto;

import lotto.domain.LottoRank;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("###,###");

        return """
                당첨 통계
                ---
                %d개 일치 (%s원) - %d개
                %d개 일치 (%s원) - %d개
                %d개 일치 (%s원) - %d개
                %d개 일치, 보너스 볼 일치 (%s원) - %d개
                %d개 일치 (%s원) - %d개
                총 수익률은 %s%%입니다.
                """
                .formatted(
                        FIFTH.getMatchCount(), df.format(FIFTH.getPrize()), getFifthCount(),
                        FOURTH.getMatchCount(), df.format(FOURTH.getPrize()), getFourthCount(),
                        THIRD.getMatchCount(), df.format(THIRD.getPrize()), getThirdCount(),
                        SECOND.getMatchCount(), df.format(SECOND.getPrize()), getSecondCount(),
                        FIRST.getMatchCount(), df.format(FIRST.getPrize()), getFirstCount(),
                        df.format(this.yield)
                        );
    }
}
