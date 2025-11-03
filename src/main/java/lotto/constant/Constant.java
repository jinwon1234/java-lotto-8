package lotto.constant;

import static lotto.message.ErrorMessage.INVALID_CONSTRUCTOR;

public final class Constant {

    public static final int LOTTO_COST = 1_000;
    public static final int LOTTO_MIN_NUM = 1;
    public static final int LOTTO_MAX_NUM = 45;

    private Constant() {
        throw new AssertionError(INVALID_CONSTRUCTOR);
    }
}
