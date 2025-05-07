package edu.ntnu.irr.bidata.view.risk;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CountryView extends Button{
  private Label text = new Label();
  private final double boardWidth;
  private final double boardHeight;
  private HBox content = new HBox(5);

  public CountryView(double boardWidth, double boardHeight) {
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
  }

  public void render(Integer amountOfTroops, String ownerColor, double relativeX, double relativeY) {
    text.setText(Integer.toString(amountOfTroops));
    text.getStyleClass().add("country-label");

    ImageView swordIcon = new ImageView(new Image(
        getClass().getResource("/sword.png").toExternalForm()
    ));
    swordIcon.setFitHeight(12);
    swordIcon.setPreserveRatio(true);
    swordIcon.setSmooth(true);

    content.getChildren().setAll(text, swordIcon);
    content.setAlignment(Pos.CENTER_RIGHT);
    this.setGraphic(content);

    this.getStyleClass().add("country-view");
    this.setStyle("-fx-background-color: " + ownerColor + "; ");
    setLayoutX(boardWidth * relativeX);
    setLayoutY(boardHeight * relativeY);
  }
}
