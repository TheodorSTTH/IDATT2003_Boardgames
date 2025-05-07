package edu.ntnu.irr.bidata.controller;

import edu.ntnu.irr.bidata.model.Game;
import edu.ntnu.irr.bidata.view.CreatePlayerPageView;
import edu.ntnu.irr.bidata.view.PopUp;
import javafx.scene.control.Button;

public class CreatePlayerPageController {
  private final CreatePlayerPageView view;
  private final Game game;

  public CreatePlayerPageController(Game game) {
    this.view = new CreatePlayerPageView(game.getAvailableColors());
    this.game = game;
    initialize();
  }

  public CreatePlayerPageView getView() {
    return view;
  }

  private void initialize() {
    view.getCreatePlayerButton()
        .setOnAction(
            e -> {
              String username = view.getUsernameField().getText();
              String playerColor = view.getPlayerColorField().getValue();
              Integer age = view.getAgeSpinner().getValue();
              if (username.isEmpty() || playerColor == null || age < 1) {
                PopUp.showWarning(
                    "Selection Required",
                    "Please select a username, color and age before continuing.");
                return;
              }
              if (game.addPlayer(username, playerColor, age)) {
                view.getUsernameField().clear();
                view.getPlayerColorField().getItems().remove(playerColor);
                view.getPlayerColorField().setValue(null);
                view.getAgeSpinner().getValueFactory().setValue(1);
              }
            });

    view.getPlayerColorField()
        .valueProperty()
        .addListener(
            (obs, oldFrom, newFrom) -> {
              String username = view.getUsernameField().getText();
              Button createPlayerButton = view.getCreatePlayerButton();
              createPlayerButton.setDisable(newFrom == null || username.isEmpty());
            });

    view.getUsernameField()
        .textProperty()
        .addListener(
            (obs, oldFrom, newFrom) -> {
              Button createPlayerButton = view.getCreatePlayerButton();
              String playerColor = view.getPlayerColorField().getValue();
              createPlayerButton.setDisable(
                  newFrom == null || newFrom.isEmpty() || playerColor == null);
            });
  }
}
