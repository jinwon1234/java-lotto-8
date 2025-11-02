package lotto.util;

import static lotto.message.ErrorMessage.*;

public final class InputVerifier {

    private InputVerifier() {
        throw new AssertionError(INVALID_CONSTRUCTOR);
    }

    public static int safeParseInt(String num) {
        try {
            return Integer.parseInt(num.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR);
        }
    }

    public static int safeDivideForRemainderZero(int num, int divisor) {
        if (num % divisor == 0) {
            return num / divisor;
        }

        throw new IllegalArgumentException(NOT_ZERO_REMAINDER_ERROR);
    }
}
