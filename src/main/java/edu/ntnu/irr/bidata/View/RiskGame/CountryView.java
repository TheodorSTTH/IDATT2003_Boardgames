package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISimpleObserver;
import edu.ntnu.irr.bidata.View.PopUp;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CountryView extends Button implements ISimpleObserver {
  private final Country country;
  private final double boardWidth;
  private final double boardHeight;

  public CountryView(Country country, double boardWidth, double boardHeight) {
    country.registerObserver(this);
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
    this.country = country;
    this.setOnAction(e -> {
      PopUp.showInfo(country.getName(), country.getName() + "\nOwner: " + country.getOwner() + "\nArmies: " + country.getArmies());
    });
    render();
  }
  public void render() {
    this.setText(Integer.toString(country.getArmies()));
    //this.setText(country.getName());
    this.getStyleClass().add("country-view");
    this.setStyle("-fx-background-color: " + country.getOwnerColor() + "; ");
    setLayoutX(boardWidth * country.getRelativeX());
    setLayoutY(boardHeight * country.getRelativeY());
  }

  public void update() {
    render(); // The country should stay the same
  }
}
