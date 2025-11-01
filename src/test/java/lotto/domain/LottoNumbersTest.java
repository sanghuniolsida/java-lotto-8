package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoNumbersTest {

    @Test
    void 유효한_6개면_생성되고_오름차순정렬보장() {
        LottoNumbers numbers = new LottoNumbers(List.of(8, 1, 45, 3, 5, 22));
        assertThat(numbers.getSortedNumbers()).containsExactly(1, 3, 5, 8, 22, 45);
    }

    @Test
    void 개수가_6개가_아니면_예외() {
        assertThatThrownBy(() -> new LottoNumbers(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    void 범위를_벗어나면_예외() {
        assertThatThrownBy(() -> new LottoNumbers(List.of(0, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    void 중복이_있으면_예외() {
        assertThatThrownBy(() -> new LottoNumbers(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}
