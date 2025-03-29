package edu.ntnu.irr.bidata.Wiew.LadderGameOverview;

import javafx.scene.layout.StackPane;

public class OverviewLayout extends StackPane {
  private BoardCard boardCard;
  public OverviewLayout() {
    super();
    this.getStyleClass().add("createPlayer-layout");
    this.boardCard = new BoardCard();
    this.getChildren().add(boardCard);
  }
  public BoardCard getBoardCard() {
    return boardCard;
  }
}