package lotto.parser;

import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("InputParsers: 당첨/보너스 입력 파싱")
class InputParsersTest {

    @Test
    @DisplayName("쉼표로 구분된 6개 숫자 문자열을 Lotto로 파싱")
    void parseWinningNumbers_ok() {
        Lotto lotto = InputParsers.parseWinningNumbers("1,2,3,4,5,6");
        assertThat(lotto.numbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("숫자가 아닌 값/개수 불일치/공백은 예외([ERROR])")
    void parseWinningNumbers_error() {
        assertThatThrownBy(() -> InputParsers.parseWinningNumbers("1,2,3,4,5,a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
        assertThatThrownBy(() -> InputParsers.parseWinningNumbers("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
        assertThatThrownBy(() -> InputParsers.parseWinningNumbers("  "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("보너스 번호 문자열을 int로 파싱")
    void parseBonus_ok() {
        assertThat(InputParsers.parseBonus("7")).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호가 숫자 아님/빈 문자열이면 예외([ERROR])")
    void parseBonus_error() {
        assertThatThrownBy(() -> InputParsers.parseBonus("x"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
        assertThatThrownBy(() -> InputParsers.parseBonus(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("구입 금액 문자열을 long으로 파싱")
    void parseMoney_ok() {
        assertThat(InputParsers.parseMoney("8000")).isEqualTo(8000L);
    }

    @Test
    @DisplayName("구입 금액이 숫자 아님/음수/빈 문자열이면 예외([ERROR])")
    void parseMoney_error() {
        assertThatThrownBy(() -> InputParsers.parseMoney("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
        assertThatThrownBy(() -> InputParsers.parseMoney("-1000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
        assertThatThrownBy(() -> InputParsers.parseMoney(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}