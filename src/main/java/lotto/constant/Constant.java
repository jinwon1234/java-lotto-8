package lotto.constant;

import static lotto.message.ErrorMessage.INVALID_CONSTRUCTOR;

public final class Constant {

    public static final int LOTTO_COST = 1_000;

    private Constant() {
        throw new AssertionError(INVALID_CONSTRUCTOR);
    }
}
