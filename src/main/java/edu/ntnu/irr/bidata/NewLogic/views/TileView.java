package edu.ntnu.irr.bidata.NewLogic.views;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class TileView extends StackPane {
  public TileView(int width, int height, int squareNumber) {
    setPrefSize(width, height);
    setStyle("-fx-border-color: black; -fx-background-color: yellow;");
    Label numberLabel = new Label(Integer.toString(squareNumber));
    getChildren().add(numberLabel);
  }
}