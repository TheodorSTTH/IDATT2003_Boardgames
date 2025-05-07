package edu.ntnu.irr.bidata.view.createplayer;

import java.util.List;
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

public class CreatePlayerPageView extends VBox {
  Label titleLabel = new Label("Create a New Hero");
  TextField usernameField = new TextField();
  ComboBox<String> playerColorField = new ComboBox<>();
  Label colorLabel = new Label("Choose a color: ");
  Label ageLabel = new Label("How old are your hero: ");
  Spinner<Integer> ageSpinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1));
  Button createPlayerButton = new Button("New Hero");

  public TextField getUsernameField() {
    return usernameField;
  }

  public ComboBox<String> getPlayerColorField() {
    return playerColorField;
  }

  public Spinner<Integer> getAgeSpinner() {
    return ageSpinner;
  }

  public Button getCreatePlayerButton() {
    return createPlayerButton;
  }

  public CreatePlayerPageView(List<String> availableColors) {
    getStyleClass().add("start-page");
    setAlignment(Pos.TOP_CENTER);

    titleLabel.getStyleClass().add("fantasy-title");
    VBox.setMargin(titleLabel, new Insets(20, 5, 10, 5));

    usernameField.getStyleClass().add("fantasy-text-field");
    VBox.setMargin(usernameField, new Insets(5, 100, 10, 100));
    usernameField.setPromptText("Hero Name");


    VBox.setMargin(colorLabel, new Insets(5, 5, 5, 5));
    colorLabel.getStyleClass().add("fantasy-text");

    VBox.setMargin(playerColorField, new Insets(5, 5, 5, 5));
    playerColorField.getStyleClass().add("fantasy-combo-box");
    playerColorField.getItems().addAll(availableColors);
    playerColorField.setPromptText("Chose a color");

    VBox.setMargin(ageLabel, new Insets(5, 5, 5, 5));
    ageLabel.getStyleClass().add("fantasy-text");

    ageSpinner.getStyleClass().add("fantasy-spinner");
    VBox.setMargin(ageSpinner, new Insets(5, 5, 5, 5));
    ageSpinner.setEditable(true);

    createPlayerButton.getStyleClass().add("fantasy-button");
    createPlayerButton.getStyleClass().add("close-button");
    VBox.setMargin(createPlayerButton, new Insets(10, 5, 5, 5));
    createPlayerButton.setDisable(true);

    HBox chooseColorBox = new HBox(10);
    chooseColorBox.setAlignment(Pos.CENTER);
    chooseColorBox.getChildren().addAll(colorLabel, playerColorField);
    HBox.setMargin(chooseColorBox, new Insets(5, 5, 5, 5));

    HBox chooseAgeBox = new HBox(10);
    chooseAgeBox.setAlignment(Pos.CENTER);
    chooseAgeBox.getChildren().addAll(ageLabel, ageSpinner);
    HBox.setMargin(chooseAgeBox, new Insets(5, 5, 5, 5));

    getChildren().addAll(titleLabel, usernameField, chooseColorBox, chooseAgeBox, createPlayerButton);
  }
}
