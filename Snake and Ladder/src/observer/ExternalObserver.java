package observer;

import entity.MoveResult;
import player.Player;

import java.util.List;

public class ExternalObserver implements GameState{

    private String observerName;

    public ExternalObserver(final String observerName) {
        this.observerName = observerName;
    }
    @Override
    public void onGameStarted(List<Player> playerList) {
        System.out.println("Game Started, Participating Players are :");
        playerList.forEach(player -> System.out.println(player.getUsername()+" is playing with "+player.getPlayerColor()));
    }

    @Override
    public void onMovePlayed(MoveResult moveResult) {
        System.out.println(moveResult);
    }

    @Override
    public void onGameEnd(Player player) {
        System.out.println("Game won by player "+player.getUsername()+" with color "+ player.getPlayerColor());
    }
}
