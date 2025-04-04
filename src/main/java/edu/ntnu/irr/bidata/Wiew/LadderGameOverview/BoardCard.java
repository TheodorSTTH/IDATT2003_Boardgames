package edu.ntnu.irr.bidata.Wiew.LadderGameOverview;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Controler.UILaderGame;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Wiew.BoardView;
import edu.ntnu.irr.bidata.Wiew.Tile;

import java.util.HashMap;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class BoardCard extends VBox {
  BoardView myBoardView = new BoardView();

  public void updateBoard(HashMap<String, Integer> playerPositons) {
    myBoardView.updateBoardUI();
    for (String player : playerPositons.keySet()) {
      Tile playerTile = myBoardView.getTile(playerPositons.get(player));
      playerTile.getChildren().add(new Label(player));
    }
  }

  public BoardCard() {
    Label label = new Label("Overview over ladder game");
    label.getStyleClass().addAll("styled-label", "w-p-text");

    Button rollButton = new Button("Roll");
    Button saveButton = new Button("Save current game");
    rollButton.getStyleClass().addAll("styled-button", "b-p-text", "b-radius");

    rollButton.setOnAction(e -> {
      UILaderGame.triggerNewRound();
    });
    saveButton.setOnAction(e -> {
      UI.saveGame();
    });

    myBoardView.renderBoard();

    this.getChildren().addAll(label, myBoardView, rollButton, saveButton);
    this.getStyleClass().addAll("createUser-card", "w-radius");
  }
}
