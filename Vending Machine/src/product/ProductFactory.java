package product;

public class ProductFactory {

    public static Beverage createBeverage(String id, String name, double price, int volumeMl) {
        return new Beverage(id, name, price, volumeMl);
    }

    public static Snack createSnack(String id, String name, double price, double gms) {
        return new Snack(id, name, price, gms);
    }
}
