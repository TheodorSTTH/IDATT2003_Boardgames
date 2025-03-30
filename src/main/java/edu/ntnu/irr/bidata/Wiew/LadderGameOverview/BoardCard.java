package edu.ntnu.irr.bidata.Wiew.LadderGameOverview;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Wiew.BoardView;
import edu.ntnu.irr.bidata.Wiew.Tile;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class BoardCard extends VBox {
  BoardView myBoardView = new BoardView();

  public void updateBoard(List<Player> players) {
    myBoardView.updateBoardUI();
    for (Player player : players) {
      Tile playerTile = myBoardView.getTile(player.getCurrentTile());
      playerTile.getChildren().add(new Label(player.getName()));
    }
  }

  public BoardCard() {
    Label label = new Label("Overview over ladder game");
    label.getStyleClass().addAll("styled-label", "w-p-text");

    Button rollButton = new Button("Roll");
    rollButton.getStyleClass().addAll("styled-button", "b-p-text", "b-radius");

    rollButton.setOnAction(e -> {
      UI.triggerNewRound();
    });

    myBoardView.renderBoard();

    this.getChildren().addAll(label, myBoardView, rollButton);
    this.getStyleClass().addAll("createUser-card", "w-radius");
  }
}
