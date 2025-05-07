package edu.ntnu.irr.bidata.model.interfaces.observer;

/**
 * Is the subject in the Observer design pattern. Is meant for the object being observed by another
 * object. Supports sending objects upwards
 */
public interface Subject<T> {
  /** Registers an observer in the observer list of the subject. */
  void registerObserver(Observer<T> o);

  void removeObserver(Observer<T> o);

  void notifyObservers(T data);
}
