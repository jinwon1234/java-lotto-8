package lotto.dto;

import lotto.Lotto;

import java.util.List;

public record LottoRequestDto(
        List<Lotto> myLottos,
        Lotto winningLotto,
        int bonusNumber
) {

}
