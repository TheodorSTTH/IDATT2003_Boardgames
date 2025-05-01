package edu.ntnu.irr.bidata.Model.Risk;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISimpleObserver;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISimpleSubject;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Country implements ISimpleSubject {
  private final String name;
  private final double relativeX;
  private final double relativeY;
  private int armies = 0;
  private String owner = null; // TODO: Make this a player object. We need the player color
  private final List<String> neighbors;

  private final ArrayList<ISimpleObserver> allObservers;

  @JsonCreator
  public Country(
      @JsonProperty("name") String name,
      @JsonProperty("neighbors") List<String> neighbors,
      @JsonProperty("relativeX") double relativeX,
      @JsonProperty("relativeY") double relativeY) {
    this.name = name;
    this.neighbors = neighbors;
    this.relativeX = Math.clamp(relativeX, 0, 1);
    this.relativeY = Math.clamp(relativeY, 0, 1);
    this.allObservers = new ArrayList<>();
  }



  @Override
  public void registerObserver(ISimpleObserver o) {
    allObservers.add(o);
  }

  @Override
  public void removeObserver(ISimpleObserver o) {
    allObservers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (ISimpleObserver observer : allObservers) {
      observer.update();
    }
  }

  public void placeTropes(int tropes) {
    this.armies += tropes;
    notifyObservers();
  }

  public List<String> getNeighbors() {
    return neighbors;
  }

  public String getName() {
    return name;
  }

  public void loseTroops(int tropes) {
    if (tropes > this.armies) {
      throw new IllegalArgumentException("Country " + name + " does not have enough troops.");
  }
  this.armies -= tropes;
  notifyObservers();
  }

  /**
   * Is the relative x coordinate position of the country on any board
   * position is from 0 to 1.
   * */
  public double getRelativeX() {
    return this.relativeX;
  }

  /**
   * Is the relative y coordinate position of the country on any board
   * position is from 0 to 1.
   * */
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

  public void setOwner(String owner) {
    this.owner = owner;
    notifyObservers();
  }

  @Override
  public String toString() {
    return name;
  }
}
