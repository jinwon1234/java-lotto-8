package lotto;

import lotto.config.AppConfig;
import lotto.dto.LottoRequestDto;
import lotto.dto.LottoResultDto;
import lotto.inputhandler.InputHandler;
import lotto.outputhandler.OutputHandler;
import lotto.service.LottoService;

public class LottoApplicationRunner {


    public static void run() {
        AppConfig appConfig = AppConfig.getAppConfig();
        InputHandler inputHandler = appConfig.inputHandler();
        LottoService lottoService = appConfig.lottoService();
        OutputHandler outputHandler = appConfig.outputHandler();

        LottoRequestDto lottoRequestDto = inputHandler.getInput();
        LottoResultDto lottoResult = lottoService.getLottoResult(lottoRequestDto);
        outputHandler.getResponse(lottoResult);
    }

}
