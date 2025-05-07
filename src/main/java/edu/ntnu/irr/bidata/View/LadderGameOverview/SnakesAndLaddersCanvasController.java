package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;

public class SnakesAndLaddersCanvasController implements IObserver<LaderGame> {
  private SnakesAndLaddersCanvasView view;

  public SnakesAndLaddersCanvasController(LaderGame snakesAndLadders) {
    this.view = new SnakesAndLaddersCanvasView();
    snakesAndLadders.registerObserver(this);
    update(snakesAndLadders);
  }

  public SnakesAndLaddersCanvasView getView() {
    return view;
  }

  /**
   * Renders the current board with quiz boxes, snakes and ladders. Also places & renders players
   * on the board. Effectively updating the board for the user.
   *
   * @param snakesAndLadders is the current snakes and ladders game object.
   * */
  @Override
  public void update(LaderGame snakesAndLadders) {
    view.renderBoard();
    view.drawQuizEvents(snakesAndLadders.getBoard().getEvents());
    view.drawSnakesAndLadders(snakesAndLadders.getBoard().getEvents());
    view.placePlayers(snakesAndLadders.getPlayerPositions());
    view.drawPlayers(snakesAndLadders.getPlayerPositions());
  }
}
