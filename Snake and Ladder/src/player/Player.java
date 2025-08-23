package player;

public class Player {

    private int playerId;
    private String username;
    private PlayerColor playerColor;

    private int currentPositionInBoard;

    public Player(int playerId, String username, PlayerColor playerColor) {
        this.playerId = playerId;
        this.username = username;
        this.playerColor = playerColor;
        this.currentPositionInBoard = 1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public void setCurrentPositionInBoard(int position) {
        this.currentPositionInBoard = position;
    }

    public int getCurrentPositionInBoard() {
       return this.currentPositionInBoard;
    }
}
