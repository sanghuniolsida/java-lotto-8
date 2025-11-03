package lotto.domain;

public enum Rank {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5_000L),
    MISS(0, false, 0L);

    private final int matchCount;
    private final boolean requiresBonus;
    private final long prize;

    Rank(int matchCount, boolean requiresBonus, long prize) {
        this.matchCount = matchCount;
        this.requiresBonus = requiresBonus;
        this.prize = prize;
    }

    public static Rank fromMatchResult(int matchCount, boolean bonusMatched) {
        for (Rank rank : values()) {
            if (rank.matches(matchCount, bonusMatched)) {
                return rank;
            }
        }
        return MISS;
    }

    private boolean matches(int matchCount, boolean bonusMatched) {
        if (this == MISS) return true;
        return this.matchCount == matchCount
                && (!this.requiresBonus || bonusMatched);
    }

    public long prize() {
        return prize;
    }
}