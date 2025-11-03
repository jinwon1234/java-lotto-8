package lotto;

import static lotto.message.ErrorMessage.INVALID_CONSTRUCTOR;

public final class TestConstant {

    public static final String LOTTO_NUMBER_DELIMITER = ",";

    private TestConstant() {
        throw new AssertionError(INVALID_CONSTRUCTOR);
    }
}
