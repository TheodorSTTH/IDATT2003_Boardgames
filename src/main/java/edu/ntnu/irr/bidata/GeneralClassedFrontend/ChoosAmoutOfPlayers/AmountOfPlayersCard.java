package edu.ntnu.irr.bidata.GeneralClassedFrontend.ChoosAmoutOfPlayers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AmountOfPlayersCard extends VBox{
  public AmountOfPlayersCard() {
    Label label = new Label("How many players");
    label.getStyleClass().addAll("styled-label", "w-p-text");

    Button AmountOfPlayers = new Button("confirm");
    AmountOfPlayers.getStyleClass().addAll("styled-button", "b-p-text", "b-radius");

    TextField usernameField = new TextField();
    usernameField.setPromptText("Username");
    usernameField.getStyleClass().addAll("styled-textfield", "w-s-text", "w-radius");


    AmountOfPlayers.setOnAction(e -> {
      //UiController.AmountOfNewPlayers(usernameField.getText());
    });

    this.getChildren().addAll(label, usernameField, AmountOfPlayers);
    this.getStyleClass().addAll("createUser-card", "w-radius");
    }
  
}
