package edu.ntnu.irr.bidata.Wiew.ChoosAmountOfPlayers;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Wiew.AlertInterface;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AmountOfPlayersCard extends VBox {
  public AmountOfPlayersCard() {
    Label label = new Label("How many players, and what game do you want to play?");
    label.getStyleClass().addAll("styled-label", "w-p-text");

    ComboBox<Integer> amountOfPlayersComboBox = new ComboBox<>();
    amountOfPlayersComboBox.getItems().addAll(2, 3, 4, 5, 6);
    amountOfPlayersComboBox.setPromptText("Select Number of Players");

    ComboBox<String> WhatGameComboBox = new ComboBox<>();
    WhatGameComboBox.getItems().addAll("Lader Game", "Risk");
    WhatGameComboBox.setPromptText("Select a Game");

    Button amountOfPlayersButton = new Button("Confirm");
    amountOfPlayersButton.getStyleClass().addAll("styled-button", "b-p-text", "b-radius");

    // Button action to confirm the selected number of players
    amountOfPlayersButton.setOnAction(e -> {
      Integer selectedPlayers = amountOfPlayersComboBox.getValue();
      if (selectedPlayers != null && WhatGameComboBox.getValue() != null) {
        UI.AmountOfPlayersAndGameChoosen(selectedPlayers, WhatGameComboBox.getValue());
      } else {
        AlertInterface.showWarning("Selection Required", "Please select the number of players and a game before continuing.");
      }
    });

    // Add components to VBox
    this.getChildren().addAll(label, amountOfPlayersComboBox, WhatGameComboBox, amountOfPlayersButton);
    this.getStyleClass().addAll("createUser-card", "w-radius");
  }
}