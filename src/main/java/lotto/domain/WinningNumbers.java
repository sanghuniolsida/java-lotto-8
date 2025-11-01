package lotto.domain;

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

    private void validateWinningNotNull(LottoNumbers winningNumbers) {
        if (winningNumbers == null) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호가 null입니다.");
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