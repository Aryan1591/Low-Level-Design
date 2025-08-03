package state;

import coin.Coin;
import service.VendingMachine;

public class IdleState implements VendingMachineState {
    private VendingMachine machine;
    public IdleState(VendingMachine machine) { this.machine = machine; }
    @Override public void insertCoin(Coin coin) { machine.addCoin(coin); machine.setState(new PaymentState(machine)); }
    @Override public void selectProduct(String slotId) { System.out.println("Please insert money first."); }
    @Override public void dispense() { System.out.println("Please insert money and select a product."); }
    @Override public void cancel() { System.out.println("No transaction to cancel."); }
}
