package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.Model.Die;
import edu.ntnu.irr.bidata.Model.FileHandler;
import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.View.StartPage.StartPageController;
import edu.ntnu.irr.bidata.View.WinPage.WinningPageController;
import javafx.scene.paint.Color;

public class SnakesAndLaddersSidePanelController implements IObserver<LaderGame> {
  private final SnakesAndLaddersSidePanelView view;

  public SnakesAndLaddersSidePanelController(LaderGame snakesAndLadders) {
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
      snakesAndLadders.saveGame();
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

  public void update(LaderGame snakesAndLadders) {
    view.getUsernameLabel().setText(snakesAndLadders.getCurrentPlayer().getName());
  }

  public SnakesAndLaddersSidePanelView getView() {
    return view;
  }
}
