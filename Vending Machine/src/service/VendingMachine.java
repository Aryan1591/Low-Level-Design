package service;

import coin.Coin;
import coin.CoinAcceptor;
import inventory.Inventory;
import observer.VendingMachineObserver;
import payment.CashPaymentStrategy;
import payment.PaymentStrategy;
import product.Product;
import state.IdleState;
import state.VendingMachineState;

import java.util.*;

public class VendingMachine {
    private Inventory inventory;
    private CoinAcceptor acceptor;
    private VendingMachineState currentState;
    private PaymentStrategy paymentStrategy;
    private List<VendingMachineObserver> observers = new ArrayList<>();
    private String selectedSlotId = null;

    public VendingMachine(Inventory inventory, CoinAcceptor acceptor) {
        this.inventory = inventory;
        this.acceptor = acceptor;
        this.currentState = new IdleState(this);
        this.paymentStrategy = new CashPaymentStrategy(acceptor);
    }

    // Internal Dispense (called by states)
    public void dispenseInternal() {
        Product product = inventory.getProduct(selectedSlotId);
        if (product == null || !inventory.dispenseFromSlot(selectedSlotId)) {
            System.out.println("Unable to dispense. Product not available.");
            return;
        }
        double price = product.getPrice();
        System.out.println("Dispensing: " + product.getDescription());
        notifyObservers("SALE", product.getDescription());
        if (inventory.getQuantity(selectedSlotId) <= 2) {
            notifyObservers("LOW_STOCK", selectedSlotId);
        }
        if (acceptor.getTotalInserted() > price) {
            List<Coin> change = paymentStrategy.calculateChange(acceptor.getTotalInserted(), price);
            System.out.println("Returning change: " + change);
        }
        acceptor.clear();
        selectedSlotId = null;
        setState(new IdleState(this));
    }

    // User actions (delegate to current state)
    public void insertCoin(Coin coin) { currentState.insertCoin(coin); }
    public void selectProduct(String slotId) {
        selectedSlotId = slotId;
        Product product = inventory.getProduct(slotId);
        if (product == null) {
            System.out.println("Invalid slot selected.");
            return;
        }
        if (acceptor.getTotalInserted() >= product.getPrice()) {
            currentState.selectProduct(slotId);
        } else {
            System.out.println("Insufficient funds. Please insert more coins.");
        }
    }
    public void dispense() { currentState.dispense(); }
    public void cancel() { currentState.cancel(); }
    public void addObserver(VendingMachineObserver observer) { observers.add(observer); }
    public void notifyObservers(String eventType, Object data) {
        for (VendingMachineObserver o : observers) o.update(eventType, data);
    }

    // Internally called by states
    public void setState(VendingMachineState state) { this.currentState = state; }
    public void addCoin(Coin coin) { acceptor.addCoin(coin); }
    public void returnCoins() {
        List<Coin> returned = acceptor.returnAllCoins();
        System.out.println("Returned coins: " + returned);
    }
    public Product getSelectedProduct() { return inventory.getProduct(selectedSlotId); }
}
