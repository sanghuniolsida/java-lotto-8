package lotto.domain;

import lotto.Lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class Statistics {

    private Statistics() {}

    public static Result calculate(List<Lotto> tickets,
                                   WinningNumbers winning,
                                   Money purchase) {
        validateInputsNotNull(tickets, winning, purchase);

        Map<Rank, Long> rankCounts = countRanksByTickets(tickets, winning);
        long totalPrize = calculateTotalPrize(rankCounts);
        ProfitRate profitRate = ProfitRate.calculateRate(totalPrize, purchase.amount());

        return new Result(rankCounts, totalPrize, profitRate);
    }

    private static Map<Rank, Long> countRanksByTickets(List<Lotto> tickets,
                                                       WinningNumbers winning) {
        Map<Rank, Long> rankCounts = initializeRankCounts();

        for (Lotto ticket : tickets) {
            Rank rank = winning.rankOf(ticket);
            rankCounts.put(rank, rankCounts.get(rank) + 1);
        }

        return rankCounts;
    }

    private static long calculateTotalPrize(Map<Rank, Long> rankCounts) {
        long total = 0L;
        for (Map.Entry<Rank, Long> entry : rankCounts.entrySet()) {
            total += entry.getKey().prize() * entry.getValue();
        }
        return total;
    }

    private static void validateInputsNotNull(List<Lotto> tickets,
                                              WinningNumbers winning,
                                              Money purchase) {
        if (tickets == null) {
            throw new IllegalArgumentException("[ERROR] 티켓 목록은 null일 수 없습니다.");
        }
        if (winning == null) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 null일 수 없습니다.");
        }
        if (purchase == null) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 null일 수 없습니다.");
        }
    }

    private static Map<Rank, Long> initializeRankCounts() {
        Map<Rank, Long> map = new EnumMap<>(Rank.class);
        for (Rank r : Rank.values()) {
            map.put(r, 0L);
        }
        return map;
    }

    /** 집계 결과 VO */
    public static final class Result {
        private final Map<Rank, Long> rankCount;
        private final long totalPrize;
        private final ProfitRate profitRate;

        public Result(Map<Rank, Long> rankCount, long totalPrize, ProfitRate profitRate) {
            this.rankCount = Map.copyOf(rankCount);
            this.totalPrize = totalPrize;
            this.profitRate = profitRate;
        }

        public Map<Rank, Long> rankCount() { return rankCount; }
        public long totalPrize() { return totalPrize; }
        public ProfitRate profitRate() { return profitRate; }
    }
}
