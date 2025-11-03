package lotto.domain;

import java.util.List;

import static lotto.constant.Constant.*;
import static lotto.message.ErrorMessage.*;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_COUNT);
        }

        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .filter(number -> {
                    if (number > LOTTO_MAX_NUM || number < LOTTO_MIN_NUM) {
                        throw new IllegalArgumentException(INVALID_LOTTO_NUMBER);
                    }
                    return true;
                }).toList();

        if (distinctNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBERS);
        }
    }

    // TODO: 추가 기능 구현


    public List<Integer> getNumbers() {
        return numbers;
    }

    public LottoRank getRank(WinningLotto winningLotto) {
        List<Integer> winningLottoNumbers = winningLotto.getLotto().getNumbers();
        List<Integer> myLottoNumbers = this.getNumbers();

        int matchCount = 0;
        boolean matchBonusNumber = false;

        for (Integer myLottoNumber : myLottoNumbers) {
            if (winningLottoNumbers.contains(myLottoNumber)) matchCount++;
            else if (myLottoNumber.equals(winningLotto.getBonusNumber())) matchBonusNumber = true;
        }

        return LottoRank.calculateRank(matchCount, matchBonusNumber);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
