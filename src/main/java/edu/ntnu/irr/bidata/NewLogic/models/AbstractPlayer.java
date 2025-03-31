package edu.ntnu.irr.bidata.NewLogic.models;

public abstract class AbstractPlayer<T extends ISpace<?>> implements IPlayer<T>  {
  protected final String name;
  protected T currentSpace;

  public AbstractPlayer(String name, T currentSpace) {
    this.name = name;
    this.currentSpace = currentSpace;
  }
  public String getName() {
    return name;
  }
  public void setCurrentSpace(T space) {
    this.currentSpace = space;
  }
  public T getCurrentSpace() {
    return currentSpace;
  }
}
