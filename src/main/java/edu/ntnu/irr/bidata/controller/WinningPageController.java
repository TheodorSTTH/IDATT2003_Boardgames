package edu.ntnu.irr.bidata.controller;

import edu.ntnu.irr.bidata.MyWindow;
import edu.ntnu.irr.bidata.NavigationManager;
import edu.ntnu.irr.bidata.view.WinningPageView;

public class WinningPageController {
  private final WinningPageView view;

  /**
   * Constructs a WinningPage to display the winner and navigation options.
   *
   * @param winner The name of the player who won the game.
   * @param winPageClass The style class used to style the winning page.
   */
  public WinningPageController(String winner, String winPageClass) {
    this.view = new WinningPageView(winner, winPageClass);

    // Set the action for the button to navigate to the StartPage when clicked.
    view.getBackToMenuButton().setOnAction(e -> {
      NavigationManager.navigate(new StartPageController().getView());
    });

    // Set the action for the button to close the application when clicked.
    view.getExitButton().setOnAction(e -> {
      MyWindow.closeApplication();
    });
  }

  public WinningPageView getView() {
    return view;
  }
}
