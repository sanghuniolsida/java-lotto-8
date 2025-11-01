package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    @Test
    void 금액이_양수이고_천원단위면_생성된다() {
        Money money = Money.of(8000);
        assertThat(money.toTicketCount()).isEqualTo(8);
        assertThat(money.value()).isEqualTo(8000);
    }

    @Test
    void 금액이_0이하이면_예외() {
        assertThatThrownBy(() -> Money.of(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    void 천원단위가_아니면_예외() {
        assertThatThrownBy(() -> Money.of(7500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}
