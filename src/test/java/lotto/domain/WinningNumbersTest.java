package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("WinningNumbers 테스트")
class WinningNumbersTest {

    @Test
    @DisplayName("정상 생성")
    void create_valid() {
        LottoNumbers winning = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers w = new WinningNumbers(winning, 7);

        assertThat(w.winning().getSortedNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(w.bonus()).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스가 메인과 중복이면 예외")
    void bonus_overlaps_with_winning_throws() {
        LottoNumbers winning = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningNumbers(winning, 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("보너스가 범위를 벗어나면 예외")
    void bonus_out_of_range_throws() {
        LottoNumbers winning = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningNumbers(winning, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
        assertThatThrownBy(() -> new WinningNumbers(winning, 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("경계값 OK: 1 또는 45(메인에 없으면 허용)")
    void boundary_bonus_1_or_45_ok_when_not_in_winning() {
        LottoNumbers winning = new LottoNumbers(List.of(2, 3, 4, 5, 6, 7));
        assertThatCode(() -> new WinningNumbers(winning, 1)).doesNotThrowAnyException();

        LottoNumbers winning2 = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        assertThatCode(() -> new WinningNumbers(winning2, 45)).doesNotThrowAnyException();
    }
}