package lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.NumberGenerator;

import java.util.List;

public final class MissionUtilsNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generateSixUnique() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}