package edu.ntnu.irr.bidata.View.CreatePlayer;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.View.PopUp;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreatePlayerCard extends VBox {
    public CreatePlayerCard() {
        Label label = new Label("Create a new player");
        label.getStyleClass().addAll("styled-label", "w-p-text");

        Button createPlayerButton = new Button("New player");
        createPlayerButton.getStyleClass().addAll("styled-button", "b-p-text", "b-radius");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.getStyleClass().addAll("styled-textfield", "w-s-text", "w-radius");

        ComboBox<String> playerColureField = new ComboBox<>();
        playerColureField.getItems().addAll(UI.getGame().getAvalibleColores());
        playerColureField.setPromptText("Chose a color");

        createPlayerButton.setOnAction(e -> {
            if (usernameField.getText().isEmpty() || playerColureField.getValue() == null) {
                PopUp.showWarning("Selection Required",
                        "Please select a username and a color before continuing.");
                return;
            }
            UI.newPlayer(usernameField.getText(), playerColureField.getValue());
            playerColureField.getItems().remove(playerColureField.getValue());
            usernameField.clear();
            playerColureField.setValue(null);
        });

        this.getChildren().addAll(label, usernameField, playerColureField, createPlayerButton);
        this.getStyleClass().addAll("createUser-card", "w-radius");
    }
}
