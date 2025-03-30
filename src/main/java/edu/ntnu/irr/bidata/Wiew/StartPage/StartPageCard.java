package edu.ntnu.irr.bidata.Wiew.StartPage;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Wiew.AlertMessage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class StartPageCard extends VBox {
  public StartPageCard() {
    Label label = new Label("How many players, and what game do you want to play?");
    label.getStyleClass().addAll("styled-label", "w-p-text");

    ComboBox<Integer> amountOfPlayersComboBox = new ComboBox<>();
    amountOfPlayersComboBox.getItems().addAll(2, 3, 4, 5, 6);
    amountOfPlayersComboBox.setPromptText("Select Number of Players");

    ComboBox<String> WhatGameComboBox = new ComboBox<>();
    WhatGameComboBox.getItems().addAll("Lader Game", "Risk");
    WhatGameComboBox.setPromptText("Select a Game");

    TextField gameNameField = new TextField();
    gameNameField.setPromptText("Game Name");
    gameNameField.getStyleClass().addAll("styled-textfield", "w-s-text", "w-radius");

    Button amountOfPlayersButton = new Button("Confirm");
    amountOfPlayersButton.getStyleClass().addAll("styled-button", "b-p-text", "b-radius");

    // Button action to confirm the selected number of players
    amountOfPlayersButton.setOnAction(e -> {
      Integer selectedPlayers = amountOfPlayersComboBox.getValue();
      if (selectedPlayers != null && WhatGameComboBox.getValue() != null && !gameNameField.getText().isEmpty()) {
        UI.StartPageCreateNewGameButon(selectedPlayers, WhatGameComboBox.getValue(), gameNameField.getText());
      } else {
        AlertMessage.showWarning("Selection Required", "Please select the number of players and a game before continuing.");
      }
    });

    // Add components to VBox
    this.getChildren().addAll(label, amountOfPlayersComboBox, WhatGameComboBox, gameNameField, amountOfPlayersButton);
    this.getStyleClass().addAll("createUser-card", "w-radius");
  }
}