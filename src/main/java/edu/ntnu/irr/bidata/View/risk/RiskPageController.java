package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.NavigationManager;
import edu.ntnu.irr.bidata.controller.StartPageController;
import edu.ntnu.irr.bidata.model.risk.Risk;
import edu.ntnu.irr.bidata.view.PopUp;

/**
 * Controller for RiskPageView is responsible for attaching and handling exit
 * and save events on the view.
 * */
public class RiskPageController {
  private final RiskPageView view;

  /**
   * Responsible for constructing the Risk view and attaching actions.
   *
   * @param risk Current state of Risk game, containing the game board and other game-related data.
   * */
  public RiskPageController(Risk risk) {
    this.view = new RiskPageView(risk);
    view.getExitGameButton().setOnAction(
        e -> {
          NavigationManager.navigate(
              new StartPageController().getView()); // Navigate to the start page
        });
    view.getSaveButton().setOnAction(
        e -> {
          try {
            risk.saveGame();
            PopUp.showInfo("Game saved", "Game saved as\n" + risk.getGameName());
          } catch (RuntimeException ex) {
            PopUp.showError("Error saving game", ex.getMessage());
          }
        });
  }

  /**
   * Is responsible for retrieving the view stored in the controller. Should be used
   * to attach event listeners / action listeners.

   * @return risk view held in controller.
   * */
  public RiskPageView getView() {
    return view;
  }
}
