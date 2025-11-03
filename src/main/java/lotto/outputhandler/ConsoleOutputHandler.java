package lotto.outputhandler;

import lotto.dto.LottoResultDto;

import java.text.DecimalFormat;

import static lotto.domain.LottoRank.*;
import static lotto.domain.LottoRank.FIRST;
import static lotto.domain.LottoRank.SECOND;
import static lotto.domain.LottoRank.THIRD;
import static lotto.message.Message.OUTPUT_MESSAGE_FORMAT;

public class ConsoleOutputHandler implements OutputHandler {

    private static final String PRIZE_FORMAT_PATTERN = "###,###";
    private static final String YIELD_FORMAT_PATTERN = "###,##0.0";

    public void getResponse(LottoResultDto resultDto) {

        DecimalFormat prizeFormat = new DecimalFormat(PRIZE_FORMAT_PATTERN);
        DecimalFormat yieldFormat = new DecimalFormat(YIELD_FORMAT_PATTERN);

        String response = OUTPUT_MESSAGE_FORMAT
                .formatted(
                        FIFTH.getMatchCount(), prizeFormat.format(FIFTH.getPrize()), resultDto.getFifthCount(),
                        FOURTH.getMatchCount(), prizeFormat.format(FOURTH.getPrize()), resultDto.getFourthCount(),
                        THIRD.getMatchCount(), prizeFormat.format(THIRD.getPrize()), resultDto.getThirdCount(),
                        SECOND.getMatchCount(), prizeFormat.format(SECOND.getPrize()), resultDto.getSecondCount(),
                        FIRST.getMatchCount(), prizeFormat.format(FIRST.getPrize()), resultDto.getFirstCount(),
                        yieldFormat.format(resultDto.yield())
                );
        System.out.println(response);
    }
}
