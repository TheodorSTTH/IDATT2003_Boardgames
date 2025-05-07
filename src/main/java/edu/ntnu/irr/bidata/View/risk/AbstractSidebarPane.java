package edu.ntnu.irr.bidata.view.risk;

import java.util.ArrayList;

import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.interfaces.observer.Subject;
import edu.ntnu.irr.bidata.model.risk.Risk;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

public class AbstractSidebarPane extends TitledPane implements Subject<AbstractSidebarPane> {
  protected final Risk risk;
  private AbstractSidebarPane nextSidebarPane;
  private final ArrayList<Observer<AbstractSidebarPane>> allObservers;
  private final VBox container;
  private final ScrollPane scrollPane;

  public AbstractSidebarPane(Risk risk) {
    this.risk = risk;
    this.allObservers = new ArrayList<>();
    this.container = new VBox();
    this.scrollPane = new ScrollPane(container); 
    scrollPane.setFitToWidth(true); 
    scrollPane.setFitToHeight(true); 
    setContent(scrollPane); 
  }

  protected VBox getContainer() {
    return container;
  }

  @Override
  public void registerObserver(Observer<AbstractSidebarPane> o) {
    allObservers.add(o);
  }

  @Override
  public void removeObserver(Observer<AbstractSidebarPane> o) {
    allObservers.remove(o);
  }

  @Override
  public void notifyObservers(AbstractSidebarPane nextSidebarPane) {
    for (Observer<AbstractSidebarPane> observer : allObservers) {
      observer.update(nextSidebarPane);
    }
  }

  public AbstractSidebarPane getNextSidebarPane() {
    return this.nextSidebarPane;
  }

  public void setNextSidebarPane(AbstractSidebarPane nextSidebarPane) {
    this.nextSidebarPane = nextSidebarPane;
  }
}
