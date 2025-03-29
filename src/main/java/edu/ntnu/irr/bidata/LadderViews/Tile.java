package edu.ntnu.irr.bidata.LadderViews;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane {
  public Tile(int width, int height, int squareNumber) {
    setPrefSize(width, height);
    setStyle("-fx-border-color: black; -fx-background-color: yellow;");
    Label numberLabel = new Label(Integer.toString(squareNumber));
    getChildren().add(numberLabel);
  }
}
