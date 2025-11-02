package lotto.view;

import lotto.domain.LottoNumbers;
import lotto.domain.ProfitRate;
import lotto.domain.Rank;

import java.util.List;
import java.util.Map;

public final class OutputView {

    private OutputView() {}

    public static void printPurchased(List<LottoNumbers> tickets) {
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (LottoNumbers t : tickets) {
            System.out.println(t.getSortedNumbers());
        }
        System.out.println();
    }

    public static void printStatisticsHeader() {
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    public static void printRankCounts(Map<Rank, Long> counts) {
        System.out.printf("3개 일치 (5,000원) - %d개%n", counts.getOrDefault(Rank.FIFTH, 0L));
        System.out.printf("4개 일치 (50,000원) - %d개%n", counts.getOrDefault(Rank.FOURTH, 0L));
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", counts.getOrDefault(Rank.THIRD, 0L));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", counts.getOrDefault(Rank.SECOND, 0L));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", counts.getOrDefault(Rank.FIRST, 0L));
    }

    public static void printProfitRate(ProfitRate rate) {
        System.out.printf("총 수익률은 %s입니다.%n", rate.asPercentage());
    }

    public static void printError(String message) {
        System.out.println(message);
    }
}
