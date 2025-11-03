package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ProfitRate {
    private static final BigDecimal PERCENTAGE_MULTIPLIER = BigDecimal.valueOf(100);
    private static final int DECIMAL_SCALE = 1;
    private static final String PERCENTAGE_SUFFIX = "%";

    private static final String ERROR_PURCHASE_NON_POSITIVE = "[ERROR] 구매 금액이 0보다 커야 수익률을 계산할 수 있습니다.";
    private static final String ERROR_NEGATIVE_PRIZE = "[ERROR] 총 상금은 음수가 될 수 없습니다.";


    private final String percentage;

    private ProfitRate(String percentage) {
        this.percentage = percentage;
    }

    public static ProfitRate calculateRate(long totalPrize, long purchaseAmount) {
        validatePurchasePositive(purchaseAmount);
        validatePrizeNotNegative(totalPrize);

        BigDecimal prizeAmount = BigDecimal.valueOf(totalPrize);
        BigDecimal purchaseAmountDecimal = BigDecimal.valueOf(purchaseAmount);

        BigDecimal profitRateValue = prizeAmount
                .multiply(PERCENTAGE_MULTIPLIER)
                .divide(purchaseAmountDecimal, DECIMAL_SCALE, RoundingMode.HALF_UP);

        return new ProfitRate(profitRateValue.toPlainString() + PERCENTAGE_SUFFIX);
    }

    public String asPercentage() {
        return percentage;
    }

    private static void validatePurchasePositive(long purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(ERROR_PURCHASE_NON_POSITIVE);
        }
    }

    private static void validatePrizeNotNegative(long totalPrize) {
        if (totalPrize < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_PRIZE);
        }
    }
}