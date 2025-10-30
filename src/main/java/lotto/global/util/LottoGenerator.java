package lotto.global.util;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

import static lotto.global.message.ErrorMessage.INVALID_CONSTRUCTOR;

public final class LottoGenerator {

    private LottoGenerator() {
        throw new AssertionError(INVALID_CONSTRUCTOR);
    }

    public static List<Lotto> generate(int lottoCount) {

        List<Lotto> lottos = new ArrayList<>();

        for (int i=0; i<lottoCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                    .stream().sorted().toList();
            lottos.add(new Lotto(numbers));
        }

        return lottos;
    }
}
