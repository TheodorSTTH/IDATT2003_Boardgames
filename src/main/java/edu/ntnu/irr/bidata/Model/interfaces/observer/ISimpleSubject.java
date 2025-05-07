package edu.ntnu.irr.bidata.model.interfaces.observer;

/**
 * Is the subject in the Observer design pattern. Is meant for
 * the object being observed by another object. Does not support
 * sending objects upwards.
 * */
public interface ISimpleSubject {
  /**
   * Registers an observer in the observer list of the subject.
   * */
  void registerObserver(ISimpleObserver o);
  void removeObserver(ISimpleObserver o);
  void notifyObservers();
}