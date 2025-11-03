package lotto.view;

import lotto.Lotto;
import lotto.domain.ProfitRate;
import lotto.domain.Rank;

import java.util.List;
import java.util.Map;

public final class OutputView {

    private OutputView() {}

    public static void printPurchased(List<Lotto> purchasedTickets) {
        System.out.println(purchasedTickets.size() + "개를 구매했습니다.");
        for (Lotto ticket : purchasedTickets) {
            System.out.println(ticket.numbers());
        }
        System.out.println();
    }

    public static void printStatisticsHeader() {
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    public static void printRankCounts(Map<Rank, Long> rankCounts) {
        System.out.printf("3개 일치 (5,000원) - %d개%n", rankCounts.getOrDefault(Rank.FIFTH, 0L));
        System.out.printf("4개 일치 (50,000원) - %d개%n", rankCounts.getOrDefault(Rank.FOURTH, 0L));
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", rankCounts.getOrDefault(Rank.THIRD, 0L));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", rankCounts.getOrDefault(Rank.SECOND, 0L));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", rankCounts.getOrDefault(Rank.FIRST, 0L));
    }

    public static void printProfitRate(ProfitRate profitRate) {
        System.out.printf("총 수익률은 %s입니다.%n", profitRate.asPercentage());
    }

    public static void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}