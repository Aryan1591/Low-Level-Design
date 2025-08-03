package product;

public class Beverage extends Product {
    private final int volumeMl;

    public Beverage(String id, String name, double price, int volumeMl) {
        super(id, name, price);
        this.volumeMl = volumeMl;
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    @Override
    public String getDescription() {
        return getName() + " (" + volumeMl + "ml)";
    }
}
