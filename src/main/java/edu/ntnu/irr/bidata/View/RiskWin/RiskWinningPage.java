package edu.ntnu.irr.bidata.View.RiskWin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RiskWinningPage extends VBox {
  public RiskWinningPage(String winner) {
    getStyleClass().add("risk-win-page");
    setAlignment(Pos.TOP_RIGHT);
    Label winLabel = new Label("You win " + winner + "!");
    VBox.setMargin(winLabel, new Insets(10, 20, 10, 20));
    winLabel.getStyleClass().add("fantasy-title");
    getChildren().add(winLabel);
  }
}
