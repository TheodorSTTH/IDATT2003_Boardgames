package edu.ntnu.irr.bidata.model.newlogic;

public class Game {
  protected String name;
  protected final PlayerManager playerManager;

  public Game(String name, PlayerManager playerManager) {
    this.name = name;
    this.playerManager = playerManager;
  }
}
