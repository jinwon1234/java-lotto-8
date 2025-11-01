package lotto.global.message;

import static lotto.global.message.ErrorMessage.*;

public final class Message {

    // InputHandler
    public static final String INPUT_PURCHASE_AMOUNT_REQUEST = "구입금액을 입력해 주세요.";
    public static final String INPUT_PURCHASE_AMOUNT_RESPONSE = "\n%d개를 구매했습니다.";
    public static final String INPUT_WINNING_LOTTO = "\n당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_NUMBER = "\n보너스 번호를 입력해 주세요.";

    // OutputHandler
    public static final String OUTPUT_MESSAGE_FORMAT = """
                
                당첨 통계
                ---
                %d개 일치 (%s원) - %d개
                %d개 일치 (%s원) - %d개
                %d개 일치 (%s원) - %d개
                %d개 일치, 보너스 볼 일치 (%s원) - %d개
                %d개 일치 (%s원) - %d개
                총 수익률은 %s%%입니다.
                """;

    private Message() {
        throw new AssertionError(INVALID_CONSTRUCTOR);
    }
}
