package lotto.global.message;

import static lotto.global.message.ErrorMessage.*;

public final class Message {

    // InputHandler
    public static final String INPUT_PURCHASE_AMOUNT_REQUEST = "구입금액을 입력해 주세요.";
    public static final String INPUT_PURCHASE_AMOUNT_RESPONSE = "%d개를 구매했습니다";
    public static final String INPUT_WINNING_LOTTO = "당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    private Message() {
        throw new AssertionError(INVALID_CONSTRUCTOR);
    }
}
