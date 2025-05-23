package edu.ntnu.irr.bidata.controller.risk;

import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.interfaces.observer.Subject;
import edu.ntnu.irr.bidata.model.newlogic.risk.RiskGame;
import edu.ntnu.irr.bidata.view.risk.AbstractSidebarPaneView;
import java.util.ArrayList;

/**
 * Abstract class for the controllers of the sidebar panes in the Risk game. It implements the
 * Subject interface to notify observers when the sidebar pane changes.
 */
public abstract class AbstractSidebarPaneController
    implements Subject<AbstractSidebarPaneController> {

  private final ArrayList<Observer<AbstractSidebarPaneController>>
      allObservers; // List of observers
  protected final RiskGame risk; // The Risk game instance
  private AbstractSidebarPaneController nextSidebarPane; // The next sidebar pane to be shown

  /**
   * Constructor for the AbstractSidebarPaneController. Initializes the Risk game instance and the
   * observers list.
   *
   * @param risk The Risk game instance.
   */
  public AbstractSidebarPaneController(RiskGame risk) {
    this.risk = risk;
    this.allObservers = new ArrayList<>();
  }

  /**
   * Registers an observer to be notified when the sidebar pane changes.
   *
   * @param o The observer to be registered.
   */
  @Override
  public void registerObserver(Observer<AbstractSidebarPaneController> o) {
    allObservers.add(o);
  }

  /**
   * Removes an observer from the list of observers.
   *
   * @param o The observer to be removed.
   */
  @Override
  public void removeObserver(Observer<AbstractSidebarPaneController> o) {
    allObservers.remove(o);
  }

  /**
   * Notifies all registered observers that the sidebar pane has changed. The observers will be
   * updated with the new sidebar pane.
   *
   * @param nextSidebarPane The next sidebar pane to be shown.
   */
  @Override
  public void notifyObservers(AbstractSidebarPaneController nextSidebarPane) {
    for (Observer<AbstractSidebarPaneController> observer : allObservers) {
      observer.update(nextSidebarPane);
    }
  }

  /**
   * Gets the next sidebar pane to be shown.
   *
   * @return The next sidebar pane.
   */
  public AbstractSidebarPaneController getNextSidebarPane() {
    return this.nextSidebarPane;
  }

  /**
   * Sets the next sidebar pane to be shown.
   *
   * @param nextSidebarPane The next sidebar pane.
   */
  public void setNextSidebarPane(AbstractSidebarPaneController nextSidebarPane) {
    this.nextSidebarPane = nextSidebarPane;
  }

  /**
   * Abstract method to get the view associated with this controller. Each specific controller will
   * implement its own version of this method.
   *
   * @return The view for this sidebar pane.
   */
  public abstract AbstractSidebarPaneView getView();
}
