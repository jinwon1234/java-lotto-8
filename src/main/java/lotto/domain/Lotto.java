package lotto.domain;

import java.util.List;

import static lotto.domain.LottoRank.*;
import static lotto.global.message.ErrorMessage.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(INVALID_LOTTO_COUNT);
        }

        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .filter(number -> {
                    if (number > 45 || number < 0) {
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

    public LottoRank getRank(Lotto winningLotto, int bonusNumber) {
        List<Integer> winningLottoNumbers = winningLotto.getNumbers();
        List<Integer> myLottoNumbers = this.getNumbers();

        int count = 0;
        boolean matchBonusNumber = false;

        for (Integer myLottoNumber : myLottoNumbers) {
            if (winningLottoNumbers.contains(myLottoNumber)) count++;
            else if (myLottoNumber.equals(bonusNumber)) matchBonusNumber = true;
        }

        if (count == 6) return FIRST;
        if (count == 5 && matchBonusNumber) return SECOND;
        if (count == 5) return THIRD;
        if (count == 4) return FOURTH;
        if (count == 3) return FIFTH;
        return NONE;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
