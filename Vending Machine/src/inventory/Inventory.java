package inventory;

import product.Product;
import slot.Slot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Inventory {

    private Map<String, Slot> slotMap;

    public Inventory() {
        slotMap = new HashMap<>();
    }

    public void addSlot(String slotId, Product product, int count) {
        slotMap.put(slotId, new Slot(slotId, product, count));
    }

    public Product getProduct(String slotId) {
        return Optional.ofNullable(slotMap.get(slotId)).map(Slot::getProduct).orElse(null);
    }

    public int getQuantity(String slotId) {
        return Optional.ofNullable(slotMap.get(slotId)).map(Slot::getCount).orElse(-1);
    }

    public boolean dispenseFromSlot(String slotId) {
        Slot slot = slotMap.get(slotId);
        return (slot != null) && slot.dispense();
    }

    public void restockSlot(String slotId, int quantity) {
        Slot slot = slotMap.get(slotId);
        if (slot != null) {
            slot.restock(quantity);
        }
    }

    public Set<String> getAllSlotIds() {
        return slotMap.keySet();
    }
}
