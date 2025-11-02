package lotto.domain;

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
    @DisplayName("Money.lottoCount() 장수만큼 로또를 발행한다")
    void issues_tickets_by_money_count() {
        Money money = Money.of(3_000);
        NumberGenerator gen = new StubNumberGenerator(List.of(
                List.of(8, 1, 45, 3, 5, 22),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 9, 11, 13, 15, 17)
        ));
        LottoMachine machine = new LottoMachine(gen);

        List<LottoNumbers> tickets = machine.generateTickets(money);

        assertThat(tickets).hasSize(3);
        assertThat(tickets.get(0).getSortedNumbers()).containsExactly(1, 3, 5, 8, 22, 45);
        assertThat(tickets.get(1).getSortedNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("제네레이터가 잘못된 값을 주면 LottoNumbers가 예외를 던진다")
    void invalid_sequence_causes_exception() {
        Money money = Money.of(1_000);
        NumberGenerator gen = new StubNumberGenerator(List.of(
                List.of(1, 1, 2, 3, 4, 5) // 중복 → LottoNumbers에서 예외
        ));
        LottoMachine machine = new LottoMachine(gen);

        assertThatThrownBy(() -> machine.generateTickets(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}