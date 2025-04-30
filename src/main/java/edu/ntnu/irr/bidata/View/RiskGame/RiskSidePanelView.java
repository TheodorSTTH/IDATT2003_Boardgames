package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.View.PopUp;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class RiskSidePanelView extends Accordion implements IObserver<AbstractSidebarPane> {
  private final PlaceTroopsPane placeTroopsPane;
  private final AttackPane attackPane;
  private final MoveTroopsPane moveTroopsPane;

  public RiskSidePanelView(Risk risk) {
    this.placeTroopsPane = new PlaceTroopsPane(risk);
    this.attackPane = new AttackPane(risk);
    this.moveTroopsPane = new MoveTroopsPane(risk);
    placeTroopsPane.registerObserver(this);
    attackPane.registerObserver(this);
    moveTroopsPane.registerObserver(this);
    placeTroopsPane.setNextSidebarPane(attackPane);
    attackPane.setNextSidebarPane(moveTroopsPane);
    moveTroopsPane.setNextSidebarPane(placeTroopsPane);
    this.setMinWidth(200);

    setPaneActive(placeTroopsPane);
    this.getPanes().addAll(
        placeTroopsPane,
        attackPane,
        moveTroopsPane
    );
  }
  private void disableAll() {
    placeTroopsPane.setDisable(true);
    attackPane.setDisable(true);
    moveTroopsPane.setDisable(true);
  }
  private void closeAll() {
    placeTroopsPane.setExpanded(false);
    attackPane.setExpanded(false);
    moveTroopsPane.setExpanded(false);
  }
  private void setPaneActive(AbstractSidebarPane pane) {
    closeAll();
    disableAll();
    pane.setExpanded(true);
    pane.setDisable(false);
    setExpandedPane(pane);
  }

  /**
   * Updates which pane is currently open.
   *
   * @param nextPane is the pane we are going to open.
   * */
  public void update(AbstractSidebarPane nextPane) {
    setPaneActive(nextPane);
  }
}
