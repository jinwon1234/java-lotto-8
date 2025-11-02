package lotto.message;

public final class ErrorMessage {

    private static final String ERROR_PREFIX = "[ERROR] ";
    public static final String INVALID_CONSTRUCTOR = ERROR_PREFIX + "final 클래스는 인스턴스를 생성할 수 없습니다.";
    public static final String NOT_NUMBER_ERROR = ERROR_PREFIX + "숫자가 아닌 값은 입력할 수 없습니다.";
    public static final String NOT_ZERO_REMAINDER_ERROR = ERROR_PREFIX + "구입 금액은 로또 금액으로 나누어 떨어져야합니다.";
    public static final String INVALID_LOTTO_COUNT = ERROR_PREFIX + "로또 번호는 6개여야 합니다.";
    public static final String INVALID_LOTTO_NUMBER = ERROR_PREFIX + "로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    public static final String DUPLICATE_LOTTO_NUMBERS = ERROR_PREFIX + "로또 번호는 중복될 수 없습니다.";

    private ErrorMessage() {
        throw new AssertionError(INVALID_CONSTRUCTOR);
    }
}
