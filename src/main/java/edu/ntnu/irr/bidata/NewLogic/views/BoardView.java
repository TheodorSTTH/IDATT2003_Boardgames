package edu.ntnu.irr.bidata.NewLogic.views;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BoardView extends Pane {
  private final int tileWidth = 40;
  private final int tileHeight = 35;
  private final int boardColumnAmount = 9;
  private final int boardRowsAmount = 10;

  private final ArrayList<TileView> currentTiles = new ArrayList<>(boardColumnAmount * boardRowsAmount);

  public BoardView() {
    for (int i = 0; i < 90; i++) {
      currentTiles.add(null);
    }
    renderBoard();
  }

  public void renderBoard() {
    for (int row = 0; row < boardRowsAmount; row++) {
      for (int col = 1; col < boardColumnAmount + 1; col++) {
        final int currentTileNumber = row*9 + col;
        TileView currentTile = new TileView(tileWidth, tileHeight, currentTileNumber);
        currentTiles.set(currentTileNumber - 1, currentTile);
        if (row % 2 == 0) {
          currentTile.setLayoutX((col - 1)*tileWidth);
        } else {
          currentTile.setLayoutX(tileWidth * boardColumnAmount - col*tileWidth);
        }
        currentTile.setLayoutY(tileHeight * (boardRowsAmount - 1) - row*tileHeight);
        getChildren().add(currentTile);
      }
    }
  }

  public TileView getTile(int index) {
    return currentTiles.get(index);
  }

  public void updateBoardUI() {
    getChildren().clear();
    renderBoard();
  }
}
