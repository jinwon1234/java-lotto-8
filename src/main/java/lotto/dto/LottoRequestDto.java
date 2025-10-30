package lotto.dto;

import lotto.domain.Lotto;

import java.util.List;

public record LottoRequestDto(
        List<Lotto> myLottos,
        Lotto winningLotto,
        int bonusNumber
) {

}
