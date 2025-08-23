package util;

import entity.GameEntity;
import entity.MoveResult;
import player.Player;

public class MoveResultUtil {

    public static MoveResult buildMoveResult(Player player, int from, int to, int diceScore, GameEntity gameEntity,
                                             boolean isGameEnd, String desc) {
        MoveResult moveResult = new MoveResult(player, from, to, diceScore, gameEntity, isGameEnd);
        if (desc != null) {
            moveResult.setAdditionalDescription(desc);
        }
        return moveResult;
    }
}
