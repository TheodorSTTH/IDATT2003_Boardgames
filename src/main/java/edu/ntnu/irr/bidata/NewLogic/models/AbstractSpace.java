package edu.ntnu.irr.bidata.NewLogic.models;

public abstract class AbstractSpace<T extends IPlayer<?>> implements ISpace<T> {
  protected final int id;
  protected IAction<T> landAction;

  public AbstractSpace(int id) {
    this.id = id;
  }

  public AbstractSpace(int id, IAction<T> landAction) {
    this.id = id;
    this.landAction = landAction;
  }

  public void landPlayer(T player) {
    if (landAction != null) landAction.perform(player);
  }

  public int getId() {
    return id;
  }

  public void setAction(IAction<T> landAction) {
    this.landAction = landAction;
  }
}
