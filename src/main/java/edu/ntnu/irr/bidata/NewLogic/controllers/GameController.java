package edu.ntnu.irr.bidata.NewLogic.controllers;

import edu.ntnu.irr.bidata.NewLogic.models.LadderGame;
import edu.ntnu.irr.bidata.NewLogic.views.GameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GameController {

  private final LadderGame game;
  private final GameView view;

  public GameController(LadderGame game, GameView view) {
    this.game = game;
    this.view = view;
    initController();
  }

  private void initController() {
    view.getRollButton().setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        playTurnWithUI();
      }
    });
  }

  private void playTurnWithUI() {
    game.playTurn();
    view.updateStatus();

    if (game.isFinished()) {
      view.getRollButton().setDisable(true);
      System.out.println("Winner: " + game.getWinner().getName());
    }
  }
}
