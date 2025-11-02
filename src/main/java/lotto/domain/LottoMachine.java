package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public final class LottoMachine {
    private final NumberGenerator numberGenerator;

    public LottoMachine(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<LottoNumbers> generateTickets(Money purchaseMoney) {
        int ticketCount = purchaseMoney.lottoCount();
        List<LottoNumbers> tickets = new ArrayList<>(ticketCount);

        for (int i = 0; i < ticketCount; i++) {
            tickets.add(new LottoNumbers(numberGenerator.generateSixUnique()));
        }
        return List.copyOf(tickets);
    }
}
