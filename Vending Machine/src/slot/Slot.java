package slot;

import product.Product;

public class Slot {
    private final String slotId;
    private final Product product;
    private int count;

    public Slot(String slotId, Product product, int count) {
        this.slotId = slotId;
        this.product = product;
        this.count = count;
    }

    public String getSlotId() { return slotId; }
    public Product getProduct() { return product; }
    public int getCount() { return count; }

    public boolean dispense() {
        if (count > 0) {
            count--;
            return true;
        }
        return false;
    }

    public void restock(int quantity) {
        count += quantity;
    }
}
