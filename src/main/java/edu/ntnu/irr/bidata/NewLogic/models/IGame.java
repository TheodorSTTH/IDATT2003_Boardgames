package edu.ntnu.irr.bidata.NewLogic.models;

import java.util.List;

/**
 * Interface for a typical turn based game
 * */
public interface IGame<T extends IPlayer<?>> {
  public void addPlayer(T player);
  public T getWinner();
  public boolean isFinished();
  public void playTurn();
  public List<T> getPlayers();
  public T getCurrentPlayer();
}
