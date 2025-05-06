package edu.ntnu.irr.bidata.View.StartPage;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Model.FileHandler;
import edu.ntnu.irr.bidata.View.PopUp;
import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartPage extends VBox {
  public StartPage() {
    getStyleClass().add("start-page");
    setAlignment(Pos.TOP_CENTER);

    Label tittel = new Label("Choose your next adventure!");
    tittel.getStyleClass().add("fantasy-title");
    VBox.setMargin(tittel, new Insets(20, 3, 10, 5));


    TextField gameNameField = new TextField();
    gameNameField.setPromptText("Game Name");
    VBox.setMargin(gameNameField, new Insets(5, 100, 10, 100));
    gameNameField.getStyleClass().add("fantasy-text-field");


    ComboBox<Integer> amountOfPlayersComboBox = new ComboBox<>();
    amountOfPlayersComboBox.getStyleClass().add("fantasy-combo-box");
    VBox.setMargin(amountOfPlayersComboBox, new Insets(5, 5, 5, 5));
    amountOfPlayersComboBox.getItems().addAll(2, 3, 4, 5);
    amountOfPlayersComboBox.setPromptText("Select Number of Players");


    ComboBox<String> WhatGameComboBox = new ComboBox<>();
    WhatGameComboBox.getStyleClass().add("fantasy-combo-box");
    VBox.setMargin(WhatGameComboBox, new Insets(5, 5, 5, 5));
    WhatGameComboBox.getItems().addAll("Lader Game Classic","Lader Game Qizz", "Risk");
    WhatGameComboBox.setPromptText("Select a Game to play");


    Button ConfirmButton = new Button("Confirm");
    ConfirmButton.getStyleClass().add("fantasy-button");
    VBox.setMargin(ConfirmButton, new Insets(5, 5, 5, 5));
    ConfirmButton.setDisable(true);

    ConfirmButton.setOnAction(e -> {
    Integer selectedPlayers = amountOfPlayersComboBox.getValue();
      if (selectedPlayers != null && WhatGameComboBox.getValue() != null && !gameNameField.getText().isEmpty()) {
        UI.StartPageCreateNewGameButon(selectedPlayers, WhatGameComboBox.getValue(), gameNameField.getText());
      } else {
        PopUp.showWarning("Selection Required","Please select the number of players and a game before continuing.");
        }
    });
      

    Label or = new Label("OR");
    or.getStyleClass().add("fantasy-text");
    VBox.setMargin(or, new Insets(0, 0, 10, 0)); // Top, Right, Bottom, Left


    ComboBox<String> savedGames = new ComboBox<>();
    savedGames.getStyleClass().add("fantasy-combo-box");
    VBox.setMargin(savedGames, new Insets(5, 5, 5, 5));
    HashMap<String, String> savedGamesMap = FileHandler.getSavedGames();
    for (String game : savedGamesMap.keySet()) {
      String gameType = savedGamesMap.get(game);
      savedGames.getItems().add(game + " (" + gameType + ")");
    }
    savedGames.setPromptText("Select a Game, form saved games");


    Button LoadGameButton = new Button("Load Game");
    LoadGameButton.getStyleClass().add("fantasy-button");
    VBox.setMargin(LoadGameButton, new Insets(5, 5, 5, 5));
    LoadGameButton.setDisable(true);

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



    HBox newGameBox = new HBox(10);
    newGameBox.setAlignment(Pos.CENTER);
    newGameBox.getChildren().addAll(gameNameField, amountOfPlayersComboBox, WhatGameComboBox, ConfirmButton);
    HBox.setMargin(newGameBox, new Insets(5, 5, 5, 5));
    
    HBox savedGameBox = new HBox(10);
    savedGameBox.setAlignment(Pos.CENTER);
    savedGameBox.getChildren().addAll(savedGames, LoadGameButton);
    HBox.setMargin(savedGameBox, new Insets(5, 5, 15, 5));
    

    getChildren().addAll(tittel, gameNameField, newGameBox, or, savedGameBox);


    savedGames.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      if (newFrom != null) {
        LoadGameButton.setDisable(false);
      } else {
        LoadGameButton.setDisable(true);
      }
    });

    amountOfPlayersComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      if (newFrom != null && WhatGameComboBox.getValue() != null && !gameNameField.getText().isEmpty()) {
        ConfirmButton.setDisable(false);
      } else {
        ConfirmButton.setDisable(true);
      }
    });

    WhatGameComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      if (newFrom != null && amountOfPlayersComboBox.getValue() != null && !gameNameField.getText().isEmpty()) {
        ConfirmButton.setDisable(false);
      } else {
        ConfirmButton.setDisable(true);
      }
    });

    gameNameField.textProperty().addListener((obs, oldFrom, newFrom) -> {
      if (!newFrom.isEmpty() && amountOfPlayersComboBox.getValue() != null && WhatGameComboBox.getValue() != null) {
        ConfirmButton.setDisable(false);
      } else {
        ConfirmButton.setDisable(true);
      }
    });
  }
}
