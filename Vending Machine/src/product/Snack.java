package product;

public class Snack extends Product {
    private final double gms;
    public Snack(String id, String name, double price, double gms) {
        super(id, name, price);
        this.gms = gms;
    }
    public double getWeight() { return gms; }
    @Override public String getDescription() {
        return getName() + " (" + gms + ")";
    }
}