package edu.ntnu.irr.bidata.NewLogic.models;

public interface IPlayer<T extends ISpace<?>> {
  String getName();
  void setCurrentSpace(T space);
  T getCurrentSpace();
}
