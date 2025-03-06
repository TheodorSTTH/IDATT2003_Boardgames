package edu.ntnu.irr.bidata.LadderViews;

import edu.ntnu.irr.bidata.LaderGameBackend.BoardLaderGame;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BoardView extends Pane {
  private final BoardLaderGame board;
  private final int tileWidth = 40;
  private final int tileHeight = 35;
  private final int boardTileWidth = 9;
  private final int boardTileHeight = 10;


  public BoardView(BoardLaderGame board) {
    this.board = board;
    initializeBoardUI();
  }

  private void initializeBoardUI() {
    for (int i = 0; i < boardTileHeight; i++) {
      for (int j = 1; j < boardTileWidth + 1; j++) {
        StackPane square = new StackPane();
        square.setPrefSize(tileWidth, tileHeight);
        square.setStyle("-fx-border-color: black; -fx-background-color: yellow;");
        final int currentTileKey = i*9 + j;
        Label numberLabel = new Label(Integer.toString(currentTileKey));
        square.getChildren().add(numberLabel);
        if (i % 2 == 0) {
          square.setLayoutX((j - 1)*tileWidth);
        } else {
          square.setLayoutX(tileWidth * boardTileWidth - j*tileWidth);
        }
        square.setLayoutY(tileHeight * (boardTileHeight - 1) - i*tileHeight);
        getChildren().add(square);
      }
    }
  }
}
