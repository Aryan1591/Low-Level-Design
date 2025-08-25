package service;

import observer.AuditLogObserver;
import observer.PriceUpdateObserver;
import order.Order;
import order.OrderBook;
import strategy.PriceMatchingStrategy;

import java.util.HashMap;
import java.util.Map;

public class ExchangeService {
    private Map<String, OrderBook> stockBooks;
    private PriceMatchingStrategy priceMatchingStrategy;

    public ExchangeService(PriceMatchingStrategy priceMatchingStrategy) {
        this.priceMatchingStrategy = priceMatchingStrategy;
        this.stockBooks = new HashMap<>();
    }

    public void submitOrder(Order order) {
        String stockName = order.getStockName();
        if (!stockBooks.containsKey(stockName)) {
            OrderBook orderBook = new OrderBook(stockName, priceMatchingStrategy);
            orderBook.addObserver(new AuditLogObserver());
            orderBook.addObserver(new PriceUpdateObserver());
            stockBooks.put(stockName, orderBook);
        }
        OrderBook orderBook = stockBooks.get(stockName);
        orderBook.addOrder(order);
        orderBook.tryToMatch();
    }

    public void changeMatchingStrategy(String stock, PriceMatchingStrategy newStrategy) {
        OrderBook orderBook = stockBooks.get(stock);
        if (orderBook != null) {
            orderBook.setPriceMatchingStrategy(newStrategy);
            System.out.println("Changed matching strategy for " + stock + " to " + newStrategy.getClass().getSimpleName());
        }
    }

    public void showAllBooks() {
        System.out.println("\n=== EXCHANGE - All Order Books ===");
        for (OrderBook book : stockBooks.values()) {
            book.showAllOrders();
        }
    }

    public void setPriceMatchingStrategy(PriceMatchingStrategy priceMatchingStrategy) {
        this.priceMatchingStrategy = priceMatchingStrategy;
    }
}
