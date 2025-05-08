package edu.ntnu.irr.bidata.controller.snakesandladders;

import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.snakesandladders.SnakesAndLadders;
import edu.ntnu.irr.bidata.view.snakesandladders.SnakesAndLaddersCanvasView;

/**
 * Controller responsible for managing the canvas view of the Snakes and Ladders game. It observes
 * the game model and updates the view whenever the game state changes.
 */
public class SnakesAndLaddersCanvasController implements Observer<SnakesAndLadders> {
  private SnakesAndLaddersCanvasView view;

  /**
   * Constructs the canvas controller and registers it as an observer of the SnakesAndLadders model.
   * Initializes the view and triggers the first render based on the current game state.
   *
   * @param snakesAndLadders the SnakesAndLadders game model to observe and visualize
   */
  public SnakesAndLaddersCanvasController(SnakesAndLadders snakesAndLadders) {
    this.view = new SnakesAndLaddersCanvasView();
    snakesAndLadders.registerObserver(this);
    update(snakesAndLadders);
  }

  /**
   * Returns the view managed by this controller.
   *
   * @return the SnakesAndLaddersCanvasView representing the game board
   */
  public SnakesAndLaddersCanvasView getView() {
    return view;
  }

  /**
   * Updates the canvas view to reflect the latest game state. This includes:
   *
   * <ul>
   *   <li>Rendering the board
   *   <li>Drawing quiz events
   *   <li>Drawing snakes and ladders
   *   <li>Placing and drawing players
   * </ul>
   *
   * @param snakesAndLadders the current game model state to be rendered
   */
  @Override
  public void update(SnakesAndLadders snakesAndLadders) {
    view.renderBoard();
    view.drawQuizEvents(snakesAndLadders.getBoard().getEvents());
    view.drawSnakesAndLadders(snakesAndLadders.getBoard().getEvents());
    view.placePlayers(snakesAndLadders.getPlayerPositions());
    view.drawPlayers(snakesAndLadders.getPlayerPositions());
  }
}
