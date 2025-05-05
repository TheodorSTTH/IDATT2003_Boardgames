package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class SnakesAndLaddersPage extends Scene {
  SnakesAndLaddersCanvasView board;
  SnakesAndLaddersSidePanelView sidePanel;

  public SnakesAndLaddersPage(LaderGame snakesAndLadders) {
    super(new HBox());
    if (getClass().getResource("/style.css") != null) {
      this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    } else {
      System.err.println("Warning: style.css not found!");
    }
    this.board = new SnakesAndLaddersCanvasView(snakesAndLadders);
    this.sidePanel = new SnakesAndLaddersSidePanelView(snakesAndLadders);
    HBox root = (HBox) this.getRoot();
    root.getChildren().addAll(sidePanel, board);
  }

  public SnakesAndLaddersSidePanelView getSidePanelView() {
    return sidePanel;
  }
}
