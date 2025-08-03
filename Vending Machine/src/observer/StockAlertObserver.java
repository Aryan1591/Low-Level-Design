package observer;

public class StockAlertObserver implements VendingMachineObserver {
    private final String name;

    public StockAlertObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String eventType, Object data) {
        if ("LOW_STOCK".equals(eventType)) {
            System.out.println("[" + name + "] ALERT: Low stock in slot " + data);
        }
    }
}
