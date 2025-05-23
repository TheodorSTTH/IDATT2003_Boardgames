package edu.ntnu.irr.bidata.controller.risk;

import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.newlogic.risk.RiskGame;
import edu.ntnu.irr.bidata.view.risk.RiskSidePanelView;

/**
 * Controller for managing the side panel of the Risk game interface, which contains multiple game
 * phase panes (e.g., placing troops, attacking, moving troops).
 *
 * <p>It handles transitions between panes based on the game's turn phase and responds to events
 * from individual pane controllers via the Observer pattern.
 */
public class RiskSidePanelController implements Observer<AbstractSidebarPaneController> {

  private RiskSidePanelView view;
  private final PlaceTroopsPaneController placeTroopsPaneController;
  private final AttackPaneController attackPaneController;
  private final MoveTroopsPaneController moveTroopsPaneController;

  /**
   * Constructs the RiskSidePanelController and sets up its child pane controllers, view, and
   * transitions between game phases.
   *
   * @param risk the main Risk game model
   */
  public RiskSidePanelController(RiskGame risk) {
    this.placeTroopsPaneController = new PlaceTroopsPaneController(risk);
    this.attackPaneController = new AttackPaneController(risk);
    this.moveTroopsPaneController = new MoveTroopsPaneController(risk);

    // Create view using the views from the individual controllers
    this.view =
        new RiskSidePanelView(
            placeTroopsPaneController.getView(),
            attackPaneController.getView(),
            moveTroopsPaneController.getView());

    // Register this controller as an observer to all pane controllers
    placeTroopsPaneController.registerObserver(this);
    attackPaneController.registerObserver(this);
    moveTroopsPaneController.registerObserver(this);

    // Define the sequence of game phases
    placeTroopsPaneController.setNextSidebarPane(attackPaneController);
    attackPaneController.setNextSidebarPane(moveTroopsPaneController);
    moveTroopsPaneController.setNextSidebarPane(placeTroopsPaneController);

    // Start the game with the "Place Troops" pane active
    update(placeTroopsPaneController);
  }

  /**
   * Switches the active pane in the side panel view. Triggered by phase changes or user actions
   * during the turn.
   *
   * @param nextPane the next pane to activate in the side panel
   */
  @Override
  public void update(AbstractSidebarPaneController nextPane) {
    view.setPaneActive(nextPane.getView());
  }

  /**
   * Returns the view associated with this controller.
   *
   * @return the RiskSidePanelView containing all the sidebar panes
   */
  public RiskSidePanelView getView() {
    return view;
  }
}
