package lotto.inputhandler;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.dto.LottoRequestDto;
import lotto.global.util.InputVerifier;
import lotto.global.util.LottoGenerator;
import lotto.global.util.RetryHandler;

import java.util.Arrays;
import java.util.List;

import static lotto.global.constant.Constant.*;
import static lotto.global.message.Message.*;

public class ConsoleInputHandler implements InputHandler {

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
        System.out.printf((INPUT_PURCHASE_AMOUNT_RESPONSE) + "\n", lottoCount);
        return LottoGenerator.generate(lottoCount);
    }

    private Lotto getWinningLotto() {
        System.out.println(INPUT_WINNING_LOTTO);
        String numbers = Console.readLine();

        List<Integer> lottoNumbers = Arrays.stream(numbers.split(","))
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
