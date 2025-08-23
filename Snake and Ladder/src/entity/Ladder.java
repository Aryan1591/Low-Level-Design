package entity;

public class Ladder extends GameEntity{

    public Ladder(int bottom, int top) {
        super(bottom, top);
        if (bottom >= top) {
            throw new IllegalArgumentException("Ladder start should be lower than ladder end");
        }
    }

    @Override
    public String getType() {
        return "Ladder";
    }


}
