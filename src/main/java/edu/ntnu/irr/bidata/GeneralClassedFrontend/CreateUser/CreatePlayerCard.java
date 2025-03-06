package edu.ntnu.irr.bidata.GeneralClassedFrontend.CreateUser;


import javafx.scene.control.Button;
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


        createPlayerButton.setOnAction(e -> {
            //UiController.tryCreateUser(usernameField.getText(), passwordField.getText(), nameField.getText());
        });

        this.getChildren().addAll(label, usernameField, createPlayerButton);
        this.getStyleClass().addAll("createUser-card", "w-radius");
    }
}
