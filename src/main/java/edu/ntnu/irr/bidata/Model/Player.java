package edu.ntnu.irr.bidata.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Player class i responsible for managing player piece/user.
 * */
public class Player {

  @JsonProperty
  private final String name;
  @JsonProperty
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

  /**
   * Turns the player class into a key for json storage
   * */
  @JsonValue
  public String toJsonKey() {
    return name + ";" + color;
  }

  /**
   * Reads json player key from file and turns it into player object.
   * */
  @JsonCreator
  public static Player fromJsonKey(String key) {
    String[] parts = key.split(";", 2);
    return new Player(parts[0], parts.length>1 ? parts[1] : "");
  }
}
