package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Rank 등수 판정/상금 테스트")
class RankTest {

    @Test
    @DisplayName("일치수와 보너스 여부로 등수 결정")
    void decide_rank_by_matches_and_bonus() {
        assertThat(Rank.from(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.from(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.from(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.from(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.from(3, false)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.from(2, false)).isEqualTo(Rank.MISS);
        assertThat(Rank.from(0, false)).isEqualTo(Rank.MISS);
    }

    @Test
    @DisplayName("각 등수의 상금이 요구사항과 일치")
    void prize_values_match_spec() {
        assertThat(Rank.FIRST.prize()).isEqualTo(2_000_000_000L);
        assertThat(Rank.SECOND.prize()).isEqualTo(30_000_000L);
        assertThat(Rank.THIRD.prize()).isEqualTo(1_500_000L);
        assertThat(Rank.FOURTH.prize()).isEqualTo(50_000L);
        assertThat(Rank.FIFTH.prize()).isEqualTo(5_000L);
        assertThat(Rank.MISS.prize()).isZero();
    }
}
