package edu.ntnu.irr.bidata.view.risk;

import javafx.scene.control.Accordion;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * The RiskSidePanelView is the side panel of the Risk game that contains different action panes
 * like placing troops, attacking, and moving troops. The view uses an Accordion to allow users to
 * expand and collapse each action pane.
 */
public class RiskSidePanelView extends VBox {

  private final Accordion parentAccordion; // Accordion to hold the action panes
  private final PlaceTroopsPaneView placeTroopsPaneView; // View for placing troops
  private final AttackPaneView attackPaneView; // View for attacking
  private final MoveTroopsPaneView moveTroopsPaneView; // View for moving troops

  /**
   * Constructs a new RiskSidePanelView with the given panes for placing troops, attacking, and
   * moving troops. These panes are displayed as collapsible sections within an Accordion layout.
   *
   * @param placeTroopsPaneView The pane for placing troops.
   * @param attackPaneView The pane for attacking.
   * @param moveTroopsPaneView The pane for moving troops.
   */
  public RiskSidePanelView(
      PlaceTroopsPaneView placeTroopsPaneView,
      AttackPaneView attackPaneView,
      MoveTroopsPaneView moveTroopsPaneView) {
    this.placeTroopsPaneView = placeTroopsPaneView;
    this.attackPaneView = attackPaneView;
    this.moveTroopsPaneView = moveTroopsPaneView;

    // Initialize the Accordion and set the background style
    this.parentAccordion = new Accordion();
    this.setStyle("-fx-background-color:rgb(255, 255, 255);");

    // Set minimum width and allow the Accordion to grow vertically
    this.setMinWidth(200);
    parentAccordion.setMaxHeight(Double.MAX_VALUE);
    setVgrow(parentAccordion, Priority.ALWAYS);

    // Add the action panes to the Accordion
    this.parentAccordion.getPanes().addAll(placeTroopsPaneView, attackPaneView, moveTroopsPaneView);

    // Add the Accordion to the VBox container
    this.getChildren().addAll(parentAccordion);
  }

  /** Disables all the action panes (PlaceTroops, Attack, MoveTroops). */
  private void disableAll() {
    placeTroopsPaneView.setDisable(true);
    attackPaneView.setDisable(true);
    moveTroopsPaneView.setDisable(true);
  }

  /** Closes all the action panes (PlaceTroops, Attack, MoveTroops). */
  private void closeAll() {
    placeTroopsPaneView.setExpanded(false);
    attackPaneView.setExpanded(false);
    moveTroopsPaneView.setExpanded(false);
  }

  /** Makes all action panes collapsible (so they can be expanded or collapsed). */
  private void makeAllCollapsible() {
    placeTroopsPaneView.setCollapsible(true);
    attackPaneView.setCollapsible(true);
    moveTroopsPaneView.setCollapsible(true);
  }

  /** Resets all the panes by making them collapsible, closing them, and disabling them. */
  private void resetAll() {
    makeAllCollapsible();
    closeAll();
    disableAll();
  }

  /**
   * Activates the given pane by expanding it, enabling it, and disabling the other panes. This
   * method ensures that only one pane is active at a time, and it is the one passed as the
   * argument.
   *
   * @param pane The pane to be set as active (expanded, enabled, non-collapsible).
   */
  public void setPaneActive(AbstractSidebarPaneView pane) {
    resetAll(); // Reset the state of all panes
    pane.setExpanded(true); // Expand the selected pane
    pane.setDisable(false); // Enable the selected pane
    pane.setCollapsible(false); // Make the selected pane non-collapsible
    parentAccordion.setExpandedPane(pane); // Set the expanded pane in the Accordion
  }
}
