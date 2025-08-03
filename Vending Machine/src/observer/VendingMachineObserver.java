package observer;

public interface VendingMachineObserver {
    void update(String eventType, Object data);
}
