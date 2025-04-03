package edu.ntnu.irr.bidata.NewLogic.models;

public interface ISpace<T extends IPlayer<?>> {
  int getId();
  void landPlayer(T player);
  void setAction(IAction<T> action);
}
