package strategy;

import order.Order;
import trade.Trade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PricePriorityMatchingStrategy implements PriceMatchingStrategy {
    private AtomicInteger tradeCounter = new AtomicInteger(1);

    @Override
    public List<Trade> matchOrders(List<Order> buyOrders, List<Order> sellOrders, String stock) {
        List<Trade> trades = new ArrayList<>();

        // Sort buy orders by price (highest first)
        buyOrders.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));

        // Sort sell orders by price (lowest first)
        sellOrders.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));

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
