package lotto.domain;

import java.util.List;

public final class WinningNumbers {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private final LottoNumbers winning;
    private final int bonus;

    public WinningNumbers(LottoNumbers winning, int bonus) {
        validateWinningNotNull(winning);
        validateBonusRange(bonus);
        validateNoOverlap(winning, bonus);
        this.winning = winning;
        this.bonus = bonus;
    }

    public LottoNumbers winning() {
        return winning;
    }

    public int bonus() {
        return bonus;
    }

    /** 티켓 번호에 대한 등수를 스스로 판정한다(Tell, Don't Ask). */
    public Rank rankOf(LottoNumbers ticketNumbers) {
        validateTicketNotNull(ticketNumbers);

        List<Integer> sortedTicketNumbers = ticketNumbers.getSortedNumbers();
        List<Integer> sortedWinningNumbers = winning.getSortedNumbers();

        int matchCount = countMatchingNumbers(sortedTicketNumbers, sortedWinningNumbers);
        boolean isBonusMatched = sortedTicketNumbers.contains(bonus);

        return Rank.fromMatchResult(matchCount, isBonusMatched);
    }

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

    private void validateWinningNotNull(LottoNumbers winningNumbers) {
        if (winningNumbers == null) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호가 null입니다.");
        }
    }

    private void validateTicketNotNull(LottoNumbers ticketNumbers) {
        if (ticketNumbers == null) {
            throw new IllegalArgumentException("[ERROR] 비교할 티켓 번호가 null입니다.");
        }
    }

    private void validateBonusRange(int bonusNumber) {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 사이여야 합니다.");
        }
    }

    private void validateNoOverlap(LottoNumbers winningNumbers, int bonusNumber) {
        if (winningNumbers.getSortedNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}