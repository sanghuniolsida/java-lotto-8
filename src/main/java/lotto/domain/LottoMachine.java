package lotto.domain;

import lotto.Lotto;
import java.util.ArrayList;
import java.util.List;

public final class LottoMachine {
    private final NumberGenerator numberGenerator;

    public LottoMachine(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> generateTickets(Money purchaseMoney) {
        int ticketCount = purchaseMoney.lottoCount();
        List<Lotto> tickets = new ArrayList<>(ticketCount);

        for (int i = 0; i < ticketCount; i++) {
            tickets.add(new Lotto(numberGenerator.generateSixUnique()));
        }
        return List.copyOf(tickets);
    }
}