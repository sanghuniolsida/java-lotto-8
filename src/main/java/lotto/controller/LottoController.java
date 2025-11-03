package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.Money;
import lotto.domain.Statistics;
import lotto.domain.WinningNumbers;
import lotto.generator.MissionUtilsNumberGenerator;
import lotto.parser.InputParsers;
import lotto.view.OutputView;

import java.util.List;

public final class LottoController {

    private final LottoMachine lottoMachine;

    public LottoController() {
        this.lottoMachine = new LottoMachine(new MissionUtilsNumberGenerator());
    }

    public void executeLottoFlow() {
        Money purchaseMoney = readPurchaseMoneyWithRetry();
        List<Lotto> purchasedTickets = lottoMachine.generateTickets(purchaseMoney);
        OutputView.printPurchased(purchasedTickets);

        WinningNumbers winningNumbers = readWinningNumbersWithRetry();

        Statistics.Result result = Statistics.calculate(purchasedTickets, winningNumbers, purchaseMoney);
        OutputView.printStatisticsHeader();
        OutputView.printRankCounts(result.rankCount());
        OutputView.printProfitRate(result.profitRate());
    }

    private Money readPurchaseMoneyWithRetry() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                String purchaseAmountInput = Console.readLine();
                long purchaseAmount = InputParsers.parseMoney(purchaseAmountInput);
                return Money.of(purchaseAmount);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private WinningNumbers readWinningNumbersWithRetry() {
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String winningNumbersInput = Console.readLine();
                Lotto mainWinningNumbers = InputParsers.parseWinningNumbers(winningNumbersInput);

                System.out.println("보너스 번호를 입력해 주세요.");
                String bonusNumberInput = Console.readLine();
                int bonusNumber = InputParsers.parseBonus(bonusNumberInput);

                return new WinningNumbers(mainWinningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}