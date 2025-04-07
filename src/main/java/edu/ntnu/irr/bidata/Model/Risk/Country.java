package edu.ntnu.irr.bidata.Model.Risk;
import java.util.List;

import edu.ntnu.irr.bidata.Model.Player;

public class Country {
  private final String name;
  private int armies = 0;
  private String owner = null;
  private List<String> neighbors;

  public Country(String name, List<String> neighbors) {
    this.name = name;
    this.neighbors = neighbors;
  }

  public void placeTropes(int tropes) {
    this.armies += tropes;
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

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }
}
