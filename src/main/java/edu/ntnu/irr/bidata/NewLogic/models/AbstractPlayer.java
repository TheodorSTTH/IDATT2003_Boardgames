package edu.ntnu.irr.bidata.NewLogic.models;

public abstract class AbstractPlayer<T extends ISpace<?>> implements IPlayer  {
  protected final String name;

  public AbstractPlayer(String name) {
    this.name = name;
  }
  public String getName() {
    return name;
  }
  abstract public boolean hasWon();
}
