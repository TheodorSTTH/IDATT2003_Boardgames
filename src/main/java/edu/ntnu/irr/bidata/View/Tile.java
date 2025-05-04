package edu.ntnu.irr.bidata.View;

import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.View.LadderGameOverview.PlayerFigureView;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class Tile extends FlowPane {
  public Tile(int width, int height, int squareNumber) {
    setPrefSize(width, height);
    this.setAlignment(Pos.CENTER);
    setStyle("-fx-border-color: black; -fx-background-color: yellow;");
    Label numberLabel = new Label(Integer.toString(squareNumber));
    getChildren().add(numberLabel);
  }

  public void placePlayer(Player player) {
    getChildren().removeIf(child -> child instanceof Label);
    PlayerFigureView playerView = new PlayerFigureView(player.getColor());
    getChildren().add(playerView);
  }
}
