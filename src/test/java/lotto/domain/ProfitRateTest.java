package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("ProfitRate(수익률) 계산/포맷 테스트")
class ProfitRateTest {

    @Test
    @DisplayName("62.5%처럼 소수 한 자리로 반올림하여 문자열 반환")
    void formats_to_one_decimal_percent() {
        ProfitRate rate = ProfitRate.calculateRate(5_000, 8_000);
        assertThat(rate.asPercentage()).isEqualTo("62.5%");
    }

    @Test
    @DisplayName("정확히 100.0% 포맷")
    void formats_exact_100_percent() {
        ProfitRate rate = ProfitRate.calculateRate(8_000, 8_000);
        assertThat(rate.asPercentage()).isEqualTo("100.0%");
    }

    @Test
    @DisplayName("상금 0이면 0.0%")
    void formats_zero_percent() {
        ProfitRate rate = ProfitRate.calculateRate(0, 8_000);
        assertThat(rate.asPercentage()).isEqualTo("0.0%");
    }

    @Test
    @DisplayName("구매 금액이 0 이하이면 [ERROR]로 시작하는 예외")
    void throws_when_purchase_amount_not_positive() {
        assertThatThrownBy(() -> ProfitRate.calculateRate(0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
        assertThatThrownBy(() -> ProfitRate.calculateRate(1000, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("총 상금이 음수이면 [ERROR]로 시작하는 예외")
    void throws_when_total_prize_is_negative() {
        assertThatThrownBy(() -> ProfitRate.calculateRate(-1, 8_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

}
