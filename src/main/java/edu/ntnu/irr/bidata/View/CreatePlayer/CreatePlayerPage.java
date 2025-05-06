package edu.ntnu.irr.bidata.View.CreatePlayer;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.View.PopUp;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;


public class CreatePlayerPage extends VBox {
  public CreatePlayerPage() {
    if (getClass().getResource("/style.css") != null) {
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    } else {
        System.err.println("Warning: style.css not found!");
    }

    Spinner<Integer> ageSpinner = new Spinner<>(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1));
    ageSpinner.setEditable(true);

    Label label = new Label("Create a new player");
    label.getStyleClass().addAll("styled-label", "w-p-text");

    Button createPlayerButton = new Button("New player");
    createPlayerButton.getStyleClass().addAll("styled-button", "b-p-text", "b-radius");

    TextField usernameField = new TextField();
    usernameField.setPromptText("Username");
    usernameField.getStyleClass().addAll("styled-textfield", "w-s-text", "w-radius");

    ComboBox<String> playerColureField = new ComboBox<>();
    playerColureField.getItems().addAll(UI.getGame().getAvailableColors());
    playerColureField.setPromptText("Chose a color");

    createPlayerButton.setOnAction(e -> {
        if (usernameField.getText().isEmpty() || playerColureField.getValue() == null || ageSpinner.getValue() <= 1) {
            PopUp.showWarning("Selection Required",
                "Please select a username, color and age before continuing.");
            return;
        }

        if (UI.newPlayer(usernameField.getText(), playerColureField.getValue(), ageSpinner.getValue())) {
            playerColureField.getItems().remove(playerColureField.getValue());
            usernameField.clear();
            playerColureField.setValue(null);
        }
    });

    getChildren().addAll(label, usernameField, playerColureField, ageSpinner, createPlayerButton);
    getStyleClass().addAll("createUser-card", "w-radius");
  }
}