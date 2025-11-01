package lotto.domain;

public final class Money {
    private static final int LOTTO_PRICE = 1_000;

    private final long amount;

    private Money(long amount) {
        validatePositive(amount);
        validateUnit(amount);
        this.amount = amount;
    }

    public static Money of(long amount) {
        return new Money(amount);
    }

    public int toTicketCount() {
        return (int) (amount / LOTTO_PRICE);
    }

    public long value() {
        return amount;
    }

    private void validatePositive(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0보다 커야 합니다.");
        }
    }

    private void validateUnit(long value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }
}