package lotto.config;

import lotto.inputhandler.ConsoleInputHandler;
import lotto.inputhandler.InputHandler;
import lotto.outputhandler.ConsoleOutputHandler;
import lotto.outputhandler.OutputHandler;
import lotto.service.LottoService;
import lotto.service.LottoServiceV1;

public final class AppConfig {

    private static final AppConfig APP_CONFIG = new AppConfig();
    private final LottoService lottoService = new LottoServiceV1();
    private final InputHandler inputHandler = new ConsoleInputHandler();
    private final OutputHandler outputHandler = new ConsoleOutputHandler();

    private AppConfig() {
    }

    public static AppConfig getAppConfig() {
        return APP_CONFIG;
    }

    public LottoService lottoService() {
        return lottoService;
    }

    public InputHandler inputHandler() {
        return inputHandler;
    }

    public OutputHandler outputHandler() {
        return outputHandler;
    }

}
