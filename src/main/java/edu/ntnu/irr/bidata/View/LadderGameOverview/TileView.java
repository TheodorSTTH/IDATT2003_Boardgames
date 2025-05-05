package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/**
 * Represents the view a single ladder game tile.
 * */
public abstract class TileView extends FlowPane {
  /**
   * Renders tile with number label.
   * */
  public TileView(int width, int height) {
    setPrefSize(width, height);
    this.setAlignment(Pos.CENTER);
    setStyle("-fx-border-color: black; -fx-background-color: yellow;");
  }

  /**
   * Will be run before we add a player to the tile
   * */
  protected abstract void doBeforePlacingPlayer();

  /**
   * Places and renders a player.
   * */
  public void placePlayer(Player player) {
    doBeforePlacingPlayer();
    PlayerFigureView playerView = new PlayerFigureView(player.getColor());
    getChildren().add(playerView);
  }
}
