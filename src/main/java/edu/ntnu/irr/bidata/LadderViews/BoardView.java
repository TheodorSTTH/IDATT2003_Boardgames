package edu.ntnu.irr.bidata.LadderViews;

import edu.ntnu.irr.bidata.GeneralClassesBackend.Player;
import edu.ntnu.irr.bidata.LaderGameBackend.BoardLaderGame;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BoardView extends Pane {
  private final BoardLaderGame board;
  private final int tileWidth = 40;
  private final int tileHeight = 35;
  private final int boardColumnAmount = 9;
  private final int boardRowsAmount = 10;

  private final ArrayList<Tile> currentTiles = new ArrayList<>(boardColumnAmount * boardRowsAmount);

  public BoardView(BoardLaderGame board) {
    this.board = board;
    for (int i = 0; i < 90; i++) {
      currentTiles.add(null);
    }
    renderBoard();
  }

  private StackPane createBoardSquare(int width, int height, int squareNumber) {
    StackPane square = new StackPane();
    square.setPrefSize(width, height);
    square.setStyle("-fx-border-color: black; -fx-background-color: yellow;");
    Label numberLabel = new Label(Integer.toString(squareNumber));
    square.getChildren().add(numberLabel);
    return square;
  }

  public Tile getTile(int index) {
    return currentTiles.get(index);
  }

  public void renderBoard() {
    for (int row = 0; row < boardRowsAmount; row++) {
      for (int col = 1; col < boardColumnAmount + 1; col++) {
        final int currentTileNumber = row*9 + col;
        Tile currentTile = new Tile(tileWidth, tileHeight, currentTileNumber);
        System.out.println("--YOOOO--" + Integer.toString(currentTileNumber) + "--" + currentTiles.get(currentTileNumber - 1));
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

  public void updateBoardUI() {
    getChildren().clear();
    renderBoard();
  }
}
