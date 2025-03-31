package edu.ntnu.irr.bidata.NewLogic.models;

/**
 * Interface for all actions you can perform on a tile.
 * */
public interface IAction<T extends IPlayer<?>> {
  public void perform(T player);
}
