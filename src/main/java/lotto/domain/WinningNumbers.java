package lotto.domain;

import lotto.Lotto;

import java.util.List;

public final class WinningNumbers {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final Lotto winning; // 메인 당첨 번호 6개
    private final int bonus;     // 보너스 번호 1개

    public WinningNumbers(Lotto winning, int bonus) {
        validateWinningNotNull(winning);
        validateBonusRange(bonus);
        validateNoOverlap(winning, bonus);
        this.winning = winning;
        this.bonus = bonus;
    }

    public Lotto winning() { return winning; }
    public int bonus() { return bonus; }

    /** 티켓의 등수를 스스로 판정한다 (Tell, Don't Ask). */
    public Rank rankOf(Lotto ticket) {
        validateTicketNotNull(ticket);

        List<Integer> sortedTicketNumbers = ticket.numbers();   // Lotto가 오름차순 보장
        List<Integer> sortedWinningNumbers = winning.numbers();

        int matchCount = countMatchingNumbers(sortedTicketNumbers, sortedWinningNumbers);
        boolean isBonusMatched = sortedTicketNumbers.contains(bonus);


        return Rank.fromMatchResult(matchCount, isBonusMatched);
    }

    /** 정렬된 두 리스트의 교집합 개수(일치 개수)를 센다. */
    private int countMatchingNumbers(List<Integer> sortedTicketNumbers,
                                     List<Integer> sortedWinningNumbers) {
        int i = 0, j = 0, matchCount = 0;
        while (i < sortedTicketNumbers.size() && j < sortedWinningNumbers.size()) {
            int ticketValue = sortedTicketNumbers.get(i);
            int winningValue = sortedWinningNumbers.get(j);
            if (ticketValue == winningValue) {
                matchCount++;
                i++;
                j++;
                continue;
            }
            if (ticketValue < winningValue) {
                i++;
                continue;
            }
            j++;
        }
        return matchCount;
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