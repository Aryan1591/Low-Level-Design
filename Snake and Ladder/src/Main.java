import board.GameBoard;
import dice.Dice;
import dice.StandardDice;
import observer.ExternalObserver;
import player.Player;
import player.PlayerColor;
import service.SnakeAndLadderGame;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player(1, "Aryan", PlayerColor.RED);
        Player player2 = new Player(2, "Avishekh", PlayerColor.BLUE);

        Dice dice = new StandardDice();
        GameBoard board = new GameBoard(100);

        board.addSnake(99, 54);
        board.addSnake(70, 55);
        board.addSnake(52, 42);
        board.addSnake(25, 2);
        board.addSnake(95, 72);

        // Add some ladders
        board.addLadder(6, 25);
        board.addLadder(11, 40);
        board.addLadder(60, 85);
        board.addLadder(46, 90);
        board.addLadder(17, 69);


        List<Player> playerList = Arrays.asList(player1, player2);
        SnakeAndLadderGame game = new SnakeAndLadderGame(board, playerList, dice);

        game.addObserver(new ExternalObserver("External"));

        game.startGame();
    }
}