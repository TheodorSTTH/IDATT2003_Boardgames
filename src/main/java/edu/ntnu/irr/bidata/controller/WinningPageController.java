package edu.ntnu.irr.bidata.controller;

import edu.ntnu.irr.bidata.MyWindow;
import edu.ntnu.irr.bidata.NavigationManager;
import edu.ntnu.irr.bidata.view.WinningPageView;

/**
 * Controller for the Winning Page of the game. Handles user actions after a game is completed,
 * including navigation back to the main menu or exiting the application.
 */
public class WinningPageController {
  private final WinningPageView view;

  /**
   * Constructs a {@code WinningPageController} and initializes the view with event handlers.
   *
   * @param winner The name of the player who won the game.
   * @param winPageClass The CSS style class used to style the winning page.
   */
  public WinningPageController(String winner, String winPageClass) {
    this.view = new WinningPageView(winner, winPageClass);

    // Set action for the "Back to Menu" button to return to the start page.
    view.getBackToMenuButton()
        .setOnAction(
            e -> {
              NavigationManager.navigate(new StartPageController().getView());
            });

    // Set action for the "Exit" button to close the application.
    view.getExitButton()
        .setOnAction(
            e -> {
              MyWindow.closeApplication();
            });
  }

  /**
   * Returns the view managed by this controller.
   *
   * @return the {@code WinningPageView} instance
   */
  public WinningPageView getView() {
    return view;
  }
}
