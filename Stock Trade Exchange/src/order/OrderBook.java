package order;

import observer.TradeObserver;
import strategy.PriceMatchingStrategy;
import trade.Trade;

import java.util.ArrayList;
import java.util.List;

public class OrderBook {

    private String stock;
    private List<Order> buyOrders;
    private List<Order> sellOrders;
    private List<TradeObserver> tradeObservers;
    private PriceMatchingStrategy priceMatchingStrategy;

    public OrderBook(String stock, PriceMatchingStrategy priceMatchingStrategy) {
        this.stock = stock;
        this.priceMatchingStrategy = priceMatchingStrategy;
        this.buyOrders = new ArrayList<>();
        this.sellOrders = new ArrayList<>();
        this.tradeObservers = new ArrayList<>();
    }

    public void addOrder(Order order) {
        if (order.getOrderType() == OrderType.BUY) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }

    }

    public List<Trade> tryToMatch() {
        List<Trade> trades = priceMatchingStrategy.matchOrders(buyOrders, sellOrders, stock);

        for (Trade trade : trades) {
            System.out.println(trade);
            notifyObservers(trade);
        }

        return trades;
    }

    private void notifyObservers(Trade trade) {
        for (TradeObserver observer : tradeObservers) {
            observer.onTradeExecuted(trade);
        }
    }

    public void showAllOrders() {
        System.out.println("\n=== " + stock + " Order Book ===");
        System.out.println("BUY ORDERS:");
        for (Order order : buyOrders) {
            System.out.println("  " + order);
        }
        System.out.println("SELL ORDERS:");
        for (Order order : sellOrders) {
            System.out.println("  " + order);
        }
        System.out.println();
    }

    public void addObserver(TradeObserver observer) {
        tradeObservers.add(observer);
    }

    public String getStock() {
        return stock;
    }

    public List<Order> getBuyOrders() {
        return buyOrders;
    }

    public List<Order> getSellOrders() {
        return sellOrders;
    }

    public void setPriceMatchingStrategy(PriceMatchingStrategy priceMatchingStrategy) {
        this.priceMatchingStrategy = priceMatchingStrategy;
    }
}
