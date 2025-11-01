package lotto;

import lotto.dto.LottoRequestDto;
import lotto.dto.LottoResultDto;
import lotto.inputhandler.ConsoleInputHandler;
import lotto.inputhandler.InputHandler;
import lotto.service.LottoService;
import lotto.service.LottoServiceV1;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputHandler inputHandler = new ConsoleInputHandler();

        LottoRequestDto input = inputHandler.getInput();
        LottoService lottoService = new LottoServiceV1();
        LottoResultDto lottoResult = lottoService.getLottoResult(input);
        System.out.println(lottoResult);
    }
}
