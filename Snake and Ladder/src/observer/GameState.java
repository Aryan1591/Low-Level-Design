package observer;

import entity.MoveResult;
import player.Player;

import java.util.List;

public interface GameState {
    public void onGameStarted(List<Player> playerList);
    public void onMovePlayed(MoveResult moveResult);
    public void onGameEnd(Player player);
}
