package edu.ntnu.irr.bidata.RiskBackend;
import java.util.List;

import edu.ntnu.irr.bidata.GeneralClassesBackend.Player;

public class Country {
  private final String name;
  private int armies = 0;
  private Player owner = null;
  private List<String> neighbors;

  public Country(String name, List<String> neighbors) {
    this.name = name;
    this.neighbors = neighbors;
  }

  public List<String> getNeighbors() {
    return neighbors;
  }

  public String getName() {
    return name;
  }

  public int getArmies() {
    return armies;
  }

  public void setArmies(int armies) {
    this.armies = armies;
  }

  public Player getOwner() {
    return owner;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }
  
}
