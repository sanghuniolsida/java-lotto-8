package lotto.domain;

public final class Money {
    private static final int LOTTO_PRICE = 1_000;
    private static final String ERROR_NOT_POSITIVE = "[ERROR] 구입 금액은 0보다 커야 합니다.";
    private static final String ERROR_NOT_MULTIPLE_OF_LOTTO_PRICE = "[ERROR] 구입 금액은 1,000원 단위여야 합니다.";


    private final long amountInWon; // 금액(원)

    private Money(long amountInWon) {
        validateGreaterThanZero(amountInWon);
        validateMultipleOfLottoPrice(amountInWon);
        this.amountInWon = amountInWon;
    }

    public static Money of(long amountInWon) {
        return new Money(amountInWon);
    }

    public int lottoCount() {
        return (int) (amountInWon / LOTTO_PRICE);
    }

    public long amount() {
        return amountInWon;
    }

    private void validateGreaterThanZero(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException(ERROR_NOT_POSITIVE);
        }
    }

    private void validateMultipleOfLottoPrice(long value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_NOT_MULTIPLE_OF_LOTTO_PRICE);
        }
    }
}