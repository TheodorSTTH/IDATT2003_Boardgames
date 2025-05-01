package edu.ntnu.irr.bidata.Model;

/**
 * Player class i responsible for managing player piece/user.
 * */
public class Player {
  private final String name;
  private final String color;

  public Player(String name, String color) {
    this.name = name;
    this.color = color;
  }
  
  public String getName() {
    return name;
  }

  public String getSaveFormat() {
    return name + ";" + color;
  }
  
  public String getColor() {
    return color;
  }
}
