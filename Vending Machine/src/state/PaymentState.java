package state;

import coin.Coin;
import service.VendingMachine;

public class PaymentState implements VendingMachineState {
    private VendingMachine machine;
    public PaymentState(VendingMachine machine) { this.machine = machine; }
    @Override public void insertCoin(Coin coin) { machine.addCoin(coin); }
    @Override public void selectProduct(String slotId) { machine.setState(new DispensingState(machine)); }
    @Override public void dispense() { System.out.println("Please select a product first."); }
    @Override public void cancel() { machine.returnCoins(); machine.setState(new IdleState(machine)); }
}
