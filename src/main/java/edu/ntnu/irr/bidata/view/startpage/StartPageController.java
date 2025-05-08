package edu.ntnu.irr.bidata.view.startpage;

import edu.ntnu.irr.bidata.controller.MyWindow;
import edu.ntnu.irr.bidata.controller.NavigationManager;
import edu.ntnu.irr.bidata.model.FileHandler;
import edu.ntnu.irr.bidata.model.Game;
import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import edu.ntnu.irr.bidata.model.risk.Risk;
import edu.ntnu.irr.bidata.view.createplayer.CreatePlayerPageController;
import edu.ntnu.irr.bidata.view.snakesandladders.SnakesAndLaddersPageView;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.risk.RiskPage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;
import javafx.beans.value.ChangeListener;

public class StartPageController {
  private final StartPageView view;
  private HashMap<String, String> savedGamesMap;

  public StartPageController() {
    try {
      this.savedGamesMap = FileHandler.getSavedGames();
    } catch (UncheckedIOException e) {
      PopUp.showError("Something went wrong trying to get saved games", e.getMessage());
    }
    this.view = new StartPageView(savedGamesMap);
    initialize();
  }

  public StartPageView getView() {
    return view;
  }

  private void initialize() {
    view.getConfirmButton().setOnAction(event -> handleConfirm());
    view.getExitButton().setOnAction(event -> MyWindow.closeApplication());
    view.getLoadGameButton().setOnAction(event -> handleLoad());

    ChangeListener<Object> enabler = (obs, oldV, newV) -> view.getConfirmButton().setDisable(!allInputsValid());
    view.getGameNameField().textProperty().addListener(enabler);
    view.getPlayerCountBox().valueProperty().addListener(enabler);
    view.getGameSelectorBox().valueProperty().addListener(enabler);
    view.getSavedGamesBox().valueProperty().addListener((obs, oldV, newV) -> view.getLoadGameButton().setDisable(newV.isEmpty()));
  }

  private boolean allInputsValid() {
    return !view.getGameNameField().getText().isBlank()
        && view.getPlayerCountBox().getValue() != null
        && view.getGameSelectorBox().getValue() != null;
  }

  private void handleConfirm() {
    String gameName = view.getGameNameField().getText();
    Integer selectedPlayerCount = view.getPlayerCountBox().getValue();
    if (gameName.contains(",")) {
      PopUp.showWarning("Invalid name", "Name cannot contain a comma (,)");
      return;
    }
    if (selectedPlayerCount != null && !gameName.isEmpty()) {
      startGame(selectedPlayerCount, view.getGameSelectorBox().getValue(), gameName);
    } else {
      PopUp.showWarning("Invalid name", "Please select the number of players and a game before continuing.");
    }
  }

  private void startGame(Integer playerCount, String gameType, String gameName) {
    try {
      Game game;
      if (gameType.equals("Lader Game Classic")) {
        game = new SnakesAndLadders(playerCount, gameName, "classic");
      } else if (gameType.equals("Lader Game Quiz")) {
        game = new SnakesAndLadders(playerCount, gameName, "quiz");
      } else {
        game = new Risk(playerCount, gameName);
      }

      NavigationManager.navigate(new CreatePlayerPageController(game).getView());
    } catch (UncheckedIOException e) {
      PopUp.showError("A file related error occurred", e.getMessage());
    }
  }

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