package edu.ntnu.irr.bidata.Model.Risk;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISimpleObserver;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISimpleSubject;
import java.util.ArrayList;
import java.util.List;

public class Country implements ISimpleSubject {
  private final String name;
  private final double relativeX;
  private final double relativeY;
  private int armies = 0;
  private String owner = null; // TODO: Make this a player object. We need the player color
  private final List<String> neighbors;

  private final ArrayList<ISimpleObserver> allObservers;

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

  public Country(String name, List<String> neighbors, double relativeX, double relativeY) {
    this.name = name;
    this.neighbors = neighbors;
    this.relativeX = Math.clamp(relativeX, 0, 1);
    this.relativeY = Math.clamp(relativeY, 0, 1);
    this.allObservers = new ArrayList<>();
    notifyObservers();
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
