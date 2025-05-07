package edu.ntnu.irr.bidata.View.RiskGame;

import java.util.HashMap;

import edu.ntnu.irr.bidata.model.Risk.Country;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RiskBoardView extends Pane {
  public RiskBoardView(HashMap<String, Country> countries) {
    getChildren().clear();
    double sizeMultiplier = 10.3;
    double imagePixelWidth = 75 * sizeMultiplier;
    double imagePixelHeight = 52 * sizeMultiplier;
    try {
      // https://commons.wikimedia.org/wiki/File:Risk_board.svg
      Image riskBoardImage = new Image(getClass().getResourceAsStream("/risk_board.png"));
      ImageView imageView = new ImageView(riskBoardImage);
      imageView.setFitWidth(imagePixelWidth);
      imageView.setFitHeight(imagePixelHeight);
      getChildren().add(imageView);
      for (Country country : countries.values()) {
        CountryView countryView = new CountryView(country, imagePixelWidth, imagePixelHeight);
        country.registerObserver(countryView);
        getChildren().add(countryView);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
