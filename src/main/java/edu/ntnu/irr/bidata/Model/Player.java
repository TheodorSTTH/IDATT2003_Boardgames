package edu.ntnu.irr.bidata.Model;

/**
 * Player class i responsible for managing player piece/user.
 * */
public class Player {
  private final String name;

  public Player(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }

  public String getSaveFormat() {
    return name;
  } 
}
