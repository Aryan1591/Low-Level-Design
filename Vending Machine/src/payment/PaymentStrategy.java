package payment;

import coin.Coin;

import java.util.List;

public interface PaymentStrategy {
    boolean pay(double amount);

    List<Coin> calculateChange(double amountPaid, double amountDue);
}
