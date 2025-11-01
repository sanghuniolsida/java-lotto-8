package lotto.domain;

public enum Rank {
    FIRST(2_000_000_000L),
    SECOND(30_000_000L),
    THIRD(1_500_000L),
    FOURTH(50_000L),
    FIFTH(5_000L),
    MISS(0L);

    private final long prize;

    Rank(long prize) {
        this.prize = prize;
    }

    public static Rank fromMatchResult(int matchNumbersCount, boolean bonusNumberMatched) {
        if (matchNumbersCount == 6) return FIRST;
        if (matchNumbersCount == 5 && bonusNumberMatched) return SECOND;
        if (matchNumbersCount == 5) return THIRD;
        if (matchNumbersCount == 4) return FOURTH;
        if (matchNumbersCount == 3) return FIFTH;
        return MISS;
    }

    public long prize() {
        return prize;
    }
}
