package edu.ntnu.irr.bidata.model.risk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.ntnu.irr.bidata.model.Player;
import edu.ntnu.irr.bidata.model.interfaces.observer.SimpleObserver;
import edu.ntnu.irr.bidata.model.interfaces.observer.SimpleSubject;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Country} class represents a country in the Risk game. Each country has a name, a
 * relative position on the map, an owner, an army count, and a list of neighboring countries. It
 * also implements an observer pattern to notify listeners on state changes.
 */
public class Country implements SimpleSubject {

  private final String name;
  private final double relativeX;
  private final double relativeY;
  private int armies = 0;
  private String owner;
  private String ownerColor;
  private final List<String> neighbors;

  private final ArrayList<SimpleObserver> allObservers;

  /**
   * Constructor used by Jackson for deserialization.
   *
   * <p>Note: Using JsonProperty in the constructor to allow Jackson to map JSON properties to class
   * fields. Was suggested by chatGTP.
   *
   * @param name the name of the country
   * @param owner the name of the player who owns this country
   * @param ownerColor the color of the owner's pieces
   * @param armies the number of armies present in the country
   * @param neighbors list of neighboring country names
   * @param relativeX horizontal position (0.0–1.0) on the game map
   * @param relativeY vertical position (0.0–1.0) on the game map
   */
  @JsonCreator
  public Country(
      @JsonProperty("name") String name,
      @JsonProperty("owner") String owner,
      @JsonProperty("ownerColor") String ownerColor,
      @JsonProperty("armies") int armies,
      @JsonProperty("neighbors") List<String> neighbors,
      @JsonProperty("relativeX") double relativeX,
      @JsonProperty("relativeY") double relativeY) {
    this.name = name;
    this.owner = owner;
    this.ownerColor = ownerColor;
    this.armies = armies;
    this.neighbors = neighbors;
    this.relativeX = clamp(relativeX, 0, 1);
    this.relativeY = clamp(relativeY, 0, 1);
    this.allObservers = new ArrayList<>();
  }

  /** Constructor for manual creation without an owner or army count. */
  public Country(String name, List<String> neighbors, double relativeX, double relativeY) {
    this.name = name;
    this.neighbors = neighbors;
    this.relativeX = clamp(relativeX, 0, 1);
    this.relativeY = clamp(relativeY, 0, 1);
    this.allObservers = new ArrayList<>();
  }

  @Override
  public void registerObserver(SimpleObserver o) {
    allObservers.add(o);
  }

  @Override
  public void removeObserver(SimpleObserver o) {
    allObservers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (SimpleObserver observer : allObservers) {
      observer.update();
    }
  }

  /**
   * Adds troops to the country.
   *
   * @param troops the number of troops to add
   */
  public void placeTroops(int troops) {
    this.armies += troops;
    notifyObservers();
  }

  /**
   * Removes troops from the country.
   *
   * @param troops the number of troops to remove
   * @throws IllegalArgumentException if trying to remove more than available
   */
  public void loseTroops(int troops) {
    if (troops > this.armies) {
      throw new IllegalArgumentException("Country " + name + " does not have enough troops.");
    }
    this.armies -= troops;
    notifyObservers();
  }

  public List<String> getNeighbors() {
    return neighbors;
  }

  public String getName() {
    return name;
  }

  /** Returns the X coordinate relative to the map (0.0–1.0). */
  public double getRelativeX() {
    return this.relativeX;
  }

  /** Returns the Y coordinate relative to the map (0.0–1.0). */
  public double getRelativeY() {
    return this.relativeY;
  }

  public int getArmies() {
    return armies;
  }

  public void setArmies(int armies) {
    this.armies = armies;
    notifyObservers();
  }

  public String getOwner() {
    return owner;
  }

  public String getOwnerColor() {
    return ownerColor;
  }

  /** Updates the country's owner and notifies observers. */
  public void setOwner(Player owner) {
    this.owner = owner.getName();
    this.ownerColor = owner.getColor();
    notifyObservers();
  }

  @Override
  public String toString() {
    return name;
  }

  /**
   * Utility method to clamp a value between a minimum and maximum. (Replaces use of Math.clamp,
   * which doesn't exist in standard Java.)
   */
  private double clamp(double value, double min, double max) {
    return Math.max(min, Math.min(max, value));
  }
}
