package edu.ntnu.irr.bidata.view.winningpage;

import edu.ntnu.irr.bidata.controler.MyWindow;
import edu.ntnu.irr.bidata.controler.NavigationManager;
import edu.ntnu.irr.bidata.view.startpage.StartPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * WinningPage represents the final page displayed when a player wins the game. It shows the
 * winner's name, and provides buttons to navigate back to the start page or exit the application.
 */
public class WinningPage extends VBox {

  /**
   * Constructs a WinningPage to display the winner and navigation options.
   *
   * @param winner The name of the player who won the game.
   * @param winPageKey The style key used to style the winning page.
   */
  public WinningPage(String winner, String winPageKey) {
    // Apply the custom style class for this winning page.
    getStyleClass().add(winPageKey);

    // Set alignment for the elements in the VBox to be at the top-right corner.
    setAlignment(Pos.TOP_RIGHT);

    // Create a label to display the winner's name.
    Label winLabel = new Label("You win " + winner + "!");
    VBox.setMargin(winLabel, new Insets(10, 20, 10, 20)); // Add margin to the label.
    winLabel.getStyleClass().add("fantasy-title"); // Apply a style class to the label.

    // Create a button that navigates back to the start page.
    Button backToMenuButton = new Button("Back to the start page");
    backToMenuButton.getStyleClass().addAll("fantasy-button-sidbar"); // Apply a button style.
    VBox.setMargin(backToMenuButton, new Insets(25, 25, 25, 25)); // Add margin to the button.

    // Set the action for the button to navigate to the StartPage when clicked.
    backToMenuButton.setOnAction(
        e -> {
          NavigationManager.navigate(new StartPage()); // Navigate to the start page.
        });

    // Create a button to exit the application.
    Button exitButton = new Button("Leave Runeborne");
    exitButton.getStyleClass().add("fantasy-button-sidbar"); // Apply a button style.
    VBox.setMargin(exitButton, new Insets(25, 25, 25, 25)); // Add margin to the button.

    // Set the action for the button to close the application when clicked.
    exitButton.setOnAction(
        e -> {
          MyWindow.closeApplication(); // Close the application.
        });

    // Add all the components (win label, back button, exit button) to the VBox.
    getChildren().addAll(winLabel, backToMenuButton, exitButton);
  }
}
