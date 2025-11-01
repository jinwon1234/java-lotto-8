package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;

import java.util.List;

public record LottoRequestDto(
        List<Lotto> myLottos,
        WinningLotto winningLotto
) {

}
