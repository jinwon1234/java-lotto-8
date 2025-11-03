package lotto.domain;

import static lotto.constant.Constant.*;
import static lotto.message.ErrorMessage.DUPLICATE_LOTTO_NUMBERS;
import static lotto.message.ErrorMessage.INVALID_LOTTO_NUMBER;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;


    public WinningLotto(Lotto lotto, int bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        if (bonusNumber < LOTTO_MIN_NUM || bonusNumber > LOTTO_MAX_NUM) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER);
        }

        if(lotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBERS);
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
