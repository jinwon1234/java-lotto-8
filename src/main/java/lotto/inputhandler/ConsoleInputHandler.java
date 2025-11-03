package lotto.inputhandler;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.LottoRequestDto;
import lotto.util.InputVerifier;
import lotto.util.LottoGenerator;
import lotto.util.RetryHandler;

import java.util.Arrays;
import java.util.List;

import static lotto.constant.Constant.LOTTO_COST;
import static lotto.message.Message.*;


public class ConsoleInputHandler implements InputHandler {

    private static final String LOTTO_NUMBER_DELIMITER = ",";

    public LottoRequestDto getInput() {
        List<Lotto> myLotto = RetryHandler.retryUntilValid(this::getMyLottos);

        myLotto.forEach(lotto -> System.out.println(lotto.toString()));

        Lotto winningLotto = RetryHandler.retryUntilValid(this::getWinningLotto);

        WinningLotto winningLottoWithBonusNumber = RetryHandler.retryUntilValid(() -> getBonusNumber(winningLotto));

        return new LottoRequestDto(myLotto, winningLottoWithBonusNumber);
    }

    private List<Lotto> getMyLottos() {

        System.out.println(INPUT_PURCHASE_AMOUNT_REQUEST);
        int purchaseAmount = InputVerifier.safeParseInt(Console.readLine());
        int lottoCount = InputVerifier.safeDivideForRemainderZero(purchaseAmount, LOTTO_COST);
        System.out.println(String.format(INPUT_PURCHASE_AMOUNT_RESPONSE, lottoCount));
        return LottoGenerator.generate(lottoCount);
    }

    private Lotto getWinningLotto() {
        System.out.println(INPUT_WINNING_LOTTO);
        String numbers = Console.readLine();

        List<Integer> lottoNumbers = Arrays.stream(numbers.split(LOTTO_NUMBER_DELIMITER))
                .map(InputVerifier::safeParseInt)
                .sorted().toList();

        return new Lotto(lottoNumbers);
    }

    private WinningLotto getBonusNumber(Lotto winningLotto) {
        System.out.println(INPUT_BONUS_NUMBER);
        int bonusNumber = InputVerifier.safeParseInt(Console.readLine());

        return new WinningLotto(winningLotto, bonusNumber);
    }
}
