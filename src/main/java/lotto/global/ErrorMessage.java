package lotto.global;

public final class ErrorMessage {

    private static final String ERROR_PREFIX = "[ERROR] ";
    public static final String INVALID_CONSTRUCTOR = ERROR_PREFIX + "final 클래스는 인스턴스를 생성할 수 없습니다.";

    private ErrorMessage() {
        throw new AssertionError(INVALID_CONSTRUCTOR);
    }
}
