package lotto.service;

import lotto.dto.LottoRequestDto;
import lotto.dto.LottoResultDto;

public interface LottoService {

    LottoResultDto getLottoResult(LottoRequestDto lottoRequestDto);
}
