package edu.ntnu.irr.bidata.view.risk;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * AbstractSidebarPaneView represents a sidebar pane that can hold scrollable content within a
 * titled pane. It contains a VBox as the container for its contents and is wrapped inside a
 * ScrollPane to ensure the content is scrollable if necessary.
 *
 * <p>This class serves as a base class for creating sidebar views with specific contents.
 */
public abstract class AbstractSidebarPaneView extends TitledPane {

  private final VBox container; // VBox container to hold content in the sidebar
  private final ScrollPane scrollPane; // ScrollPane to make the container scrollable

  /**
   * Constructs an AbstractSidebarPaneView and initializes the layout. It creates a VBox as the
   * content container, wraps it in a ScrollPane, and sets this ScrollPane as the content of the
   * TitledPane.
   */
  public AbstractSidebarPaneView() {
    this.container = new VBox(); // Initialize the VBox container
    this.scrollPane = new ScrollPane(container); // Wrap the container in a ScrollPane

    // Set the ScrollPane to automatically adjust its size to fit the available space
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

    // Set the ScrollPane as the content of the TitledPane
    setContent(scrollPane);
  }

  /**
   * Returns the VBox container used to hold the content of the sidebar. This allows subclasses to
   * add specific content to the sidebar.
   *
   * @return the VBox container
   */
  protected VBox getContainer() {
    return container; // Return the container to allow access to the content area
  }
}
