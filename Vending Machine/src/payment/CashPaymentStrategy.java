package payment;

import coin.Coin;
import coin.CoinAcceptor;

import java.util.List;

public class CashPaymentStrategy implements PaymentStrategy{

    private CoinAcceptor coinAcceptor;
    public CashPaymentStrategy(CoinAcceptor coinAcceptor) {
        this.coinAcceptor = coinAcceptor;
    }
    @Override
    public boolean pay(double amount) {
        return coinAcceptor.getTotalInserted() >= amount;
    }

    @Override
    public List<Coin> calculateChange(double amountPaid, double amountDue) {
        return coinAcceptor.returnAllCoins(); // Just return all coins for simplicity; real logic would calculate optimal coins.
    }
}
