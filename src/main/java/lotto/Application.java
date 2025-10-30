package lotto;

import lotto.dto.LottoRequestDto;
import lotto.inputhandler.ConsoleInputHandler;
import lotto.inputhandler.InputHandler;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputHandler inputHandler = new ConsoleInputHandler();

        LottoRequestDto input = inputHandler.getInput();
    }
}
