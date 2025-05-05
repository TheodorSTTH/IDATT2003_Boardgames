package edu.ntnu.irr.bidata.View.StartPage;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Model.FileHandler;
import edu.ntnu.irr.bidata.View.PopUp;
import java.util.HashMap;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class StartPage extends VBox {
  public StartPage() {
    if (getClass().getResource("/style.css") != null) {
      getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    } else {
      System.err.println("Warning: style.css not found!");
    }

    Label label = new Label("How many players, and what game do you want to play?");
    label.getStyleClass().addAll("styled-label", "w-p-text");

    ComboBox<Integer> amountOfPlayersComboBox = new ComboBox<>();
    amountOfPlayersComboBox.getItems().addAll(2, 3, 4, 5);
    amountOfPlayersComboBox.setPromptText("Select Number of Players");

    ComboBox<String> WhatGameComboBox = new ComboBox<>();
    WhatGameComboBox.getItems().addAll("Lader Game Classic","Lader Game Qizz", "Risk");
    WhatGameComboBox.setPromptText("Select a Game");

    TextField gameNameField = new TextField();
    gameNameField.setPromptText("Game Name");
    gameNameField.getStyleClass().addAll("styled-textfield", "w-s-text", "w-radius");

    Button ConfirmButton = new Button("Confirm");
    ConfirmButton.getStyleClass().addAll("styled-button", "b-p-text", "b-radius");

    ComboBox<String> savedGames = new ComboBox<>();
    HashMap<String, String> savedGamesMap = FileHandler.getSavedGames();
    for (String game : savedGamesMap.keySet()) {
      String gameType = savedGamesMap.get(game);
      savedGames.getItems().add(game + " (" + gameType + ")");
    }
    WhatGameComboBox.setPromptText("Select a Game, form saved games");

    Button LoadGameButton = new Button("Load Game");
    ConfirmButton.getStyleClass().addAll("styled-button", "b-p-text", "b-radius");

    // Button action to confirm the selected number of players
    ConfirmButton.setOnAction(e -> {
      Integer selectedPlayers = amountOfPlayersComboBox.getValue();
      if (selectedPlayers != null && WhatGameComboBox.getValue() != null && !gameNameField.getText().isEmpty()) {
        UI.StartPageCreateNewGameButon(selectedPlayers, WhatGameComboBox.getValue(), gameNameField.getText());
      } else {
        PopUp.showWarning("Selection Required",
            "Please select the number of players and a game before continuing.");
      }
    });

    // Button action to load a saved game
    LoadGameButton.setOnAction(e -> {
      String selectedGame = savedGames.getValue();
      if (selectedGame != null) {
        String[] gameData = selectedGame.split("\\(");
        String gameName = gameData[0].trim();
        String gameType = gameData[1].replace(")", "").trim();
        UI.loadGame(gameName, gameType);
      } else {
        PopUp.showWarning("Selection Required", "Please select a saved game to load.");
      }
    });

    getChildren().addAll(label, amountOfPlayersComboBox, WhatGameComboBox, gameNameField, ConfirmButton, savedGames, LoadGameButton);
    getStyleClass().addAll("createUser-card", "w-radius", "createPlayer-layout");
  }
}
