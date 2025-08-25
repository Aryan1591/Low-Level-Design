package trade;

public class Trade {

    private String tradeId;
    private String buyOrderId;
    private String sellOrderId;
    private String stock;
    private int quantity;
    private double price;
    public Trade(String id, String buyOrderId, String sellOrderId, String stock, int quantity, double price) {
        this.tradeId = id;
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "TRADE: " + quantity + " " + stock + " at $" + price + " (Buy:" + buyOrderId + ", Sell:" + sellOrderId + ")";
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getBuyOrderId() {
        return buyOrderId;
    }

    public String getSellOrderId() {
        return sellOrderId;
    }

    public String getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
