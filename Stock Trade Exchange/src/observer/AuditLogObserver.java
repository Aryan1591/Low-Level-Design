package observer;

import trade.Trade;

public class AuditLogObserver implements TradeObserver{
    @Override
    public void onTradeExecuted(Trade trade) {
        System.out.println("📝 Audit: Trade " + trade.getTradeId() + " logged to database");
    }
}
