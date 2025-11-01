package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.dto.LottoRequestDto;
import lotto.dto.LottoResultDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;

import static lotto.global.constant.Constant.*;

public class LottoServiceV1 implements LottoService {

    public LottoResultDto getLottoResult(LottoRequestDto lottoRequestDto) {
        Map<LottoRank, Integer> rankCountMap = getRankCountMap(lottoRequestDto);

        long winningAmount = getWinningAmount(rankCountMap);

        BigDecimal yield = getYield(winningAmount, lottoRequestDto.myLottos().size() * LOTTO_COST);

        return new LottoResultDto(rankCountMap, yield);
    }

    private Map<LottoRank, Integer> getRankCountMap(LottoRequestDto lottoRequestDto) {
        Map<LottoRank, Integer> rankCountMap = new EnumMap<>(LottoRank.class);

        for (Lotto myLotto : lottoRequestDto.myLottos()) {
            LottoRank rank = myLotto.getRank(lottoRequestDto.winningLotto());
            if (rank == LottoRank.NONE) continue;
            rankCountMap.merge(rank, 1, Integer::sum);
        }
        return rankCountMap;
    }

    private long getWinningAmount(Map<LottoRank, Integer> rankCountMap) {

        return rankCountMap.entrySet()
                .stream()
                .mapToLong((entry)-> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    private BigDecimal getYield(long winningAmount, int totalCost) {
        return BigDecimal.valueOf(winningAmount)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(totalCost), 1, RoundingMode.HALF_UP);

    }
}
