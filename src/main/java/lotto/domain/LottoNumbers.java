package lotto.domain;

import java.util.*;

public final class LottoNumbers {
    private static final int REQUIRED_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public LottoNumbers(List<Integer> inputNumbers) {
        validateSize(inputNumbers);
        validateRange(inputNumbers);
        validateUnique(inputNumbers);
        this.numbers = toSortedUnmodifiable(inputNumbers);
    }

    public List<Integer> getSortedNumbers() {
        return numbers;
    }

    private void validateSize(List<Integer> inputNumbers) {
        if (inputNumbers == null) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 목록이 null입니다.");
        }
        if (inputNumbers.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateRange(List<Integer> inputNumbers) {
        for (Integer number : inputNumbers) {
            if (number == null) {
                throw new IllegalArgumentException("[ERROR] 로또 번호에 null 값이 포함되어 있습니다.");
            }
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
            }
        }
    }

    private void validateUnique(List<Integer> inputNumbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(inputNumbers);
        if (uniqueNumbers.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    /* 입력 리스트를 오름차순 정렬된 불변 리스트로 변환 */
    private List<Integer> toSortedUnmodifiable(List<Integer> inputNumbers) {
        List<Integer> sortedNumbers = new ArrayList<>(inputNumbers);
        Collections.sort(sortedNumbers);
        return List.copyOf(sortedNumbers);
    }
}