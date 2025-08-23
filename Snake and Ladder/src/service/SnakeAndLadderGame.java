package service;

import board.GameBoard;
import dice.Dice;
import entity.GameEntity;
import entity.GameStatus;
import entity.MoveResult;
import observer.GameState;
import player.Player;
import util.MoveResultUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SnakeAndLadderGame {
    private List<GameState> observerList;
    private GameBoard gameBoard;
    private List<Player> playerList;
    private GameStatus gameStatus;
    private Dice dice;
    private int currentPlayerIndex;
    public SnakeAndLadderGame(GameBoard gameBoard, List<Player> playerList, Dice dice) {
        this.playerList = playerList;
        this.gameBoard = gameBoard;
        this.observerList = new ArrayList<>();
        this.gameStatus = GameStatus.INITIALIZATION;
        this.dice = dice;
        this.currentPlayerIndex = 0;
    }

    public void addObserver(GameState observer) {
        this.observerList.add(observer);
    }

    public void removeObserver(GameState observer) {
        this.observerList.remove(observer);
    }

    public void updateGameEvent(MoveResult moveResult) {
        for (GameState observers : observerList) {
            observers.onMovePlayed(moveResult);
        }
    }

    public void addPlayer(Player player) {
        if (GameStatus.INITIALIZATION != this.gameStatus) {
            throw new IllegalArgumentException("Game is already started or completed");
        }
        this.playerList.add(player);
    }

    public void startGame() {
        this.gameStatus = GameStatus.ACTIVE;
        if (playerList.size() == 0) {
            throw new IllegalArgumentException("No Players Available");
        }
        for (GameState observers : observerList) {
            observers.onGameStarted(playerList);
        }
        play();
    }

    private void play() {
        int totalMoves = 0;
        while (gameStatus == GameStatus.ACTIVE && totalMoves < 1000) {
            final Player currentPlayer = playerList.get(currentPlayerIndex);
            int move = dice.move();
            int currentPosition = currentPlayer.getCurrentPositionInBoard();
            if (currentPosition + move > gameBoard.getSize()) {
                MoveResult moveResult = MoveResultUtil.buildMoveResult(currentPlayer, currentPosition, currentPosition, move,
                        null, false, "Invalid Move");
                updateGameEvent(moveResult);
            } else {
                int nextPosition = currentPosition + move;
                int finalPosition = gameBoard.getDestination(nextPosition);
                GameEntity gameEntity = null;
                if (nextPosition != finalPosition) {
                     gameEntity = gameBoard.gameEntity(nextPosition);
                }
                currentPlayer.setCurrentPositionInBoard(finalPosition);
                MoveResult moveResult = MoveResultUtil.buildMoveResult(currentPlayer, currentPosition, finalPosition, move, gameEntity,
                        finalPosition == gameBoard.getSize() ? true : false, null);
                updateGameEvent(moveResult);
                if (finalPosition == gameBoard.getSize()) {
                    //Game End
                    sendGameEndEvent(currentPlayer);
                    gameStatus = GameStatus.COMPLETED;
                }
            }
            currentPlayerIndex = (currentPlayerIndex + 1)%playerList.size();
            totalMoves++;
        }

        if (gameStatus != GameStatus.COMPLETED) {
            gameStatus = GameStatus.TERMINATED;
        }

    }

    public void sendGameEndEvent(Player player) {
        observerList.forEach(observer -> observer.onGameEnd(player));
    }
}
