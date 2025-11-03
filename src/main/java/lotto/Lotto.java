package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int REQUIRED_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validate(List<Integer> inputNumbers) {
        validateSize(inputNumbers);
        validateElementsNotNull(inputNumbers);
        validateRange(inputNumbers);
        validateUnique(inputNumbers);
    }

    private void validateSize(List<Integer> inputNumbers) {
        if (inputNumbers == null || inputNumbers.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateElementsNotNull(List<Integer> inputNumbers) {
        for (Integer n : inputNumbers) {
            if (n == null) {
                throw new IllegalArgumentException("[ERROR] 로또 번호에 null 값이 포함되어 있습니다.");
            }
        }
    }

    private void validateRange(List<Integer> inputNumbers) {
        for (Integer n : inputNumbers) {
            if (n < MIN_NUMBER || n > MAX_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
            }
        }
    }

    private void validateUnique(List<Integer> inputNumbers) {
        if (new HashSet<>(inputNumbers).size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    public List<Integer> numbers() {
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        return List.copyOf(sorted);
    }

    @Override
    public String toString() {
        return numbers().toString();
    }
}