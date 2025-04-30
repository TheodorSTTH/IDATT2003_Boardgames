package edu.ntnu.irr.bidata.Model.interfaces.observer;

/**
 * Is the subject in the Observer design pattern. Is meant for
 * the object being observed by another object.
 * */
public interface ISubject {
  /**
   * Registers an observer in the observer list of the subject.
   * */
  void registerObserver(IObserver o);
  void removeObserver(IObserver o);
  void notifyObservers();
}