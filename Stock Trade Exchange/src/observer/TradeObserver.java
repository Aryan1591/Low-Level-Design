package observer;

import trade.Trade;

public interface TradeObserver {
    public void onTradeExecuted(Trade trade);
}
