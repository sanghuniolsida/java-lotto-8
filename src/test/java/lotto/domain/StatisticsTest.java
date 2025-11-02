package lotto.domain;

import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Statistics: 등수 집계/총상금/수익률 계산")
class StatisticsTest {

    @Test
    @DisplayName("여러 티켓을 집계하여 등수별 개수, 총상금, 수익률을 계산한다")
    void aggregate_ranks_total_prize_and_profit_rate() {
        Lotto mainWinning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(mainWinning, 7);

        Lotto thirdPrizeTicket  = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto fifthPrizeTicket  = new Lotto(List.of(1, 2, 3, 40, 41, 42));
        Lotto missTicket        = new Lotto(List.of(8, 9, 10, 11, 12, 13));
        Lotto secondPrizeTicket = new Lotto(List.of(1, 2, 3, 4, 5, 7)); // 5개+보너스

        List<Lotto> purchasedTickets = List.of(
                thirdPrizeTicket, fifthPrizeTicket, missTicket, secondPrizeTicket
        );
        Money purchaseMoney = Money.of(4_000); // 4장

        Statistics.Result result = Statistics.calculate(purchasedTickets, winningNumbers, purchaseMoney);

        Map<Rank, Long> rankCounts = result.rankCount();
        assertThat(rankCounts.getOrDefault(Rank.SECOND, 0L)).isEqualTo(1L);
        assertThat(rankCounts.getOrDefault(Rank.THIRD, 0L)).isEqualTo(1L);
        assertThat(rankCounts.getOrDefault(Rank.FIFTH, 0L)).isEqualTo(1L);
        assertThat(rankCounts.getOrDefault(Rank.MISS, 0L)).isEqualTo(1L);

        assertThat(result.totalPrize()).isEqualTo(30_000_000L + 1_500_000L + 5_000L);

        assertThat(result.profitRate().asPercentage())
                .isNotBlank()
                .endsWith("%");
    }

    @Test
    @DisplayName("티켓이 비어도 총상금 0, 수익률 0.0%가 계산된다")
    void empty_tickets_results_in_zero() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7
        );

        Statistics.Result result = Statistics.calculate(
                List.of(), winningNumbers, Money.of(1_000)
        );

        assertThat(result.rankCount().values().stream().mapToLong(Long::longValue).sum()).isZero();
        assertThat(result.totalPrize()).isZero();
        assertThat(result.profitRate().asPercentage()).isEqualTo("0.0%");
    }
}