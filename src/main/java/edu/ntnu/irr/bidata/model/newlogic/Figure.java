package edu.ntnu.irr.bidata.model.newlogic;

import javafx.scene.paint.Color;

public abstract class Figure {
  private Player owner;

  public Figure(Player player, Color color) {
    this.owner = player;
  }

  public void setOwner(Player player) {
    this.owner = player;
  }

  public Player getOwner() {
    return owner;
  }
}
