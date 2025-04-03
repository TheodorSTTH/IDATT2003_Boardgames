package edu.ntnu.irr.bidata.Model;

/**
 * Player class i responsible for managing player piece/user.
 * */
public class Player {
  private final String name;
  private int currentTile = 0;

  public Player(String name) {
    this.name = name;
  }

  public Player(String name, int currentTile) {
    this.name = name;
    this.currentTile = currentTile;
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

  public String getSaveFormat() {
    return name + "," + currentTile;
  }
  
  
}
