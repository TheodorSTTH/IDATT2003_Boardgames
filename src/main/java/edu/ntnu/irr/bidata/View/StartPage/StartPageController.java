package edu.ntnu.irr.bidata.View.StartPage;

import edu.ntnu.irr.bidata.Controler.MyWindow;
import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.Model.FileHandler;
import edu.ntnu.irr.bidata.Model.Game;
import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.View.CreatePlayer.CreatePlayerPageController;
import edu.ntnu.irr.bidata.View.PopUp;
import java.util.HashMap;
import javafx.beans.value.ChangeListener;

public class StartPageController {
  private final StartPageView view;
  private HashMap<String, String> savedGamesMap;

  public StartPageController() {
    this.savedGamesMap = FileHandler.getSavedGames();
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
    Game game;
    if (gameType.equals("Lader Game Classic")) {
      game = new LaderGame(playerCount, gameName, "classic");
    } else if (gameType.equals("Lader Game Qizz")) {
      game = new LaderGame(playerCount, gameName, "qizz");
    } else {
      game = new Risk(playerCount, gameName);
    }

    NavigationManager.navigate(new CreatePlayerPageController(game).getView());
  }

  private void handleLoad() {
    String selectedGame = view.getSavedGamesBox().getValue();
    if (selectedGame != null) {
      String[] gameData = selectedGame.split("\\(");
      String gameName = gameData[0].trim();
      String gameType = gameData[1].replace(")", "").trim();

      Game game = FileHandler.loadGame(gameName, gameType);
      NavigationManager.navigate(new CreatePlayerPageController(game).getView());
    } else {
      PopUp.showWarning("Selection Required", "Please select a saved game to load.");
    }
  }
}