package edu.ntnu.irr.bidata.model.interfaces.observer;

/**
 * Is the observer in the Observer design pattern. Is meant for
 * the object observing another object. Supports passing a value
 * upwards
 * */
public interface IObserver<T> {
  void update(T item);
}
