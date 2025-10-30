package lotto.global.constant;

import static lotto.global.message.ErrorMessage.INVALID_CONSTRUCTOR;

public final class Constant {

    public static final int LOTTO_COST = 1000;

    private Constant() {
        throw new AssertionError(INVALID_CONSTRUCTOR);
    }
}
