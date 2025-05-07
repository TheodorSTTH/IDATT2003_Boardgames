package edu.ntnu.irr.bidata.View.WinPage;

import edu.ntnu.irr.bidata.Controler.MyWindow;
import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.View.StartPage.StartPageController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WinningPageView extends VBox {
  Label winLabel = new Label();
  Button backToMenuButton = new Button("Back to the start page");
  Button exitButton = new Button("Leave Runeborne");

  public WinningPageView(String winner, String winPageClass) {
    getStyleClass().add(winPageClass);
    setAlignment(Pos.TOP_RIGHT);
    winLabel.setText("You win " + winner + "!");
    VBox.setMargin(winLabel, new Insets(10, 20, 10, 20));
    winLabel.getStyleClass().add("fantasy-title");

    backToMenuButton.getStyleClass().addAll("fantasy-button-sidbar");
    VBox.setMargin(backToMenuButton, new Insets(25, 25, 25, 25));

    exitButton.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(exitButton, new Insets(25, 25, 25, 25));

    getChildren().addAll(winLabel, backToMenuButton, exitButton);
  }

  public Button getBackToMenuButton() {
    return backToMenuButton;
  }

  public Button getExitButton() {
    return exitButton;
  }
}
