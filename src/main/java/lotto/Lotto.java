package lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public java.util.List<Integer> numbers() {
        java.util.List<Integer> copy = new java.util.ArrayList<>(numbers);
        java.util.Collections.sort(copy);
        return java.util.List.copyOf(copy);
    }

    @Override
    public String toString() {
        return numbers().toString();
    }
}
