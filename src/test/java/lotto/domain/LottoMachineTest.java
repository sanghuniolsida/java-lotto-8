package lotto.domain;

import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("LottoMachine: 구입 금액 만큼 로또 발행")
class LottoMachineTest {

    static class StubNumberGenerator implements NumberGenerator {
        private final List<List<Integer>> sequences;
        private int index = 0;

        StubNumberGenerator(List<List<Integer>> sequences) {
            this.sequences = sequences;
        }

        @Override
        public List<Integer> generateSixUnique() {
            return sequences.get(index++);
        }
    }

    @Test
    @DisplayName("Money.lottoCount() 장수만큼 로또를 생성한다")
    void generates_tickets_by_money_count() {
        Money purchaseMoney = Money.of(3_000);
        NumberGenerator numberGenerator = new StubNumberGenerator(List.of(
                List.of(8, 1, 45, 3, 5, 22),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 9, 11, 13, 15, 17)
        ));
        LottoMachine lottoMachine = new LottoMachine(numberGenerator);

        List<Lotto> tickets = lottoMachine.generateTickets(purchaseMoney);

        assertThat(tickets).hasSize(3);
        assertThat(tickets.get(0).numbers()).containsExactly(1, 3, 5, 8, 22, 45);
        assertThat(tickets.get(1).numbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("제네레이터가 잘못된 값을 주면 Lotto가 예외를 던진다")
    void invalid_sequence_causes_exception() {
        Money purchaseMoney = Money.of(1_000);
        NumberGenerator numberGenerator = new StubNumberGenerator(List.of(
                List.of(1, 1, 2, 3, 4, 5) // 중복 → Lotto 생성자에서 예외
        ));
        LottoMachine lottoMachine = new LottoMachine(numberGenerator);

        assertThatThrownBy(() -> lottoMachine.generateTickets(purchaseMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}