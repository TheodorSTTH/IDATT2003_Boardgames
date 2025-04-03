package edu.ntnu.irr.bidata.NewLogic.models;

/**
 * Interface for all actions you can perform on a tile.
 * */
public interface IAction<T> {
  public void perform(T subject);
}
