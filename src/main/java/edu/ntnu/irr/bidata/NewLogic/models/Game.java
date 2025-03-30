package edu.ntnu.irr.bidata.NewLogic.models;

import java.util.List;

public interface Game {
  public void addPlayer(Player player);
  public void createInitialGameState();
  public Player getWinner();
  public boolean isFinished();
  public void playOneTurn();
  public List<Player> getPlayers();
}
