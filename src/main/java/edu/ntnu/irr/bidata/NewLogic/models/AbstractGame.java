package edu.ntnu.irr.bidata.NewLogic.models;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame<T extends IPlayer<?>> implements IGame<T> {
  protected List<T> players;
  protected T currentPlayer;
  protected Board<Integer, ?> board;
  protected Dice dice;

  public AbstractGame(ArrayList<T> players, Board<Integer, ?> board, Dice dice) {
    this.players = players;
    this.board = board;
    this.dice = dice;
  }

  public void addPlayer(T player) {
    players.add(player);
  }

  public abstract void playTurn();

  public abstract T getWinner();

  public boolean isFinished() {
    return getWinner() != null;
  }

  public List<T> getPlayers() {
    return players;
  }

  protected T getNextPlayer() {
    int index = players.indexOf(currentPlayer);
    if (index == players.size() - 1) {
      return players.getFirst();
    } else {
      return players.get(index + 1);
    }
  }

  public T getCurrentPlayer() {
    return currentPlayer;
  }

  protected void setCurrentPlayer(T player) {
    this.currentPlayer = player;
  }
}
