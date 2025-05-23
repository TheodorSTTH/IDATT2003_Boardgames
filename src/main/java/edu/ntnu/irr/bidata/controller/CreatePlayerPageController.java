package edu.ntnu.irr.bidata.controller;

import edu.ntnu.irr.bidata.NavigationManager;
import edu.ntnu.irr.bidata.controller.risk.RiskPageController;
import edu.ntnu.irr.bidata.model.newlogic.Game;
import edu.ntnu.irr.bidata.model.newlogic.PlayerManager;
import edu.ntnu.irr.bidata.model.newlogic.risk.RiskGame;
import edu.ntnu.irr.bidata.model.newlogic.snakesandladders.SnakesAndLaddersGame;
import edu.ntnu.irr.bidata.view.CreatePlayerPageView;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.snakesandladders.SnakesAndLaddersPageView;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

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

              try {
                System.out.println("GOT TO TRY");
                PlayerManager playerManager = game.getPlayerManager();
                if (playerManager.addPlayer(username, playerColor, age)) {
                  System.out.println("GOT IN IF");
                  view.getUsernameField().clear();
                  view.getPlayerColorField().getItems().remove(playerColor);
                  view.getPlayerColorField().setValue(null);
                  view.getAgeSpinner().getValueFactory().setValue(1);

                  boolean isFinishedAddingPlayers = playerManager.getSelectedAmountOfPlayers() <= playerManager.getPlayers().size();
                  if (isFinishedAddingPlayers) {
                    System.out.println("GOT IN FINISHED ADDING PLAYERS");
                    if (game instanceof SnakesAndLaddersGame) {
                      // Route to snakes and ladders page with created game
                      SnakesAndLaddersPageView snakesAndLaddersPageView =
                          new SnakesAndLaddersPageView((SnakesAndLaddersGame) game);
                      ((SnakesAndLaddersGame) game).start();
                      NavigationManager.navigate(snakesAndLaddersPageView);
                    } else {
                      ((RiskGame) game).getBoard().setUpBoard(game.getPlayerManager());
                      // Route to risk page with created game
                      RiskPageController riskPageController = new RiskPageController((RiskGame) game);
                      NavigationManager.navigate(riskPageController.getView());
                    }
                    // Show rules for the given game
                    PopUp.showScrollablePopup("Rules", game.getRules());
                  }
                }
              } catch (IllegalArgumentException ex) {
                PopUp.showWarning("Invalid name", ex.getMessage());
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
