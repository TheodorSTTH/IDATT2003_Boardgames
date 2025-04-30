package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class AttackPane extends AbstractSidebarPane {
  private final ComboBox<Country> attackFromComboBox;
  private final ComboBox<Country> attackTargetComboBox;
  private final Button performAttackOnceButton;
  private final Button performAttackUntilResultButton;
  private final Button ok;

  public AttackPane(Risk risk) {
    super(risk);
    this.attackFromComboBox = new ComboBox<>();
    this.attackTargetComboBox = new ComboBox<>();
    this.performAttackOnceButton = new Button("Perform attack once");
    this.performAttackUntilResultButton = new Button("Perform until result");
    this.ok = new Button("OK, i am done");
    this.setText("Attack");
    this.setLineSpacing(10);

    update();

    attackFromComboBox.valueProperty().addListener((obs, oldFrom, newFrom) -> {
      boolean isFromDefined = newFrom != null;
      updateOnIsFromDefined(newFrom != null);
      if (isFromDefined) {
        attackTargetComboBox.setItems(FXCollections.observableArrayList(risk.getCountriesCurrentPlayerCanAttackFromCountry(newFrom)));
      } else {
        attackTargetComboBox.getItems().clear();
      }
    });
    performAttackOnceButton.setOnAction(event -> {
      Country from = attackFromComboBox.getValue();
      Country to = attackTargetComboBox.getValue();
      if (from != null && to != null) {
        risk.attackOnce(from.getName(), to.getName());
        update();
      }
    });
    performAttackUntilResultButton.setOnAction(event -> {
      Country from = attackFromComboBox.getValue();
      Country to = attackTargetComboBox.getValue();
      if (from != null && to != null) {
        risk.attackUntilResolt(from.getName(), to.getName());
        update();
      }
    });
    ok.setOnAction(event -> {
      notifyObservers();
    });

    getContainer().getChildren().addAll(
        new Label("Attack from"),
        attackFromComboBox,
        new Label("Attack country"),
        attackTargetComboBox,
        performAttackOnceButton,
        performAttackUntilResultButton,
        ok
    );
  }

  private void updateOnIsFromDefined(boolean isFromDefined) {
    attackTargetComboBox.setVisible(isFromDefined);
    attackTargetComboBox.setManaged(isFromDefined);
    performAttackUntilResultButton.setVisible(isFromDefined);
    performAttackOnceButton.setVisible(isFromDefined);
  }

  private void update() {
    List<Country> attackFromOptions = risk.getCountriesCurrentPlayerCanAttackFrom();
    attackFromComboBox.setItems(FXCollections.observableArrayList(attackFromOptions));
    attackTargetComboBox.setValue(null);
    updateOnIsFromDefined(false);
    attackTargetComboBox.getItems().clear();
  }
}
