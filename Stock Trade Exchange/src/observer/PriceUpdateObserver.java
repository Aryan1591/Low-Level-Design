package observer;

import trade.Trade;

public class PriceUpdateObserver implements TradeObserver{
    @Override
    public void onTradeExecuted(Trade trade) {
        System.out.println("📈 Price updated for " + trade.getStock() + " to $" + trade.getPrice());
    }
}
