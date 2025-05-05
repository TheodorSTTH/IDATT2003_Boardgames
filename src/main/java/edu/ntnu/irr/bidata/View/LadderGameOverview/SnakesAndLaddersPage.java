package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import javafx.scene.layout.HBox;

public class SnakesAndLaddersPage extends HBox {
  SnakesAndLaddersCanvasView board;
  SnakesAndLaddersSidePanelView sidePanel;

  /**
   * Constructs snake and ladders page.
   * */
  public SnakesAndLaddersPage(LaderGame snakesAndLadders) {
    if (getClass().getResource("/style.css") != null) {
      this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    } else {
      System.err.println("Warning: style.css not found!");
    }
    this.board = new SnakesAndLaddersCanvasView(snakesAndLadders);
    this.sidePanel = new SnakesAndLaddersSidePanelView(snakesAndLadders);
    getChildren().addAll(sidePanel, board);
  }

  public SnakesAndLaddersSidePanelView getSidePanelView() {
    return sidePanel;
  }
}
