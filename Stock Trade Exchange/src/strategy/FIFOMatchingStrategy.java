package strategy;

import order.Order;
import trade.Trade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
    This is a FIFO Matching Strategy which tries to match on the basis of First come, First Served
 */
public class FIFOMatchingStrategy implements PriceMatchingStrategy {
    private AtomicInteger tradeCounter = new AtomicInteger(1);

    @Override
    public List<Trade> matchOrders(List<Order> buyOrders, List<Order> sellOrders, String stock) {
        List<Trade> trades = new ArrayList<>();

        Iterator<Order> buyIterator = buyOrders.iterator();
        while (buyIterator.hasNext()) {
            Order buyOrder = buyIterator.next();

            Iterator<Order> sellIterator = sellOrders.iterator();
            while (sellIterator.hasNext()) {
                Order sellOrder = sellIterator.next();

                if (buyOrder.getPrice() >= sellOrder.getPrice() && buyOrder.getQuantity() > 0 && sellOrder.getQuantity() > 0) {
                    int tradeQuantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
                    double tradePrice = sellOrder.getPrice();

                    Trade trade = new Trade("T" + tradeCounter.getAndIncrement(), buyOrder.getOrderId(), sellOrder.getOrderId(),
                            stock, tradeQuantity, tradePrice);
                    trades.add(trade);

                    buyOrder.setQuantity(buyOrder.getQuantity() - tradeQuantity);
                    sellOrder.setQuantity(sellOrder.getQuantity() - tradeQuantity);

                    if (sellOrder.getQuantity() == 0) {
                        sellIterator.remove();
                    }
                    if (buyOrder.getQuantity() == 0) {
                        break;
                    }
                }
            }

            if (buyOrder.getQuantity() == 0) {
                buyIterator.remove();
            }
        }

        return trades;
    }


}
