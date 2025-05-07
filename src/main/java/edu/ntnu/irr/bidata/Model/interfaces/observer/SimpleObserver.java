package edu.ntnu.irr.bidata.model.interfaces.observer;

/**
 * Is the observer in the Observer design pattern. Is meant for
 * the object observing another object. Does not support passing
 * values upwards
 * */
public interface SimpleObserver {
  void update();
}
