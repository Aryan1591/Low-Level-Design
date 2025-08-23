package dice;

import java.util.Random;

public class StandardDice implements Dice{

    private Random random;

    public StandardDice() {
        this.random = new Random();
    }
    @Override
    public int move() {
        return random.nextInt(6) + 1;
    }
}
