package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.model.risk.Risk;
import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.interfaces.observer.Subject;
import java.util.ArrayList;

public abstract class AbstractSidebarPaneController implements Subject<AbstractSidebarPaneController> {
  private final ArrayList<Observer<AbstractSidebarPaneController>> allObservers;
  protected final Risk risk;
  private AbstractSidebarPaneController nextSidebarPane;

  public AbstractSidebarPaneController(Risk risk) {
    this.risk = risk;
    this.allObservers = new ArrayList<>();
  }

  @Override
  public void registerObserver(Observer<AbstractSidebarPaneController> o) {
    allObservers.add(o);
  }

  @Override
  public void removeObserver(Observer<AbstractSidebarPaneController> o) {
    allObservers.remove(o);
  }

  @Override
  public void notifyObservers(AbstractSidebarPaneController nextSidebarPane) {
    for (Observer<AbstractSidebarPaneController> observer : allObservers) {
      observer.update(nextSidebarPane);
    }
  }

  public AbstractSidebarPaneController getNextSidebarPane() {
    return this.nextSidebarPane;
  }

  public void setNextSidebarPane(AbstractSidebarPaneController nextSidebarPane) {
    this.nextSidebarPane = nextSidebarPane;
  }

  public abstract AbstractSidebarPaneView getView();
}
