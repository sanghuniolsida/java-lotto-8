package lotto.parser;

import lotto.Lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InputParsers {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_MONEY_EMPTY = "구입 금액이 비어 있습니다.";
    private static final String ERROR_MONEY_NOT_POSITIVE = "구입 금액은 0보다 커야 합니다.";
    private static final String ERROR_MONEY_NOT_NUMBER = "구입 금액은 숫자여야 합니다.";

    private static final String ERROR_WINNING_EMPTY = "당첨 번호가 비어 있습니다.";
    private static final String ERROR_WINNING_NOT_NUMBER = "당첨 번호는 쉼표로 구분된 숫자여야 합니다.";

    private static final String ERROR_BONUS_EMPTY = "보너스 번호가 비어 있습니다.";
    private static final String ERROR_BONUS_NOT_NUMBER = "보너스 번호는 숫자여야 합니다.";


    private InputParsers() {}

    public static long parseMoney(String purchaseAmountInput) {
        if (purchaseAmountInput == null || purchaseAmountInput.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_MONEY_EMPTY);
        }
        try {
            long purchaseAmount = Long.parseLong(purchaseAmountInput.trim());
            if (purchaseAmount <= 0) {
                throw new IllegalArgumentException(ERROR_PREFIX + ERROR_MONEY_NOT_POSITIVE);
            }
            return purchaseAmount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_MONEY_NOT_NUMBER);
        }
    }

    public static Lotto parseWinningNumbers(String winningNumbersInput) {
        if (winningNumbersInput == null || winningNumbersInput.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_WINNING_EMPTY);
        }
        try {
            List<Integer> winningNumbers = Arrays.stream(winningNumbersInput.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return new Lotto(winningNumbers);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_WINNING_NOT_NUMBER);
        }
    }

    public static int parseBonus(String bonusNumberInput) {
        if (bonusNumberInput == null || bonusNumberInput.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_BONUS_EMPTY);
        }
        try {
            return Integer.parseInt(bonusNumberInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_BONUS_NOT_NUMBER);
        }
    }
}
