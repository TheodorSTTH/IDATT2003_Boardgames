package edu.ntnu.irr.bidata.GeneralClassesBackend;

/**
 * Player class i responsible for managing player piece/user.
 * */
public class Player {
  private final String name;
  private int currentTile = 0;

  public Player(String name) {
    this.name = name;
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
