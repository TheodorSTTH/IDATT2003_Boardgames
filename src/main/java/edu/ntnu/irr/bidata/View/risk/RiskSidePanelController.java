package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.model.risk.Risk;
import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;

public class RiskSidePanelController implements Observer<AbstractSidebarPaneController> {
  private RiskSidePanelView view;
  private final PlaceTroopsPaneController placeTroopsPaneController;
  private final AttackPaneController attackPaneController;
  private final MoveTroopsPaneController moveTroopsPaneController;

  public RiskSidePanelController(Risk risk) {
    this.placeTroopsPaneController = new PlaceTroopsPaneController(risk);
    this.attackPaneController = new AttackPaneController(risk);
    this.moveTroopsPaneController = new MoveTroopsPaneController(risk);
    this.view = new RiskSidePanelView(
        placeTroopsPaneController.getView(),
        attackPaneController.getView(),
        moveTroopsPaneController.getView()
    );

    placeTroopsPaneController.registerObserver(this);
    attackPaneController.registerObserver(this);
    moveTroopsPaneController.registerObserver(this);

    placeTroopsPaneController.setNextSidebarPane(attackPaneController);
    attackPaneController.setNextSidebarPane(moveTroopsPaneController);
    moveTroopsPaneController.setNextSidebarPane(placeTroopsPaneController);

    update(placeTroopsPaneController);
  }

  /**
   * Updates which pane is currently open.
   *
   * @param nextPane is the pane we are going to open.
   * */
  public void update(AbstractSidebarPaneController nextPane) {
    view.setPaneActive(nextPane.getView());
  }

  public RiskSidePanelView getView() {
    return view;
  }
}
