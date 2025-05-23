package edu.ntnu.irr.bidata.controller.snakesandladders;

import edu.ntnu.irr.bidata.NavigationManager;
import edu.ntnu.irr.bidata.controller.StartPageController;
import edu.ntnu.irr.bidata.controller.WinningPageController;
import edu.ntnu.irr.bidata.model.Die;
import edu.ntnu.irr.bidata.model.FileHandler;
import edu.ntnu.irr.bidata.model.Player;
import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import edu.ntnu.irr.bidata.view.DieView;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.snakesandladders.SnakesAndLaddersSidePanelView;
import javafx.scene.paint.Color;

/**
 * Controller for the Snakes and Ladders side panel, managing interactions with the roll, save, and
 * exit buttons. It also observes the game model and updates the UI accordingly.
 */
public class SnakesAndLaddersSidePanelController implements Observer<SnakesAndLadders> {
  private final SnakesAndLaddersSidePanelView view;

  /**
   * Constructs the side panel controller, sets up button actions, registers observers, and
   * initializes the die views.
   *
   * @param snakesAndLadders the Snakes and Ladders game model to control and observe
   */
  public SnakesAndLaddersSidePanelController(SnakesAndLadders snakesAndLadders) {
    this.view = new SnakesAndLaddersSidePanelView();

    view.getRollButton()
        .setOnAction(
            e -> {
              Player currentPlayer = snakesAndLadders.getCurrentPlayer();
              snakesAndLadders.takeAction();
              if (snakesAndLadders.getBoard().hasWon(currentPlayer)) {
                FileHandler.deleteGame(snakesAndLadders.getGameName());
                NavigationManager.navigate(
                    new WinningPageController(
                            currentPlayer.getName(), "snakes-and-ladders-win-page")
                        .getView());
              }
            });

    view.getSaveButton()
        .setOnAction(
            e -> {
              try {
                snakesAndLadders.saveGame();
                PopUp.showInfo("Game saved", "Game saved as\n" + snakesAndLadders.getGameName());
              } catch (RuntimeException ex) {
                PopUp.showError("An error occurred while saving.", ex.getMessage());
              }
            });

    view.getExitGameButton()
        .setOnAction(
            e -> {
              NavigationManager.navigate(new StartPageController().getView());
            });

    snakesAndLadders.registerObserver(this);

    for (Die die : snakesAndLadders.getDice().getDice()) {
      DieView newDieView = new DieView(40, Color.WHITE, Color.BLACK);
      newDieView.setVisible(false);
      die.registerObserver(newDieView);
      view.getDiceBox().getChildren().add(newDieView);
    }

    update(snakesAndLadders);
  }

  /**
   * Updates the side panel UI to reflect the current player's name.
   *
   * @param snakesAndLadders the game model to extract current player information from
   */
  @Override
  public void update(SnakesAndLadders snakesAndLadders) {
    view.getUsernameLabel().setText(snakesAndLadders.getCurrentPlayer().getName());
  }

  /**
   * Returns the view associated with this controller.
   *
   * @return the SnakesAndLaddersSidePanelView managed by this controller
   */
  public SnakesAndLaddersSidePanelView getView() {
    return view;
  }
}
