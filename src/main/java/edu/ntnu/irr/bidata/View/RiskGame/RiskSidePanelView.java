package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import javafx.scene.control.Accordion;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RiskSidePanelView extends VBox {
  private final Accordion parentAccordion;
  private final PlaceTroopsPaneView placeTroopsPaneView;
  private final AttackPaneView attackPaneView;
  private final MoveTroopsPaneView moveTroopsPaneView;

  public RiskSidePanelView(
      PlaceTroopsPaneView placeTroopsPaneView,
      AttackPaneView attackPaneView,
      MoveTroopsPaneView moveTroopsPaneView
  ) {
    this.placeTroopsPaneView = placeTroopsPaneView;
    this.attackPaneView = attackPaneView;
    this.moveTroopsPaneView = moveTroopsPaneView;

    this.parentAccordion = new Accordion();
    this.setStyle("-fx-background-color:rgb(255, 255, 255);");

    this.setMinWidth(200);
    parentAccordion.setMaxHeight(Double.MAX_VALUE);
    setVgrow(parentAccordion, Priority.ALWAYS);

    this.parentAccordion.getPanes().addAll(
        placeTroopsPaneView,
        attackPaneView,
        moveTroopsPaneView
    );
    this.getChildren().addAll(
        parentAccordion
    );
  }

  private void disableAll() {
    placeTroopsPaneView.setDisable(true);
    attackPaneView.setDisable(true);
    moveTroopsPaneView.setDisable(true);
  }

  private void closeAll() {
    placeTroopsPaneView.setExpanded(false);
    attackPaneView.setExpanded(false);
    moveTroopsPaneView.setExpanded(false);
  }
  
  private void makeAllCollapsible() {
    placeTroopsPaneView.setCollapsible(true);
    attackPaneView.setCollapsible(true);
    moveTroopsPaneView.setCollapsible(true);
  }

  private void resetAll() {
    makeAllCollapsible();
    closeAll();
    disableAll();
  }

  /**
   * Sets which pane should be active and closes & disables the rest of them.
   * */
  public void setPaneActive(AbstractSidebarPaneView pane) {
    resetAll();
    pane.setExpanded(true);
    pane.setDisable(false);
    pane.setCollapsible(false);
    parentAccordion.setExpandedPane(pane);
  }
}
