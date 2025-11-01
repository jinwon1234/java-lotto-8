package lotto.domain;

import static lotto.global.message.ErrorMessage.*;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;


    public WinningLotto(Lotto lotto, int bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        if (bonusNumber < 0 || bonusNumber > 45) {
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
