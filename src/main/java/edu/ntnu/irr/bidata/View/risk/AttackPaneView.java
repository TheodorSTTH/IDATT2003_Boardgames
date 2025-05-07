package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.model.risk.Country;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class AttackPaneView extends AbstractSidebarPaneView{
  private final ComboBox<Country> attackFromComboBox;
  private final ComboBox<Country> attackTargetComboBox;
  private final Button performAttackOnceButton;
  private final Button performAttackUntilResultButton;
  private final Button ok;
  private final FlowPane dieBox;
  private final Label currentUserLabel = new Label();

  public AttackPaneView() {
    getContainer().getStyleClass().add("attack-pane");
    this.dieBox = new FlowPane();
    dieBox.setHgap(12);
    dieBox.setVgap(12);
    VBox.setMargin(dieBox, new javafx.geometry.Insets(0, 0, 0, 50));

    this.currentUserLabel.getStyleClass().add("fantasy-title-sidbar");
    VBox.setMargin(currentUserLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    this.setText("Attack");
    this.setLineSpacing(10);

    Label attackFromLabel = new Label("Attack from:");
    attackFromLabel.getStyleClass().add("fantasy-text-sidbar");
    VBox.setMargin(attackFromLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    this.attackFromComboBox = new ComboBox<>();
    this.attackFromComboBox.getStyleClass().add("fantasy-combo-box-sidbar");
    attackFromComboBox.setPromptText("Select country to attack from");
    VBox.setMargin(attackFromComboBox, new javafx.geometry.Insets(0, 0, 0, 10));

    Label attackToLabel = new Label("Attack to:");
    attackToLabel.getStyleClass().add("fantasy-text-sidbar");
    VBox.setMargin(attackToLabel, new javafx.geometry.Insets(0, 0, 0, 10));

    this.attackTargetComboBox = new ComboBox<>();
    this.attackTargetComboBox.getStyleClass().add("fantasy-combo-box-sidbar");
    attackTargetComboBox.setPromptText("Select country to attack to");
    VBox.setMargin(attackTargetComboBox, new javafx.geometry.Insets(0, 0, 10, 10));

    this.performAttackOnceButton = new Button("Perform attack once");
    this.performAttackOnceButton.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(performAttackOnceButton, new javafx.geometry.Insets(0, 0, 10, 10));

    this.performAttackUntilResultButton = new Button("Perform until result");
    this.performAttackUntilResultButton.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(performAttackUntilResultButton, new javafx.geometry.Insets(0, 0, 10, 10));

    this.ok = new Button("OK, i am done");
    this.ok.getStyleClass().add("fantasy-button-sidbar");
    VBox.setMargin(ok, new javafx.geometry.Insets(0, 0, 10, 10));

    getContainer().getChildren().addAll(
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

  public final ComboBox<Country> getAttackFromComboBox() {
    return attackFromComboBox;
  }

  public final ComboBox<Country> getAttackTargetComboBox() {
    return attackTargetComboBox;
  }

  public final Button getPerformAttackOnceButton() {
    return performAttackOnceButton;
  }

  public final Button getPerformAttackUntilResultButton() {
    return performAttackUntilResultButton;
  }

  public final Button getOk() {
    return ok;
  }

  public final Label getCurrentUserLabel() {
    return currentUserLabel;
  }

  public final FlowPane getDieBox() {
    return dieBox;
  }
}
