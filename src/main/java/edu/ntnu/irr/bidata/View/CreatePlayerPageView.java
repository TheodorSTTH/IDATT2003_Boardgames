package edu.ntnu.irr.bidata.view;

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


/**
 * This class represents the view for creating a new player(hero) in the application.
 * It provides fields for entering a username(hero name), selecting a color, and choosing an age.
 */
public class CreatePlayerPageView extends VBox {

  // UI Components
  Label titleLabel = new Label("Create a New Hero"); // Title text
  TextField usernameField = new TextField(); // Field for entering hero name
  ComboBox<String> playerColorField = new ComboBox<>(); // Dropdown to choose color
  Label colorLabel = new Label("Choose a color: "); // Label for color field
  Label ageLabel = new Label("How old is your hero: "); // Label for age spinner
  Spinner<Integer> ageSpinner =
      new Spinner<>(
          new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1)); // Spinner for age
  Button createPlayerButton = new Button("New Hero"); // Button to confirm creation

  /**
   * Returns the text field where the user inputs the hero's name.
   *
   * @return the TextField for the username.
   */
  public TextField getUsernameField() {
    return usernameField;
  }

  /**
   * Returns the combo box where the user selects the hero's color.
   *
   * @return the ComboBox containing color options.
   */
  public ComboBox<String> getPlayerColorField() {
    return playerColorField;
  }

  /**
   * Returns the spinner for selecting the hero's age.
   *
   * @return the Spinner for age selection.
   */
  public Spinner<Integer> getAgeSpinner() {
    return ageSpinner;
  }

  /**
   * Returns the button used to confirm and create the hero.
   *
   * @return the create player Button.
   */
  public Button getCreatePlayerButton() {
    return createPlayerButton;
  }

  /**
   * Constructs the CreatePlayerPageView with the given list of available colors.
   * Sets up all elements and adds them to the layout.
   *
   * @param availableColors a list of color options to display in the color picker.
   */
  public CreatePlayerPageView(List<String> availableColors) {
    getStyleClass().addAll("start-page", "background");
    setAlignment(Pos.TOP_CENTER); // Align the VBox content at the top center

    titleLabel.getStyleClass().add("fantasy-title");
    VBox.setMargin(titleLabel, new Insets(20, 5, 10, 5)); // Top margin for title

    usernameField.getStyleClass().add("fantasy-text-field");
    VBox.setMargin(usernameField, new Insets(5, 100, 10, 100)); // Side padding for text field
    usernameField.setPromptText("Hero Name"); // Placeholder text

    VBox.setMargin(colorLabel, new Insets(5, 5, 5, 5));
    colorLabel.getStyleClass().add("fantasy-text");

    VBox.setMargin(playerColorField, new Insets(5, 5, 5, 5));
    playerColorField.getStyleClass().add("fantasy-combo-box");
    playerColorField.getItems().addAll(availableColors); // Load available colors into dropdown
    playerColorField.setPromptText("Choose a color"); // Fixed spelling here

    VBox.setMargin(ageLabel, new Insets(5, 5, 5, 5));
    ageLabel.getStyleClass().add("fantasy-text");

    ageSpinner.getStyleClass().add("fantasy-spinner");
    VBox.setMargin(ageSpinner, new Insets(5, 5, 5, 5));
    ageSpinner.setEditable(true); // Allow user to type age

    createPlayerButton.getStyleClass().add("fantasy-button");
    createPlayerButton.getStyleClass().add("close-button");
    VBox.setMargin(createPlayerButton, new Insets(10, 5, 5, 5));
    createPlayerButton.setDisable(true); // Disabled by default until form is valid

    // Layout for choosing color: label + dropdown
    HBox chooseColorBox = new HBox(10);
    chooseColorBox.setAlignment(Pos.CENTER);
    chooseColorBox.getChildren().addAll(colorLabel, playerColorField);
    HBox.setMargin(chooseColorBox, new Insets(5, 5, 5, 5));

    // Layout for choosing age: label + spinner
    HBox chooseAgeBox = new HBox(10);
    chooseAgeBox.setAlignment(Pos.CENTER);
    chooseAgeBox.getChildren().addAll(ageLabel, ageSpinner);
    HBox.setMargin(chooseAgeBox, new Insets(5, 5, 5, 5));

    // Add all UI elements to the vertical layout
    getChildren()
        .addAll(titleLabel, usernameField, chooseColorBox, chooseAgeBox, createPlayerButton);
  }
}
