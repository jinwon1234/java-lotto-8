package lotto.outputhandler;

import lotto.dto.LottoResultDto;

public interface OutputHandler {

    String getResponse(LottoResultDto resultDto);
}
