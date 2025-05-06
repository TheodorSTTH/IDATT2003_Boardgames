package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISubject;
import java.util.ArrayList;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class AbstractSidebarPane extends TitledPane implements ISubject<AbstractSidebarPane> {
  protected final Risk risk;
  private AbstractSidebarPane nextSidebarPane;
  private final ArrayList<IObserver<AbstractSidebarPane>> allObservers;
  private final VBox container;

  public AbstractSidebarPane(Risk risk) {
    this.risk = risk;
    this.allObservers = new ArrayList<>();
    this.container = new VBox();
    setContent(container);
  }

  protected VBox getContainer() {
    return container;
  }

  @Override
  public void registerObserver(IObserver<AbstractSidebarPane> o) {
    allObservers.add(o);
  }

  @Override
  public void removeObserver(IObserver<AbstractSidebarPane> o) {
    allObservers.remove(o);
  }

  @Override
  public void notifyObservers(AbstractSidebarPane nextSidebarPane) {
    for (IObserver<AbstractSidebarPane> observer : allObservers) {
      observer.update(nextSidebarPane);
    }
  }

  /**
   * Gets the next plane which we route to once user is finished.
   *
   * @return next pane we route to.
   * */
  public AbstractSidebarPane getNextSidebarPane() {
    return this.nextSidebarPane;
  }

  /**
   * Sets the pane which we route to when the user is finished.
   *
   * @param nextSidebarPane Is the pane which we link to
   * */
  public void setNextSidebarPane(AbstractSidebarPane nextSidebarPane) {
    this.nextSidebarPane = nextSidebarPane;
  }
}
