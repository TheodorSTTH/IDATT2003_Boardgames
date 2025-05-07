package edu.ntnu.irr.bidata.View.StartPage;

import edu.ntnu.irr.bidata.Controler.MyWindow;
import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Model.FileHandler;
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
    Integer selectedPlayers = view.getPlayerCountBox().getValue();
    if (gameName.contains(",")) {
      PopUp.showWarning("Invalid name", "Name cannot contain a comma (,)");
      return;
    }
    if (selectedPlayers != null && !gameName.isEmpty()) {
      UI.StartPageCreateNewGameButon(selectedPlayers, gameName, gameName);
    } else {
      PopUp.showWarning("Invalid name", "Please select the number of players and a game before continuing.");
    }
  }

  private void handleLoad() {
    String selectedGame = view.getSavedGamesBox().getValue();
    if (selectedGame != null) {
      String[] gameData = selectedGame.split("\\(");
      String gameName = gameData[0].trim();
      String gameType = gameData[1].replace(")", "").trim();
      UI.loadGame(gameName, gameType);
    } else {
      PopUp.showWarning("Selection Required", "Please select a saved game to load.");
    }
  }
}