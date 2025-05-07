package edu.ntnu.irr.bidata.view.risk;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public abstract class AbstractSidebarPaneView extends TitledPane {
  private final VBox container;
  private final ScrollPane scrollPane;

  public AbstractSidebarPaneView() {
    this.container = new VBox();
    this.scrollPane = new ScrollPane(container);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    setContent(scrollPane);
  }

  protected VBox getContainer() {
    return container;
  }
}
