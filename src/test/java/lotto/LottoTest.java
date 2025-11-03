package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Lotto: 번호 검증 및 제공 형태")
class LottoTest {

    @Test
    @DisplayName("번호가 6개가 아니면 예외([ERROR]) - 7개")
    void size_over_six_throws() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("번호가 6개가 아니면 예외([ERROR]) - 5개")
    void size_under_six_throws() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("null 리스트 입력 시 예외([ERROR])")
    void null_list_throws() {
        assertThatThrownBy(() -> new Lotto(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("null 요소가 포함되면 예외([ERROR])")
    void contains_null_throws() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, null, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("중복 숫자가 있으면 예외([ERROR])")
    void duplicated_numbers_throw() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("범위(1~45)를 벗어나면 예외([ERROR]) - 0 포함")
    void out_of_range_low_throws() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("범위(1~45)를 벗어나면 예외([ERROR]) - 46 포함")
    void out_of_range_high_throws() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @Test
    @DisplayName("numbers()는 오름차순 정렬된 불변 리스트를 제공한다")
    void numbers_returns_sorted_immutable_list() {
        Lotto lotto = new Lotto(List.of(8, 1, 45, 3, 5, 22));
        assertThat(lotto.numbers()).containsExactly(1, 3, 5, 8, 22, 45);

        // 불변성 확인
        assertThatThrownBy(() -> lotto.numbers().add(99))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}