package lotto.domain;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {

    @Test
    void 정상_생성() {
        LottoNumbers winning = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers w = new WinningNumbers(winning, 7);

        assertThat(w.winning().getSortedNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(w.bonus()).isEqualTo(7);
    }

    @Test
    void 보너스가_메인과_중복이면_예외() {
        LottoNumbers winning = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningNumbers(winning, 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    void 보너스가_범위를_벗어나면_예외() {
        LottoNumbers winning = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningNumbers(winning, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
        assertThatThrownBy(() -> new WinningNumbers(winning, 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    void 경계값_OK_보너스가_1이나_45여도_메인에_없으면_허용() {
        LottoNumbers winning = new LottoNumbers(List.of(2, 3, 4, 5, 6, 7));
        assertThatCode(() -> new WinningNumbers(winning, 1)).doesNotThrowAnyException();

        LottoNumbers winning2 = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        assertThatCode(() -> new WinningNumbers(winning2, 45)).doesNotThrowAnyException();
    }
}