package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import javafx.scene.control.Accordion;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RiskSidePanelView extends VBox implements IObserver<AbstractSidebarPane> {
  private final Accordion parentAccordion;
  private final PlaceTroopsPane placeTroopsPane;
  private final AttackPane attackPane;
  private final MoveTroopsPane moveTroopsPane;
  private final Risk risk;

  public RiskSidePanelView(Risk risk) {
    this.placeTroopsPane = new PlaceTroopsPane(risk);
    this.attackPane = new AttackPane(risk);
    this.moveTroopsPane = new MoveTroopsPane(risk);
    this.parentAccordion = new Accordion();
    this.risk = risk;
    placeTroopsPane.registerObserver(this);
    attackPane.registerObserver(this);
    moveTroopsPane.registerObserver(this);
    placeTroopsPane.setNextSidebarPane(attackPane);
    attackPane.setNextSidebarPane(moveTroopsPane);
    moveTroopsPane.setNextSidebarPane(placeTroopsPane);
    this.setMinWidth(200);
    parentAccordion.setMaxHeight(Double.MAX_VALUE);
    setVgrow(parentAccordion, Priority.ALWAYS);

    setPaneActive(placeTroopsPane);
    this.parentAccordion.getPanes().addAll(
        placeTroopsPane,
        attackPane,
        moveTroopsPane
    );
    this.getChildren().addAll(
        parentAccordion
    );
    update(placeTroopsPane);
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
  
  private void makeAllCollapsible() {
    placeTroopsPane.setCollapsible(true);
    attackPane.setCollapsible(true);
    moveTroopsPane.setCollapsible(true);
  }

  private void resetAll() {
    makeAllCollapsible();
    closeAll();
    disableAll();
  }

  private void setPaneActive(AbstractSidebarPane pane) {
    resetAll();
    pane.setExpanded(true);
    pane.setDisable(false);
    pane.setCollapsible(false);
    parentAccordion.setExpandedPane(pane);
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
