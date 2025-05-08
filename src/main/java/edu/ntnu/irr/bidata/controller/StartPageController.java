package edu.ntnu.irr.bidata.controller;

import edu.ntnu.irr.bidata.MyWindow;
import edu.ntnu.irr.bidata.NavigationManager;
import edu.ntnu.irr.bidata.model.FileHandler;
import edu.ntnu.irr.bidata.model.Game;
import edu.ntnu.irr.bidata.model.risk.Risk;
import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.StartPageView;
import edu.ntnu.irr.bidata.view.risk.RiskPage;
import edu.ntnu.irr.bidata.view.snakesandladders.SnakesAndLaddersPageView;
import java.io.UncheckedIOException;
import java.util.HashMap;
import javafx.beans.value.ChangeListener;

/**
 * Controller for the start page. Handles input validation, new game creation, loading saved games,
 * and navigation to the appropriate game or setup views.
 */
public class StartPageController {
  private final StartPageView view;
  private HashMap<String, String> savedGamesMap;

  /** Constructs a new StartPageController. Initializes the view and UI event handlers. */
  public StartPageController() {
    try {
      this.savedGamesMap = FileHandler.getSavedGames();
    } catch (UncheckedIOException e) {
      PopUp.showError("Something went wrong trying to get saved games", e.getMessage());
    }
    this.view = new StartPageView(savedGamesMap);
    initialize();
  }

  /**
   * Returns the view associated with this controller.
   *
   * @return the StartPageView instance
   */
  public StartPageView getView() {
    return view;
  }

  /** Initializes UI event listeners and bindings for buttons and input fields. */
  private void initialize() {
    view.getConfirmButton().setOnAction(event -> handleConfirm());
    view.getExitButton().setOnAction(event -> MyWindow.closeApplication());
    view.getLoadGameButton().setOnAction(event -> handleLoad());

    ChangeListener<Object> enabler =
        (obs, oldV, newV) -> view.getConfirmButton().setDisable(!allInputsValid());
    view.getGameNameField().textProperty().addListener(enabler);
    view.getPlayerCountBox().valueProperty().addListener(enabler);
    view.getGameSelectorBox().valueProperty().addListener(enabler);

    view.getSavedGamesBox()
        .valueProperty()
        .addListener((obs, oldV, newV) -> view.getLoadGameButton().setDisable(newV.isEmpty()));
  }

  /**
   * Validates that all required inputs for starting a new game are provided.
   *
   * @return true if all required inputs are valid; false otherwise
   */
  private boolean allInputsValid() {
    return !view.getGameNameField().getText().isBlank()
        && view.getPlayerCountBox().getValue() != null
        && view.getGameSelectorBox().getValue() != null;
  }

  /** Handles confirmation for starting a new game. Validates input before proceeding. */
  private void handleConfirm() {
    String gameName = view.getGameNameField().getText();
    Integer selectedPlayerCount = view.getPlayerCountBox().getValue();

    if (gameName.contains(",")) {
      PopUp.showWarning("Invalid name", "Name cannot contain\na comma(,)");
      return;
    }

    if (selectedPlayerCount != null && !gameName.isEmpty()) {
      startGame(selectedPlayerCount, view.getGameSelectorBox().getValue(), gameName);
    } else {
      PopUp.showWarning(
          "Invalid name", "Please select the number of players and a game before continuing.");
    }
  }

  /**
   * Starts a new game based on user input.
   *
   * @param playerCount number of players for the game
   * @param gameType the selected game type ("Lader Game Classic", "Lader Game Quiz", or "Risk")
   * @param gameName the name of the game to be created
   */
  private void startGame(Integer playerCount, String gameType, String gameName) {
    try {
      Game game;
      if (gameType.equals("Snakes and Ladders Classic")) {
        game = new SnakesAndLadders(playerCount, gameName, "classic");
      } else if (gameType.equals("Snakes and Ladders Quiz")) {
        game = new SnakesAndLadders(playerCount, gameName, "quiz");
      } else if (gameType.equals("Risk")) {
        game = new Risk(playerCount, gameName);
      } else {
        PopUp.showWarning("Invalid game type", "Game of type" + gameName + " not found.");
        return;
      }
      NavigationManager.navigate(new CreatePlayerPageController(game).getView());
    } catch (UncheckedIOException e) {
      PopUp.showError("A file related error occurred", e.getMessage());
    }
  }

  /**
   * Handles loading a previously saved game from disk based on user selection. Navigates to the
   * appropriate game page view.
   */
  private void handleLoad() {
    String selectedGame = view.getSavedGamesBox().getValue();
    if (selectedGame != null) {
      String[] gameData = selectedGame.split("\\(");
      String gameName = gameData[0].trim();
      String gameType = gameData[1].replace(")", "").trim();

      try {
        Game game = FileHandler.loadGame(gameName, gameType);
        if (game instanceof SnakesAndLadders) {
          NavigationManager.navigate(new SnakesAndLaddersPageView((SnakesAndLadders) game));
        } else {
          NavigationManager.navigate(new RiskPage((Risk) game));
        }
      } catch (UncheckedIOException e) {
        PopUp.showError("A file related error occurred trying to load game", e.getMessage());
      }
    } else {
      PopUp.showWarning("Selection Required", "Please select a saved game to load.");
    }
  }
}
