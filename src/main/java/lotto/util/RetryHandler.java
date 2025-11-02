package lotto.util;

import java.util.function.Supplier;

import static lotto.message.ErrorMessage.*;

public final class RetryHandler {

    private RetryHandler() {
        throw new AssertionError(INVALID_CONSTRUCTOR);
    }

    public static <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
