package edu.ntnu.irr.bidata.model.newlogic;

import edu.ntnu.irr.bidata.model.interfaces.observer.SimpleObserver;
import edu.ntnu.irr.bidata.model.interfaces.observer.SimpleSubject;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Space<T> implements SimpleSubject {
  protected T name;
  private final ArrayList<SimpleObserver> allObservers;
  protected ArrayList<Action> actionsOnSpace;

  public Space(T name) {
    this.name = name;
    this.allObservers = new ArrayList<>();
    this.actionsOnSpace = new ArrayList<>();
  }

  public void addAction(Action action) {
    actionsOnSpace.add(action);
  }

  public ArrayList<Action> getActionsOnSpace() {
    return actionsOnSpace;
  }

  public T getName() {
    return name;
  }


  @Override
  public void registerObserver(SimpleObserver o) {
    allObservers.add(o);
  }

  @Override
  public void removeObserver(SimpleObserver o) {
    allObservers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (SimpleObserver observer : allObservers) {
      observer.update();
    }
  }
}
