package coin;

// All acceptable coin denominations
public enum Coin {
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10);

    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Coin fromValue(int value) {
        for (Coin coin : Coin.values()) {
            if (coin.value == value) {
                return coin;
            }
        }
        throw new IllegalArgumentException("Invalid coin value: " + value);
    }
}

