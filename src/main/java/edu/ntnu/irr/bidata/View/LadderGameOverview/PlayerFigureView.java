package edu.ntnu.irr.bidata.View.LadderGameOverview;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerFigureView extends Pane {
  public PlayerFigureView(String color) {
    int width = 15;
    int height = 15;
    this.setPrefSize(width, height);
    this.setMaxSize(width, height);
    this.setStyle("-fx-background-color: " + color + ";");
  }
}
