package edu.ntnu.irr.bidata.view.snakesandladders;

import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;

public class SnakesAndLaddersCanvasController implements Observer<SnakesAndLadders> {
  private SnakesAndLaddersCanvasView view;

  public SnakesAndLaddersCanvasController(SnakesAndLadders snakesAndLadders) {
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
  public void update(SnakesAndLadders snakesAndLadders) {
    view.renderBoard();
    view.drawQuizEvents(snakesAndLadders.getBoard().getEvents());
    view.drawSnakesAndLadders(snakesAndLadders.getBoard().getEvents());
    view.placePlayers(snakesAndLadders.getPlayerPositions());
    view.drawPlayers(snakesAndLadders.getPlayerPositions());
  }
}
