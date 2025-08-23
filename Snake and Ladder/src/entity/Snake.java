package entity;

public class Snake extends GameEntity{
    public Snake(int top, int bottom) {
        super(top, bottom);
        if (top <= bottom) {
            throw new IllegalArgumentException("Snake Head cannot be lower than Tail");
        }
    }

    @Override
    public String getType() {
        return "Snake";
    }
}
