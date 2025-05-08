package edu.ntnu.irr.bidata.view;

import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** View for the start page of the game. Lets the user start a new game or load a saved one. */
public class StartPageView extends VBox {

  // UI components
  private final TextField gameNameField = new TextField();
  private final ComboBox<String> gameSelectorBox = new ComboBox<>();
  private final ComboBox<String> savedGames = new ComboBox<>();
  private final Button confirmButton = new Button("Confirm");
  private final Button loadGameButton = new Button("Load Game");
  private final Button exitButton = new Button("Leave Runeborne");
  private final ComboBox<Integer> playerCountBox = new ComboBox<>();

  /**
   * Constructs the start page view.
   *
   * @param savedGamesMap A map containing saved game names and their corresponding types.
   */
  public StartPageView(HashMap<String, String> savedGamesMap) {
    getStyleClass().addAll("start-page", "background");
    setAlignment(Pos.TOP_CENTER);

    // Title
    Label title = new Label("Welcome to the kingdom of Runeborne");
    title.getStyleClass().addAll("fantasy", "text-gold", "text-55");

    // Subtitle
    Label subtitle = new Label("Choose your next adventure!");
    subtitle.getStyleClass().addAll("fantasy", "text-gold", "text-40");

    // Game name input
    gameNameField.setPromptText("Adventure Name");
    VBox.setMargin(gameNameField, new Insets(0, 100, 10, 100));
    gameNameField.getStyleClass().addAll("fantasy-text", "fantasy");

    // Player count selector
    playerCountBox.getStyleClass().addAll("combo-box", "text-18", "fantasy");
    VBox.setMargin(playerCountBox, new Insets(5, 5, 5, 5));
    playerCountBox.getItems().addAll(2, 3, 4, 5);
    playerCountBox.setPromptText("Select Number of Heroes");

    // Game type selector
    gameSelectorBox.getStyleClass().addAll("combo-box", "text-18", "fantasy");
    VBox.setMargin(gameSelectorBox, new Insets(5, 5, 5, 5));
    gameSelectorBox
        .getItems()
        .addAll("Snakes and Ladders Classic", "Snakes and Ladders Quiz", "Risk");
    gameSelectorBox.setPromptText("Select an Adventure");

    // Confirm new game button
    confirmButton.getStyleClass().addAll("button", "text-18", "fantasy");
    VBox.setMargin(confirmButton, new Insets(5, 5, 5, 5));
    confirmButton.setDisable(true); // Initially disabled

    // "OR" separator label
    Label or = new Label("OR");
    or.getStyleClass().addAll("fantasy-text", "fantasy");
    VBox.setMargin(or, new Insets(0, 0, 10, 0));

    // Saved games selector
    savedGames.getStyleClass().addAll("combo-box", "text-18", "fantasy");
    VBox.setMargin(savedGames, new Insets(5, 5, 5, 5));
    for (String game : savedGamesMap.keySet()) {
      String gameType = savedGamesMap.get(game);
      savedGames.getItems().add(game + " (" + gameType + ")");
    }
    savedGames.setPromptText("Select a saved adventure");

    // Load saved game button
    loadGameButton.getStyleClass().addAll("button", "text-18", "fantasy");
    VBox.setMargin(loadGameButton, new Insets(5, 5, 5, 5));
    loadGameButton.setDisable(true); // Initially disabled

    // Exit button
    exitButton.getStyleClass().addAll("button", "large-size", "fantasy");
    VBox.setMargin(exitButton, new Insets(32, 5, 5, 5));

    // Layout for new game options
    HBox newGameBox = new HBox(10);
    newGameBox.setAlignment(Pos.CENTER);
    newGameBox.getChildren().addAll(gameNameField, playerCountBox, gameSelectorBox, confirmButton);
    HBox.setMargin(newGameBox, new Insets(5, 5, 5, 5));

    // Layout for saved game options
    HBox savedGameBox = new HBox(10);
    savedGameBox.setAlignment(Pos.CENTER);
    savedGameBox.getChildren().addAll(savedGames, loadGameButton);
    HBox.setMargin(savedGameBox, new Insets(5, 5, 15, 5));

    // Add all components to the main VBox
    getChildren().addAll(title, subtitle, gameNameField, newGameBox, or, savedGameBox, exitButton);
  }

  // Getters for controller access

  /**
   * Retrieves the ComboBox used for selecting a game type.
   *
   * @return the ComboBox for selecting a game type
   */
  public ComboBox<String> getGameSelectorBox() {
    return gameSelectorBox;
  }

  /**
   * Retrieves the ComboBox used for selecting a saved game.
   *
   * @return the ComboBox for selecting a saved game
   */
  public ComboBox<String> getSavedGamesBox() {
    return savedGames;
  }

  /**
   * Retrieves the TextField used for entering a new game name.
   *
   * @return the TextField for entering a new game name
   */
  public TextField getGameNameField() {
    return gameNameField;
  }

  /**
   * Retrieves the ComboBox used for selecting the number of players.
   *
   * @return the ComboBox for selecting number of players
   */
  public ComboBox<Integer> getPlayerCountBox() {
    return playerCountBox;
  }

  /**
   * Retrieves the button used to confirm the creation of a new game.
   *
   * @return the button to confirm new game creation
   */
  public Button getConfirmButton() {
    return confirmButton;
  }

  /**
   * Retrieves the button used to load a saved game.
   *
   * @return the button to load a saved game
   */
  public Button getLoadGameButton() {
    return loadGameButton;
  }

  /**
   * Retrieves the button used to exit the game.
   *
   * @return the button to exit the game
   */
  public Button getExitButton() {
    return exitButton;
  }
}
