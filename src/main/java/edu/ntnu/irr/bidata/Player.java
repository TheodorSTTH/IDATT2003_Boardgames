package edu.ntnu.irr.bidata;

/**
 * Player class i responsible for managing player piece/user.
 * */
public class Player {
  private final String name;
  private int currentTile;

  public Player(String name) { 
    this.name = name;
    this.currentTile = 0;
  }

  public String getName() {
    return name;
  }
  
  public int getCurrentTile() {
    return currentTile;
  }

  public void setCurrentTile(int currentTile) {
    this.currentTile = currentTile;
  }
}
