import order.Order;
import order.OrderFactory;
import order.OrderType;
import service.ExchangeService;
import strategy.FIFOMatchingStrategy;
import strategy.PricePriorityMatchingStrategy;

public class Main {
    public static void main(String[] args) {

        ExchangeService exchangeService = new ExchangeService(new FIFOMatchingStrategy());
        Order buy1 = OrderFactory.createOrder("Aryan", "AAPL", OrderType.BUY, 100, 150.0);
        Order sell1 = OrderFactory.createOrder("Avishekh", "AAPL", OrderType.SELL, 50, 149.0);
        Order buy2 = OrderFactory.createOrder("tom", "AAPL", OrderType.BUY, 75, 148.0);

        exchangeService.submitOrder(buy1);
        exchangeService.submitOrder(sell1);
        exchangeService.submitOrder(buy2);

        exchangeService.setPriceMatchingStrategy(new PricePriorityMatchingStrategy());

        Order googleBuy1 = OrderFactory.createOrder("user1", "GOOGL", OrderType.BUY, 10, 2500.0);
        Order googleBuy2 = OrderFactory.createOrder("user2", "GOOGL", OrderType.BUY,15, 2510.0);
        Order googleSell1 = OrderFactory.createOrder("user3", "GOOGL", OrderType.SELL,20, 2505.0);

        exchangeService.submitOrder(googleBuy1);
        exchangeService.submitOrder(googleBuy2);
        exchangeService.submitOrder(googleSell1);


        exchangeService.showAllBooks();
    }
}