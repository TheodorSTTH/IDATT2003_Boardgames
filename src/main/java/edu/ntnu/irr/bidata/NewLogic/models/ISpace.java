package edu.ntnu.irr.bidata.NewLogic.models;

public interface ISpace<T> {
  int getId();
  void performAction(T subject);
  void setAction(IAction<T> action);
}
