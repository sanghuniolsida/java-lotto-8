package lotto.parser;

import lotto.domain.LottoNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InputParsers {

    private static final String ERROR_PREFIX = "[ERROR] ";

    private InputParsers() {}

    public static long parseMoney(String inputString) {
        if (inputString == null || inputString.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구입 금액이 비어 있습니다.");
        }
        try {
            long purchaseAmount = Long.parseLong(inputString.trim());
            if (purchaseAmount <= 0) {
                throw new IllegalArgumentException(ERROR_PREFIX + "구입 금액은 0보다 커야 합니다.");
            }
            return purchaseAmount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구입 금액은 숫자여야 합니다.");
        }
    }

    public static LottoNumbers parseWinningNumbers(String raw) {
        if (raw == null || raw.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호가 비어 있습니다.");
        }
        try {
            List<Integer> winningNumberList = Arrays.stream(raw.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return new LottoNumbers(winningNumberList);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 쉼표로 구분된 숫자여야 합니다.");
        }
    }

    public static int parseBonus(String raw) {
        if (raw == null || raw.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_PREFIX + "보너스 번호가 비어 있습니다.");
        }
        try {
            return Integer.parseInt(raw.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "보너스 번호는 숫자여야 합니다.");
        }
    }
}