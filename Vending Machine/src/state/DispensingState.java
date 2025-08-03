package state;

import coin.Coin;
import service.VendingMachine;

public class DispensingState implements VendingMachineState {
    private VendingMachine machine;
    public DispensingState(VendingMachine machine) { this.machine = machine; }
    @Override public void insertCoin(Coin coin) { System.out.println("Please wait, dispensing in progress."); }
    @Override public void selectProduct(String slotId) { System.out.println("Please wait, dispensing in progress."); }
    @Override public void dispense() { machine.dispenseInternal(); }
    @Override public void cancel() { System.out.println("Cannot cancel during dispensing."); }
}