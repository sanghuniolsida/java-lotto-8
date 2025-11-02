package lotto.domain;

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
        LottoNumbers mainWinning = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winning = new WinningNumbers(mainWinning, 7);

        LottoNumbers third = new LottoNumbers(List.of(1, 2, 3, 4, 5, 8));
        LottoNumbers fifth = new LottoNumbers(List.of(1, 2, 3, 40, 41, 42));
        LottoNumbers miss  = new LottoNumbers(List.of(8, 9, 10, 11, 12, 13));
        LottoNumbers second = new LottoNumbers(List.of(1, 2, 3, 4, 5, 7));

        List<LottoNumbers> tickets = List.of(third, fifth, miss, second);
        Money purchase = Money.of(4_000); // 4장

        Statistics.Result result = Statistics.calculate(tickets, winning, purchase);

        Map<Rank, Long> counts = result.rankCount();
        assertThat(counts.getOrDefault(Rank.SECOND, 0L)).isEqualTo(1L);
        assertThat(counts.getOrDefault(Rank.THIRD, 0L)).isEqualTo(1L);
        assertThat(counts.getOrDefault(Rank.FIFTH, 0L)).isEqualTo(1L);
        assertThat(counts.getOrDefault(Rank.MISS, 0L)).isEqualTo(1L);

        assertThat(result.totalPrize()).isEqualTo(30_000_000L + 1_500_000L + 5_000L);

        assertThat(result.profitRate().asPercentage())
                .isNotBlank()
                .endsWith("%");
    }

    @Test
    @DisplayName("티켓이 비어도 총상금 0, 수익률 0.0%가 계산된다")
    void empty_tickets_results_in_zero() {
        WinningNumbers winning = new WinningNumbers(
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)), 7);

        Statistics.Result result = Statistics.calculate(
                List.of(), winning, Money.of(1_000));

        assertThat(result.rankCount().values().stream().mapToLong(Long::longValue).sum()).isZero();
        assertThat(result.totalPrize()).isZero();
        assertThat(result.profitRate().asPercentage()).isEqualTo("0.0%");
    }
}
