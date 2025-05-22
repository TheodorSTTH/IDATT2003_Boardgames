package edu.ntnu.irr.bidata.model.newlogic.risk;

import edu.ntnu.irr.bidata.model.newlogic.Space;
import java.util.ArrayList;
import java.util.List;

public class Country extends Space<String> {
  private ArrayList<Country> neighbours;
  private Army army;
  private double x;
  private double y;

  public Country(String name, double x, double y) {
    super(name);
    this.neighbours = new ArrayList<>();
    this.x = x;
    this.y = y;
  }

  public Army getArmy() {
    return army;
  }
  public void setArmy(Army army) {
    this.army = army;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public String getName() {
    return name;
  }

  public ArrayList<Country> getNeighbours() {
    return neighbours;
  }

  public void setNeighbours(List<Country> neighbours) {
    this.neighbours = (ArrayList) neighbours;
  }
}
