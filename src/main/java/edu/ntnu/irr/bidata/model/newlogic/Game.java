package edu.ntnu.irr.bidata.model.newlogic;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public abstract class Game {
  protected String name;
  protected final PlayerManager playerManager;

  public Game(String name, PlayerManager playerManager) {
    this.name = name;
    this.playerManager = playerManager;
  }

  public ArrayList<String> getAvailableColors() {
    return playerManager.getAvailableColors();
  }

  public PlayerManager getPlayerManager() {
    return playerManager;
  }

  public abstract String getRules();
}
