package lotto.domain;

import lotto.Lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class WinningNumbers {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final Lotto winning;
    private final int bonus;

    public WinningNumbers(Lotto winning, int bonus) {
        validateWinningNotNull(winning);
        validateBonusRange(bonus);
        validateNoOverlap(winning, bonus);
        this.winning = winning;
        this.bonus = bonus;
    }

    public Lotto winning() { return winning; }
    public int bonus() { return bonus; }

    // 티켓의 등수를 스스로 판정한다 (Tell, Don't Ask)
    public Rank rankOf(Lotto ticket) {
        validateTicketNotNull(ticket);

        List<Integer> ticketNumbers = ticket.numbers();

        int matchCount = countMatches(ticketNumbers);
        boolean bonusMatched = hasBonusMatch(ticketNumbers);

        return Rank.fromMatchResult(matchCount, bonusMatched);
    }

    private int countMatches(List<Integer> ticketNumbers) {
        List<Integer> winningNumbers = winning.numbers();

        Set<Integer> ticketSet = new HashSet<>(ticketNumbers);
        int matches = 0;
        for (int number : winningNumbers) {
            if (ticketSet.contains(number)) {
                matches++;
            }
        }
        return matches;
    }

    private boolean hasBonusMatch(List<Integer> ticketNumbers) {
        return ticketNumbers.contains(bonus);
    }

    private void validateWinningNotNull(Lotto winningNumbers) {
        if (winningNumbers == null) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호가 null입니다.");
        }
    }

    private void validateTicketNotNull(Lotto ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("[ERROR] 비교할 티켓 번호가 null입니다.");
        }
    }

    private void validateBonusRange(int bonusNumber) {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 사이여야 합니다.");
        }
    }

    private void validateNoOverlap(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.numbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}