package order;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderFactory {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static Order createOrder(String userId, String stock, OrderType type, int quantity, double price) {
        String id = (type == OrderType.BUY ? "B" : "S") + atomicInteger.incrementAndGet();
        return new Order(id, userId, stock, type, quantity, price);
    }
}
