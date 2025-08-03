import coin.Coin;
import coin.CoinAcceptor;
import inventory.Inventory;
import observer.StockAlertObserver;
import product.Product;
import product.ProductFactory;
import service.VendingMachine;

public class Main {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        CoinAcceptor acceptor = new CoinAcceptor();
        VendingMachine machine = new VendingMachine(inventory, acceptor);

        // Add observers
        machine.addObserver(new StockAlertObserver("Stock Manager"));


        // Add products and slots
        Product cola = ProductFactory.createBeverage("PC001", "Coca Cola", 1.50, 330);
        Product water = ProductFactory.createBeverage("PW001", "Water", 1.00, 500);
        Product chips = ProductFactory.createSnack("PS001", "Chips", 2.00, 200);
        inventory.addSlot("A1", cola, 10);
        inventory.addSlot("A2", water, 5);
        inventory.addSlot("B1", chips, 3);

        // Display menu
        System.out.println("============== VENDING MACHINE ==============");
        for (String slot : inventory.getAllSlotIds()) {
            Product p = inventory.getProduct(slot);
            System.out.printf("%s: %s - Rs%.2f (%d left)%n",
                    slot, p.getName(), p.getPrice(), inventory.getQuantity(slot));
        }


//        machine.insertCoin(Coin.FIVE);
        machine.insertCoin(Coin.ONE);
//        machine.insertCoin(Coin.TWO);
        machine.selectProduct("A1");
        machine.dispense();
        machine.insertCoin(Coin.TWO);
        machine.selectProduct("A1");
        machine.dispense();
    }
}