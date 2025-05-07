package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.model.Risk.Country;
import edu.ntnu.irr.bidata.model.interfaces.observer.IObserver;
import edu.ntnu.irr.bidata.model.interfaces.observer.ISimpleObserver;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

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
      PopUp.showInfo(country.getName(),
          country.getName() + "\nOwner: " + country.getOwner() + "\nArmies: " + country.getArmies());
    });
    render();
  }
  
  public void render() {
    Label text = new Label(Integer.toString(country.getArmies()));
    text.getStyleClass().add("country-label");

    ImageView swordIcon = new ImageView(new Image(
        getClass().getResource("/sword.png").toExternalForm()
    ));
    swordIcon.setFitHeight(12);
    swordIcon.setPreserveRatio(true);
    swordIcon.setSmooth(true);


    HBox content = new HBox(5, text, swordIcon); 
    content.setAlignment(Pos.CENTER_RIGHT);
    this.setGraphic(content);


    this.getStyleClass().add("country-view");
    this.setStyle("-fx-background-color: " + country.getOwnerColor() + "; ");
    setLayoutX(boardWidth * country.getRelativeX());
    setLayoutY(boardHeight * country.getRelativeY());
  }

  public void update() {
    render(); // The country should stay the same
  }
}
