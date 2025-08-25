package order;

import java.time.Instant;

public class Order {
    private String orderId;
    private String userId;
    private String stockName;
    private Instant createdAt;
    private OrderType orderType;
    private int quantity;
    private double price;

    public Order(String orderId, String userId, String stockName, OrderType orderType, int quantity, double price) {
        this.createdAt = Instant.now();
        this.orderId = orderId;
        this.userId = userId;
        this.stockName = stockName;
        this.orderType = orderType;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public String getStockName() {
        return stockName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
