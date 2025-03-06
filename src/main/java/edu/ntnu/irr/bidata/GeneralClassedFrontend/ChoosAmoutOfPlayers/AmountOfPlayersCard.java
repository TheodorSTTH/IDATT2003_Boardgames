package edu.ntnu.irr.bidata.GeneralClassedFrontend.ChoosAmoutOfPlayers;

import edu.ntnu.irr.bidata.GeneralClassedFrontend.UI;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AmountOfPlayersCard extends VBox {
  public AmountOfPlayersCard() {
    Label label = new Label("How many players");
    label.getStyleClass().addAll("styled-label", "w-p-text");
    
    ComboBox<Integer> amountOfPlayersComboBox = new ComboBox<>();
    amountOfPlayersComboBox.getItems().addAll(2, 3, 4, 5, 6);
    amountOfPlayersComboBox.setPromptText("Select Number of Players");

    Button amountOfPlayersButton = new Button("Confirm");
    amountOfPlayersButton.getStyleClass().addAll("styled-button", "b-p-text", "b-radius");

    // Button action to confirm the selected number of players
    amountOfPlayersButton.setOnAction(e -> {
      Integer selectedPlayers = amountOfPlayersComboBox.getValue();
      if (selectedPlayers != null) {
        UI.AmountOfPlayersChoosen(selectedPlayers);
      } else {
        System.out.println("Please select a number of players.");
          }
      });

        // Add components to VBox
        this.getChildren().addAll(label, amountOfPlayersComboBox, amountOfPlayersButton);
        this.getStyleClass().addAll("createUser-card", "w-radius");
    }
}