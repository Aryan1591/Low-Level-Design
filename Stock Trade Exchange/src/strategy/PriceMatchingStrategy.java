package strategy;

import order.Order;
import trade.Trade;

import java.util.List;

public interface PriceMatchingStrategy {
    List<Trade> matchOrders(List<Order> buyOrders, List<Order> sellOrders, String stock);
}
