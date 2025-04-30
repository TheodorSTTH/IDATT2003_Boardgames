package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CountryView extends Button implements IObserver {
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
    setLayoutX(boardWidth * country.getRelativeX());
    setLayoutY(boardHeight * country.getRelativeY());
  }
  public void update() {
    render();
  }
}
