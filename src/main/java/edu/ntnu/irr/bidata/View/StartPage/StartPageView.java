package edu.ntnu.irr.bidata.View.StartPage;

import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartPageView extends VBox {
  private final TextField gameNameField = new TextField();
  private final ComboBox<String> gameSelectorBox = new ComboBox<>();
  private final ComboBox<String> savedGames = new ComboBox<>();
  private final Button confirmButton = new Button("Confirm");
  private final Button loadGameButton = new Button("Load Game");
  private final Button exitButton = new Button("Leave Runeborne");
  private final ComboBox<Integer> playerCountBox = new ComboBox<>();

  public StartPageView(HashMap<String, String> savedGamesMap) {
    getStyleClass().add("start-page");
    setAlignment(Pos.TOP_CENTER);

    Label title = new Label("Welcome to the kindom of Runeborne");
    title.getStyleClass().add("fantasy-title");

    Label subtitle = new Label("Choose your next adventure!");
    subtitle.getStyleClass().add("fantasy-text");

    gameNameField.setPromptText("Adventure Name");
    VBox.setMargin(gameNameField, new Insets(0, 100, 10, 100));
    gameNameField.getStyleClass().add("fantasy-text-field");

    playerCountBox.getStyleClass().add("fantasy-combo-box");
    VBox.setMargin(playerCountBox, new Insets(5, 5, 5, 5));
    playerCountBox.getItems().addAll(2, 3, 4, 5);
    playerCountBox.setPromptText("Select Number of Heros");


    gameSelectorBox.getStyleClass().add("fantasy-combo-box");
    VBox.setMargin(gameSelectorBox, new Insets(5, 5, 5, 5));
    gameSelectorBox.getItems().addAll("Lader Game Classic","Lader Game Qizz", "Risk");
    gameSelectorBox.setPromptText("Select an Adventure");


    confirmButton.getStyleClass().add("fantasy-button");
    VBox.setMargin(confirmButton, new Insets(5, 5, 5, 5));
    confirmButton.setDisable(true);

    Label or = new Label("OR");
    or.getStyleClass().add("fantasy-text");
    VBox.setMargin(or, new Insets(0, 0, 10, 0)); // Top, Right, Bottom, Left


    savedGames.getStyleClass().add("fantasy-combo-box");
    VBox.setMargin(savedGames, new Insets(5, 5, 5, 5));
    for (String game : savedGamesMap.keySet()) {
      String gameType = savedGamesMap.get(game);
      savedGames.getItems().add(game + " (" + gameType + ")");
    }
    savedGames.setPromptText("Select a saved adventure");


    loadGameButton.getStyleClass().add("fantasy-button");
    VBox.setMargin(loadGameButton, new Insets(5, 5, 5, 5));
    loadGameButton.setDisable(true);

    exitButton.getStyleClass().add("fantasy-button");
    exitButton.getStyleClass().add("close-button");
    VBox.setMargin(exitButton, new Insets(32, 5, 5, 5));

    HBox newGameBox = new HBox(10);
    newGameBox.setAlignment(Pos.CENTER);
    newGameBox.getChildren().addAll(gameNameField, playerCountBox, gameSelectorBox, confirmButton);
    HBox.setMargin(newGameBox, new Insets(5, 5, 5, 5));

    HBox savedGameBox = new HBox(10);
    savedGameBox.setAlignment(Pos.CENTER);
    savedGameBox.getChildren().addAll(savedGames, loadGameButton);
    HBox.setMargin(savedGameBox, new Insets(5, 5, 15, 5));


    getChildren().addAll(title, subtitle, gameNameField, newGameBox, or, savedGameBox, exitButton);
  }

  public ComboBox<String> getGameSelectorBox() {
    return gameSelectorBox;
  }

  public ComboBox<String> getSavedGamesBox() {
    return savedGames;
  }

  public TextField getGameNameField() {
    return gameNameField;
  }

  public ComboBox<Integer> getPlayerCountBox() {
    return playerCountBox;
  }

  public Button getConfirmButton() {
    return confirmButton;
  }

  public Button getLoadGameButton() {
    return loadGameButton;
  }

  public Button getExitButton() {
    return exitButton;
  }
}