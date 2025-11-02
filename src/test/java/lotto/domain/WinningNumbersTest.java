package lotto.domain;

import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("WinningNumbers 테스트")
class WinningNumbersTest {

    @Test
    @DisplayName("정상 생성")
    void create_valid() {
        Lotto mainWinningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(mainWinningNumbers, 7);

        assertThat(winningNumbers.winning().numbers()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(winningNumbers.bonus()).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스가 메인과 중복이면 예외")
    void bonus_overlaps_with_winning_throws() {
        Lotto mainWinningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningNumbers(mainWinningNumbers, 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("보너스가 범위를 벗어나면 예외")
    void bonus_out_of_range_throws() {
        Lotto mainWinningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningNumbers(mainWinningNumbers, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
        assertThatThrownBy(() -> new WinningNumbers(mainWinningNumbers, 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("경계값 OK: 1 또는 45(메인에 없으면 허용)")
    void boundary_bonus_1_or_45_ok_when_not_in_winning() {
        Lotto mainWinningNumbers1 = new Lotto(List.of(2, 3, 4, 5, 6, 7));
        assertThatCode(() -> new WinningNumbers(mainWinningNumbers1, 1)).doesNotThrowAnyException();

        Lotto mainWinningNumbers2 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatCode(() -> new WinningNumbers(mainWinningNumbers2, 45)).doesNotThrowAnyException();
    }
}