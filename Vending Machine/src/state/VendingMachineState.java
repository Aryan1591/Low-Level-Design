package state;

import coin.Coin;
public interface VendingMachineState {
    void insertCoin(Coin coin);
    void selectProduct(String slotId);
    void dispense();
    void cancel();
}
