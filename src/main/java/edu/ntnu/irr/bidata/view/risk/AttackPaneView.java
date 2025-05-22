package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.model.risk.Country;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * AttackPaneView represents the sidebar UI for the attack phase of the game. It allows the user to
 * choose which country to attack from, which country to attack to, and provides buttons to perform
 * the attack or cancel. It also shows dice rolls as part of the attack process.
 */
public class AttackPaneView extends AbstractSidebarPaneView {

  // UI Components for the attack phase
  private final ComboBox<Country> attackFromComboBox;
  private final ComboBox<Country> attackTargetComboBox;
  private final Button performAttackOnceButton;
  private final Button performAttackUntilResultButton;
  private final Button ok;
  private final FlowPane dieBox; // FlowPane for displaying dice rolls
  private final Label currentUserLabel =
      new Label(); // Label to display the current user's name or status

  /**
   * Constructs the AttackPaneView with the necessary UI components. Sets up the layout, styles, and
   * event handlers for various controls.
   */
  public AttackPaneView() {
    // Set style for the container of the pane
    getContainer().getStyleClass().addAll("attack-pane", "background");

    // Initialize the dieBox (where dice rolls will be displayed)
    this.dieBox = new FlowPane();
    dieBox.setHgap(12); // Horizontal gap between dice
    dieBox.setVgap(12); // Vertical gap between dice
    VBox.setMargin(dieBox, new Insets(0, 0, 0, 50));

    // Initialize currentUserLabel and apply style
    this.currentUserLabel.getStyleClass().addAll("fantasy", "text-gold", "text-20");
    VBox.setMargin(currentUserLabel, new Insets(0, 0, 0, 10));

    // Set title for the AttackPaneView
    this.setText("Attack");
    this.setLineSpacing(10);

    // Create and style the 'Attack from' label and ComboBox
    Label attackFromLabel = new Label("Attack from:");
    attackFromLabel.getStyleClass().addAll("fantasy", "text-gold", "text-20");
    VBox.setMargin(attackFromLabel, new Insets(20, 0, 0, 10));

    this.attackFromComboBox = new ComboBox<>();
    this.attackFromComboBox.getStyleClass().addAll("combo-box", "sidebar-size", "fantasy");
    attackFromComboBox.setPromptText("Select country to attack from");
    VBox.setMargin(attackFromComboBox, new Insets(0, 0, 0, 10));

    // Create and style the 'Attack to' label and ComboBox
    Label attackToLabel = new Label("Attack to:");
    attackToLabel.getStyleClass().addAll("fantasy", "text-gold", "text-20");
    VBox.setMargin(attackToLabel, new Insets(0, 0, 0, 10));

    this.attackTargetComboBox = new ComboBox<>();
    this.attackTargetComboBox.getStyleClass().addAll("combo-box", "sidebar-size", "fantasy");
    attackTargetComboBox.setPromptText("Select country to attack to");
    VBox.setMargin(attackTargetComboBox, new Insets(0, 0, 10, 10));

    // Create the 'Perform Attack Once' button and style it
    this.performAttackOnceButton = new Button("Perform attack once");
    this.performAttackOnceButton.getStyleClass().addAll("button", "sidebar-size", "fantasy");
    VBox.setMargin(performAttackOnceButton, new Insets(0, 0, 10, 10));

    // Create the 'Perform Attack Until Result' button and style it
    this.performAttackUntilResultButton = new Button("Perform until result");
    this.performAttackUntilResultButton.getStyleClass().addAll("button", "sidebar-size", "fantasy");
    VBox.setMargin(performAttackUntilResultButton, new Insets(0, 0, 10, 10));

    // Create the 'OK, I am done' button and style it
    this.ok = new Button("OK, I am done");
    this.ok.getStyleClass().addAll("button", "sidebar-size", "fantasy");
    VBox.setMargin(ok, new Insets(0, 0, 10, 10));

    // Add all components to the container (VBox)
    getContainer()
        .getChildren()
        .addAll(
            currentUserLabel,
            attackFromLabel,
            attackFromComboBox,
            attackToLabel,
            attackTargetComboBox,
            performAttackOnceButton,
            performAttackUntilResultButton,
            ok,
            dieBox);
  }

  // Getter methods for each of the components

  /**
   * Returns the ComboBox for selecting the country to attack from.
   *
   * @return the ComboBox for selecting the attacking country
   */
  public final ComboBox<Country> getAttackFromComboBox() {
    return attackFromComboBox;
  }

  /**
   * Returns the ComboBox for selecting the target country.
   *
   * @return the ComboBox for selecting the target country
   */
  public final ComboBox<Country> getAttackTargetComboBox() {
    return attackTargetComboBox;
  }

  /**
   * Returns the button that triggers a single attack.
   *
   * @return the button to perform a single attack
   */
  public final Button getPerformAttackOnceButton() {
    return performAttackOnceButton;
  }

  /**
   * Returns the button that triggers attacks until a result is achieved.
   *
   * @return the button to perform attacks until result
   */
  public final Button getPerformAttackUntilResultButton() {
    return performAttackUntilResultButton;
  }

  /**
   * Returns the "OK, I am done" button.
   *
   * @return the button to confirm the attack phase is completed
   */
  public final Button getOk() {
    return ok;
  }

  /**
   * Returns the label that displays the current user's name or status.
   *
   * @return the label showing current user information
   */
  public final Label getCurrentUserLabel() {
    return currentUserLabel;
  }

  /**
   * Returns the FlowPane that displays dice rolls during the attack.
   *
   * @return the FlowPane that holds dice rolls
   */
  public final FlowPane getDieBox() {
    return dieBox;
  }
}
