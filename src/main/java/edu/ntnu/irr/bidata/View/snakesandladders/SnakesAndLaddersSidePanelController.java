package edu.ntnu.irr.bidata.view.snakesandladders;

import edu.ntnu.irr.bidata.controller.NavigationManager;
import edu.ntnu.irr.bidata.model.Die;
import edu.ntnu.irr.bidata.model.FileHandler;
import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import edu.ntnu.irr.bidata.model.Player;
import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.startpage.StartPageController;
import edu.ntnu.irr.bidata.view.winningpage.WinningPageController;
import javafx.scene.paint.Color;

public class SnakesAndLaddersSidePanelController implements Observer<SnakesAndLadders> {
  private final SnakesAndLaddersSidePanelView view;

  public SnakesAndLaddersSidePanelController(SnakesAndLadders snakesAndLadders) {
    this.view = new SnakesAndLaddersSidePanelView();

    view.getRollButton().setOnAction(e -> {
      Player currentPlayer = snakesAndLadders.getCurrentPlayer();
      snakesAndLadders.takeAction();
      if (snakesAndLadders.getBoard().hasWon(currentPlayer)) {
        FileHandler.deleteGame(snakesAndLadders.getGameName());
        NavigationManager.navigate(new WinningPageController(currentPlayer.getName(), "snakes-and-ladders-win-page").getView());
      }
    });
    view.getSaveButton().setOnAction(e -> {
      try {
        snakesAndLadders.saveGame();
      } catch (RuntimeException ex) {
        PopUp.showError("An error occurred while saving.", ex.getMessage());
      }
    });
    view.getExitGameButton().setOnAction(e -> {
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

  public void update(SnakesAndLadders snakesAndLadders) {
    view.getUsernameLabel().setText(snakesAndLadders.getCurrentPlayer().getName());
  }

  public SnakesAndLaddersSidePanelView getView() {
    return view;
  }
}
