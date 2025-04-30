package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISimpleObserver;
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
    render();
  }
  public void render() {
    this.setText(Integer.toString(country.getArmies()));
    this.setStyle("-fx-border-radius: 100; -fx-font-weight: bold");
    setLayoutX(boardWidth * country.getRelativeX());
    setLayoutY(boardHeight * country.getRelativeY());
  }
  public void update() {
    render(); // Don't update for now since country should stay the same
    System.out.println(country.getName() + ", " + country.getOwner() + ": " + country.getArmies());
  }
}
