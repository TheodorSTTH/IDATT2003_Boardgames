package edu.ntnu.irr.bidata.model.interfaces.observer;

/**
 * Is the subject in the Observer design pattern. Is meant for the object being observed by another
 * object. Does not support sending objects upwards.
 */
public interface SimpleSubject {
  /** Registers an observer in the observer list of the subject. */
  void registerObserver(SimpleObserver o);

  void removeObserver(SimpleObserver o);

  void notifyObservers();
}
