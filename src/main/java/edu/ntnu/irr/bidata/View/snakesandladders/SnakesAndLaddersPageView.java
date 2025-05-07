package edu.ntnu.irr.bidata.view.snakesandladders;

import edu.ntnu.irr.bidata.controller.NavigationManager;
import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class SnakesAndLaddersPageView extends HBox {
  SnakesAndLaddersCanvasView canvasBoardView;
  SnakesAndLaddersSidePanelView sidePanel;

  /**
   * Constructs snake and ladders page.
   * */
  public SnakesAndLaddersPageView(SnakesAndLadders snakesAndLadders) {
    getStyleClass().add("snakes-and-ladders-game-page");
    setAlignment(Pos.CENTER);
    this.canvasBoardView = new SnakesAndLaddersCanvasController(snakesAndLadders).getView();
    this.sidePanel = new SnakesAndLaddersSidePanelController(snakesAndLadders).getView();
    sidePanel.prefWidthProperty().bind(this.widthProperty().multiply(0.25));
    setMargin(sidePanel, new Insets(40));
    NavigationManager.getStage().widthProperty().addListener((obs, oldW, newW) -> {
      double stageWidth = newW.doubleValue();
      if (stageWidth < 800) {
        setMargin(sidePanel, new Insets(10));
      } else {
        setMargin(sidePanel, new Insets(40));
      }
    });
    Region splitterRegion = new Region();
    HBox.setHgrow(splitterRegion, Priority.ALWAYS);
    getChildren().addAll(
        sidePanel,
        canvasBoardView,
        splitterRegion
    );
  }

  public SnakesAndLaddersSidePanelView getSidePanelView() {
    return sidePanel;
  }
}
