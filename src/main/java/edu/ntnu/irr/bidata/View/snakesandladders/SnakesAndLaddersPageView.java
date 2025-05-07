package edu.ntnu.irr.bidata.view.snakesandladders;

import edu.ntnu.irr.bidata.NavigationManager;
import edu.ntnu.irr.bidata.controller.snakesandladders.SnakesAndLaddersCanvasController;
import edu.ntnu.irr.bidata.controller.snakesandladders.SnakesAndLaddersSidePanelController;
import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/** View for the Snakes and Ladders game page. It contains the game board and side panel. */
public class SnakesAndLaddersPageView extends HBox {

  // Instance variables for the game board and side panel views
  private SnakesAndLaddersCanvasView canvasBoardView;
  private SnakesAndLaddersSidePanelView sidePanel;

  /**
   * Constructs the Snakes and Ladders page view.
   *
   * @param snakesAndLadders The game model containing game logic and state.
   */
  public SnakesAndLaddersPageView(SnakesAndLadders snakesAndLadders) {
    // Set up the style and alignment of the HBox container
    getStyleClass().add("snakes-and-ladders-game-page");
    setAlignment(Pos.CENTER);

    // Initialize the canvas view for the board and side panel
    this.canvasBoardView = new SnakesAndLaddersCanvasController(snakesAndLadders).getView();
    this.sidePanel = new SnakesAndLaddersSidePanelController(snakesAndLadders).getView();

    // Bind the side panel's preferred width to 25% of the HBox's width
    sidePanel.prefWidthProperty().bind(this.widthProperty().multiply(0.25));
    // Set initial margins for the side panel
    setMargin(sidePanel, new Insets(40));

    // Add a listener to adjust the side panel's margin based on the stage width
    NavigationManager.getStage()
        .widthProperty()
        .addListener(
            (obs, oldW, newW) -> {
              double stageWidth = newW.doubleValue();
              // If the width of the stage is less than 800px, reduce the side panel margin
              if (stageWidth < 800) {
                setMargin(sidePanel, new Insets(10));
              } else {
                setMargin(sidePanel, new Insets(40));
              }
            });

    // Create a Region to act as a flexible splitter between the side panel and board
    Region splitterRegion = new Region();
    HBox.setHgrow(splitterRegion, Priority.ALWAYS); // Allow the region to expand

    // Add the side panel, canvas view, and splitter region to the layout
    getChildren().addAll(sidePanel, canvasBoardView, splitterRegion);
  }

  /**
   * Returns the side panel view.
   *
   * @return The SnakesAndLaddersSidePanelView associated with this page.
   */
  public SnakesAndLaddersSidePanelView getSidePanelView() {
    return sidePanel;
  }
}
