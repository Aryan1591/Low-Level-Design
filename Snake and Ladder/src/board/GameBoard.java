package board;

import entity.GameEntity;
import entity.Ladder;
import entity.Snake;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {

    private int size;
    private Map<Integer, GameEntity> boardEntity;

    public GameBoard(int size) {
        this.size = size;
        this.boardEntity = new HashMap<>();
    }

    public void addSnake(int head, int tail) {
        if (head >= size || tail < 1) {
            throw new IllegalArgumentException("Invalid snake position");
        }
        boardEntity.put(head, new Snake(head, tail));
    }

    public void addLadder(int bottom, int top) {
        if (bottom < 1 || top >= size) {
            throw new IllegalArgumentException("Invalid ladder position");
        }
        boardEntity.put(bottom, new Ladder(bottom, top));
    }


    public int getDestination(int startPoint) {
        return boardEntity.containsKey(startPoint) ? boardEntity.get(startPoint).getEnd() : startPoint;
    }

    public int getSize() {
        return this.size;
    }

    public GameEntity gameEntity(int position) {
        return boardEntity.get(position);
    }
}
