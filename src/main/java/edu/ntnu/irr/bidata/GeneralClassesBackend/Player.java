package edu.ntnu.irr.bidata.GeneralClassesBackend;

/**
 * Player class i responsible for managing player piece/user.
 * */
public abstract class Player {
  private final String name;

  public Player(String name) {
    this.name = name;
  }
  
  public abstract int getCurrentTile();

  public abstract void setCurrentTile(int currentTile);

  public String getName() {
    return name;
  }
}
