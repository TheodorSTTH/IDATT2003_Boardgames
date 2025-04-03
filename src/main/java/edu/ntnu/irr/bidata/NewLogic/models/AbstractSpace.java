package edu.ntnu.irr.bidata.NewLogic.models;

public abstract class AbstractSpace<T extends IPlayer, U> implements ISpace<U> {
  protected final int id;
  protected IAction<U> action;

  public AbstractSpace(int id) {
    this.id = id;
  }

  public AbstractSpace(int id, IAction<U> action) {
    this.id = id;
    this.action = action;
  }

  public void performAction(U subject) {
    if (action != null) action.perform(subject);
  }

  public int getId() {
    return id;
  }

  public void setAction(IAction<U> landAction) {
    this.action = landAction;
  }
}
