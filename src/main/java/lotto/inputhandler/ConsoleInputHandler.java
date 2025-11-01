package lotto.inputhandler;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.dto.LottoRequestDto;
import lotto.global.util.InputVerifier;
import lotto.global.util.LottoGenerator;
import lotto.global.util.RetryHandler;

import java.util.Arrays;
import java.util.List;

import static lotto.global.constant.Constant.*;
import static lotto.global.message.ErrorMessage.*;
import static lotto.global.message.Message.*;

public class ConsoleInputHandler implements InputHandler {

    public LottoRequestDto getInput() {
        List<Lotto> myLotto = RetryHandler.retryUntilValid(this::getMyLottos);

        myLotto.forEach(lotto -> System.out.println(lotto.toString()));

        Lotto winningLotto = RetryHandler.retryUntilValid(this::getWinningLotto);

        Integer bonusNumber = RetryHandler.retryUntilValid(this::getBonusNumber);

        return new LottoRequestDto(myLotto, winningLotto, bonusNumber);
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

    private int getBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);
        int bonusNumber = InputVerifier.safeParseInt(Console.readLine());

        if (bonusNumber < 0 || bonusNumber > 45) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER);
        }

        return bonusNumber;
    }
}
