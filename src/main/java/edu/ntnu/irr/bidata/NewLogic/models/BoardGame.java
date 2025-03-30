package edu.ntnu.irr.bidata.NewLogic.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Set up Board, Dice and Players.
 * Iterate over game loop.
 * */
public class BoardGame implements Game {
  private Board board;
  private final List<Player> players;
  private Player currentPlayer;
  private Dice dice;
  private final int boardRowsAmount = 10;
  private final int boardColumnAmount = 9;

  public BoardGame(int amountOfDice) {
    board = new Board();
    players = new ArrayList<>();
    this.dice = new Dice(amountOfDice);
  }

  public void addPlayer(Player player) {
    players.add(player);
  }

  /**
   * Creates initial ladders from the default ladder game configuration.
   * */
  private void createDefaultLadders() {
    LadderActionBuilder ladderBuilder = new LadderActionBuilder(board);
    ladderBuilder.from(1).to(40).build();
    ladderBuilder.from(8).to(10).build();
    ladderBuilder.from(24).to(5).build();
    ladderBuilder.from(33).to(3).build();
    ladderBuilder.from(36).to(52).build();
    ladderBuilder.from(42).to(30).build();
    ladderBuilder.from(56).to(37).build();
    ladderBuilder.from(64).to(27).build();
    ladderBuilder.from(65).to(82).build();
    ladderBuilder.from(68).to(85).build();
    ladderBuilder.from(74).to(12).build();
    ladderBuilder.from(87).to(70).build();
  }

  public void createInitialGameState() {
    board = new Board();
    for (int i = 0; i < boardRowsAmount * boardColumnAmount; i++) {
      Tile tileAtIndex = new Tile(i);
      board.setTile(i, tileAtIndex);
      if (i != 0) board.getTile(i-1).setNextTile(tileAtIndex);
    }
    createDefaultLadders();
  }

  public Player getWinner() {
    Player winner = null;
    for (Player player : players) {
      if (player.getCurrentTile().getNextTile() == null) {
        winner = player;
      }
    }
    return winner;
  }

  public boolean isFinished() {
    for (Player player : players) {
      if (player.getCurrentTile().getNextTile() == null) {
        return true;
      }
    }
    return false;
  }

  public void playOneTurn() {
    int diceRollResult = dice.rollAll();
    currentPlayer.move(diceRollResult);
    currentPlayer = getNextPlayer();
  }

  public Player getNextPlayer() {
    int index = players.indexOf(currentPlayer);
    if (index == players.size() - 1) {
      return players.getFirst();
    } else {
      return players.get(index + 1);
    }
  }

  public Board getBoard() {
    return board;
  }

  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Sets the current player. Is useful for letting the youngest player start.
   * PS: Refactor to use factory or builder pattern
   * */
  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }
}
