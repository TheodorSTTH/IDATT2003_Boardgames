package edu.ntnu.irr.bidata.NewLogic.controllers;

import edu.ntnu.irr.bidata.NewLogic.models.BoardGame;
import edu.ntnu.irr.bidata.NewLogic.views.GameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GameController {
  private BoardGame game;
  private GameView view;

  public GameController(BoardGame game, GameView view) {
    this.game = game;
    this.view = view;
    initController();
  }

  private void initController() {
    view.getRollButton().setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        playRound();
      }
    });
  }

  private void playRound() {
    game.playOneTurn();
    view.updateStatus();

    if (game.isFinished()) {
      view.getRollButton().setDisable(true);
      System.out.println("Winner: " + game.getWinner().getName());
    }
  }
}
