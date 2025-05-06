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
  @JsonProperty
  private final int age;

  public Player(String name, String color, int age) {
    this.name = name;
    this.color = color;
    this.age = age;
  }
  
  public String getName() {
    return name;
  }

  public String getSaveFormat() {
    return name + "," + color + "," + age;
  }
  
  public String getColor() {
    return color;
  }

  public int getAge() {
    return age;
  }
}
