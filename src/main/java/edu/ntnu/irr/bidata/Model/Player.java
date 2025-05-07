package edu.ntnu.irr.bidata.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The {@code Player} class represents a user/player in the game.
 * It stores basic attributes such as the player's name, color, and age.
 */
public class Player {
  private final String name;
  private final String color;
  private final int age;

  /**
   * Constructs a new {@code Player} with the specified name, color, and age.
   *
   * @param name  The name of the player.
   * @param color The color assigned to the player's piece.
   * @param age   The age of the player.
   */
  public Player(String name, String color, int age) {
    this.name = name;
    this.color = color;
    this.age = age;
  }

  /**
   * Gets the player's name.
   *
   * @return The name of the player.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the color assigned to the player's piece.
   *
   * @return The player's color.
   */
  public String getColor() {
    return color;
  }

  /**
   * Gets the age of the player.
   *
   * @return The player's age.
   */
  public int getAge() {
    return age;
  }

  /**
   * Returns a CSV-formatted string for saving the player data.
   * Format: name,color,age
   *
   * @return The string representing the player's save format.
   */
  public String getSaveFormat() {
    return name + "," + color + "," + age;
  }
}