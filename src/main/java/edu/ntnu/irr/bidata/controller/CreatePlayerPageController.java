package edu.ntnu.irr.bidata.controller;

import edu.ntnu.irr.bidata.model.Game;
import edu.ntnu.irr.bidata.view.CreatePlayerPageView;
import edu.ntnu.irr.bidata.view.PopUp;
import javafx.scene.control.Button;

/**
 * Controller responsible for managing the create player page. Handles user interactions for adding
 * a new player to the game and validates input fields.
 */
public class CreatePlayerPageController {
  private final CreatePlayerPageView view;
  private final Game game;

  /**
   * Constructs a new controller for the create player page.
   *
   * @param game the game instance to which players will be added
   */
  public CreatePlayerPageController(Game game) {
    this.view = new CreatePlayerPageView(game.getAvailableColors());
    this.game = game;
    initialize();
  }

  /**
   * Returns the view associated with this controller.
   *
   * @return the CreatePlayerPageView instance
   */
  public CreatePlayerPageView getView() {
    return view;
  }

  /** Initializes event handlers and bindings for user input validation and player creation. */
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
