package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SnakesAndLaddersPage extends HBox {
  SnakesAndLaddersCanvasView board;
  SnakesAndLaddersSidePanelView sidePanel;

  /**
   * Constructs snake and ladders page.
   * */
  public SnakesAndLaddersPage(LaderGame snakesAndLadders) {
    getStyleClass().add("snakes-and-ladders-game-page");
    setAlignment(Pos.CENTER);
    if (getClass().getResource("/style.css") != null) {
      this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    } else {
      System.err.println("Warning: style.css not found!");
    }
    this.board = new SnakesAndLaddersCanvasView(snakesAndLadders);
    this.sidePanel = new SnakesAndLaddersSidePanelView(snakesAndLadders);
    setMargin(sidePanel, new Insets(40));
    Region splitterRegion = new Region();
    HBox.setHgrow(splitterRegion, Priority.ALWAYS);
    getChildren().addAll(
        sidePanel,
        board,
        splitterRegion
    );
  }

  public SnakesAndLaddersSidePanelView getSidePanelView() {
    return sidePanel;
  }
}
