package edu.ntnu.irr.bidata.view.createPlayer;

import edu.ntnu.irr.bidata.controler.UI;
import edu.ntnu.irr.bidata.view.PopUp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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

        getStyleClass().add("start-page");
        setAlignment(Pos.TOP_CENTER);

        Label tittelLabel = new Label("Create a New Hero");
        tittelLabel.getStyleClass().add("fantasy-title");
        VBox.setMargin(tittelLabel, new Insets(20, 5, 10, 5));

        TextField usernameField = new TextField();
        usernameField.getStyleClass().add("fantasy-text-field");
        VBox.setMargin(usernameField, new Insets(5, 100, 10, 100));
        usernameField.setPromptText("Hero Name");
        

        Label colorLabel = new Label("Choose a color: ");
        VBox.setMargin(colorLabel, new Insets(5, 5, 5, 5));
        colorLabel.getStyleClass().add("fantasy-text");

        ComboBox<String> playerColureField = new ComboBox<>();
        VBox.setMargin(playerColureField, new Insets(5, 5, 5, 5));
        playerColureField.getStyleClass().add("fantasy-combo-box");
        playerColureField.getItems().addAll(UI.getGame().getAvailableColors());
        playerColureField.setPromptText("Chose a color");

        Label ageLabel = new Label("How old are your hero: ");
        VBox.setMargin(ageLabel, new Insets(5, 5, 5, 5));
        ageLabel.getStyleClass().add("fantasy-text");

        Spinner<Integer> ageSpinner = new Spinner<>(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1));
        ageSpinner.getStyleClass().add("fantasy-spinner");
        VBox.setMargin(ageSpinner, new Insets(5, 5, 5, 5));
        ageSpinner.setEditable(true);

        Button createPlayerButton = new Button("New Hero");
        createPlayerButton.getStyleClass().add("fantasy-button");
        createPlayerButton.getStyleClass().add("close-button");
        VBox.setMargin(createPlayerButton, new Insets(10, 5, 5, 5));
        createPlayerButton.setDisable(true);

        createPlayerButton.setOnAction(e -> {
            if (usernameField.getText().isEmpty() || playerColureField.getValue() == null
                    || ageSpinner.getValue() < 1) {
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

        HBox chooseAColureBox = new HBox(10);
        chooseAColureBox.setAlignment(Pos.CENTER);
        chooseAColureBox.getChildren().addAll(colorLabel, playerColureField);
        HBox.setMargin(chooseAColureBox, new Insets(5, 5, 5, 5));

        HBox chooseAgeBox = new HBox(10);
        chooseAgeBox.setAlignment(Pos.CENTER);
        chooseAgeBox.getChildren().addAll(ageLabel, ageSpinner);
        HBox.setMargin(chooseAgeBox, new Insets(5, 5, 5, 5));

        getChildren().addAll(tittelLabel, usernameField, chooseAColureBox, chooseAgeBox, createPlayerButton);

        playerColureField.valueProperty().addListener((obs, oldFrom, newFrom) -> {
            if (newFrom != null && !usernameField.getText().isEmpty()) {
                createPlayerButton.setDisable(false);
            } else {
                createPlayerButton.setDisable(true);
            }
        });

        usernameField.textProperty().addListener((obs, oldFrom, newFrom) -> {
            if (newFrom != null && !newFrom.isEmpty() && playerColureField.getValue() != null) {
                createPlayerButton.setDisable(false);
            } else {
                createPlayerButton.setDisable(true);
            }
        });
    }
}