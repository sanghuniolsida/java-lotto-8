package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ProfitRate {
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
                .multiply(BigDecimal.valueOf(100))
                .divide(purchaseAmountDecimal, 1, RoundingMode.HALF_UP);

        return new ProfitRate(profitRateValue.toPlainString() + "%");
    }

    public String asPercentage() {
        return percentage;
    }

    private static void validatePurchasePositive(long purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구매 금액이 0보다 커야 수익률을 계산할 수 있습니다.");
        }
    }

    // 음수 검증은 필요 없을 수 있겠지만, 방어적 가드로 사용하기 위해 작성했음
    private static void validatePrizeNotNegative(long totalPrize) {
        if (totalPrize < 0) {
            throw new IllegalArgumentException("[ERROR] 총 상금은 음수가 될 수 없습니다."); // 0원은 가능
        }
    }
}