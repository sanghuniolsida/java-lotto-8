package lotto.parser;

import lotto.Lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InputParsers {

    private static final String ERROR_PREFIX = "[ERROR] ";

    private InputParsers() {}

    public static long parseMoney(String purchaseAmountInput) {
        if (purchaseAmountInput == null || purchaseAmountInput.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구입 금액이 비어 있습니다.");
        }
        try {
            long purchaseAmount = Long.parseLong(purchaseAmountInput.trim());
            if (purchaseAmount <= 0) {
                throw new IllegalArgumentException(ERROR_PREFIX + "구입 금액은 0보다 커야 합니다.");
            }
            return purchaseAmount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구입 금액은 숫자여야 합니다.");
        }
    }

    public static Lotto parseWinningNumbers(String winningNumbersInput) {
        if (winningNumbersInput == null || winningNumbersInput.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호가 비어 있습니다.");
        }
        try {
            List<Integer> winningNumbers = Arrays.stream(winningNumbersInput.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return new Lotto(winningNumbers);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 쉼표로 구분된 숫자여야 합니다.");
        }
    }

    public static int parseBonus(String bonusNumberInput) {
        if (bonusNumberInput == null || bonusNumberInput.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_PREFIX + "보너스 번호가 비어 있습니다.");
        }
        try {
            return Integer.parseInt(bonusNumberInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "보너스 번호는 숫자여야 합니다.");
        }
    }
}
