package edu.ntnu.irr.bidata.View.RiskGame;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class RiskSidePanelView extends ScrollPane {
  public void render() {
    VBox vbox = new VBox();
    vbox.getChildren().add(new Label("Side pane"));
    this.setContent(vbox);
  }
}
