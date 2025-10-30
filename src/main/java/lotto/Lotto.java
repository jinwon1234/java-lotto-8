package lotto;

import lotto.global.message.ErrorMessage;

import java.util.List;

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

    @Override
    public String toString() {
        return numbers.toString();
    }
}
