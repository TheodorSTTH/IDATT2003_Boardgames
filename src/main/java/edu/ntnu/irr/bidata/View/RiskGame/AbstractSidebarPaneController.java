package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISubject;
import java.util.ArrayList;

public abstract class AbstractSidebarPaneController implements ISubject<AbstractSidebarPaneController> {
  private final ArrayList<IObserver<AbstractSidebarPaneController>> allObservers;
  protected final Risk risk;
  private AbstractSidebarPaneController nextSidebarPane;

  public AbstractSidebarPaneController(Risk risk) {
    this.risk = risk;
    this.allObservers = new ArrayList<>();
  }

  @Override
  public void registerObserver(IObserver<AbstractSidebarPaneController> o) {
    allObservers.add(o);
  }

  @Override
  public void removeObserver(IObserver<AbstractSidebarPaneController> o) {
    allObservers.remove(o);
  }

  @Override
  public void notifyObservers(AbstractSidebarPaneController nextSidebarPane) {
    for (IObserver<AbstractSidebarPaneController> observer : allObservers) {
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
