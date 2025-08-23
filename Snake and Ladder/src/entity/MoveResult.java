package entity;

import player.Player;

public class MoveResult {
    private Player playedBy;
    private int movedFrom;
    private int movedTo;
    private int diceScore;
    private GameEntity gameEntityEncountered;
    private boolean isGameEnd;
    private String additionalDescription;

    public MoveResult(Player playedBy, int movedFrom, int movedTo, int diceScore, GameEntity gameEntityEncountered,
                      boolean isGameEnd) {
        this.playedBy = playedBy;
        this.movedFrom = movedFrom;
        this.movedTo = movedTo;
        this.diceScore = diceScore;
        this.gameEntityEncountered = gameEntityEncountered;
        this.isGameEnd = isGameEnd;
    }

    public void setAdditionalDescription(String description) {
        this.additionalDescription = description;
    }

    @Override
    public String toString() {
        String moveResult =  "MoveResult{" +
            "playedBy=" + playedBy.getUsername() +" by color "+ playedBy.getPlayerColor()+
                    ", movedFrom=" + movedFrom +
                    ", movedTo=" + movedTo +
                    ", diceScore=" + diceScore +
                    ", isGameEnd=" + isGameEnd ;

        if (gameEntityEncountered != null) {

            if (gameEntityEncountered instanceof Snake) {
                 moveResult += " gameEntityEncountered is Snake";
            }
            else if (gameEntityEncountered instanceof Ladder) {
                moveResult += " gameEntityEncountered is Ladder";
            }
        }
        if (this.additionalDescription != null && this.additionalDescription.length() > 0) {
            moveResult+= " Description ="+this.additionalDescription;
        }

        moveResult += " }";
        return moveResult;
    }
}
