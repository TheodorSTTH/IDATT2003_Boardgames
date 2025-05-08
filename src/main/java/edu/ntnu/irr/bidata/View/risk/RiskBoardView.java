package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.model.FileHandler;
import edu.ntnu.irr.bidata.view.PopUp;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.HashMap;
import edu.ntnu.irr.bidata.model.risk.Country;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RiskBoardView extends Pane {
  private static final Logger log = LoggerFactory.getLogger(RiskBoardView.class);

  public RiskBoardView(HashMap<String, Country> countries) {
    getChildren().clear();
    double sizeMultiplier = 10.3;
    double imagePixelWidth = 75 * sizeMultiplier;
    double imagePixelHeight = 52 * sizeMultiplier;
    // https://commons.wikimedia.org/wiki/File:Risk_board.svg
    InputStream riskImageInputStream = getClass().getResourceAsStream("/risk_board.png");
    if (riskImageInputStream != null) {
      Image riskBoardImage = new Image(riskImageInputStream);
      ImageView imageView = new ImageView(riskBoardImage);
      imageView.setFitWidth(imagePixelWidth);
      imageView.setFitHeight(imagePixelHeight);
      getChildren().add(imageView);
    } else {
      log.error("Failed to read risk board image");
      PopUp.showError("Error", "Something went wrong reading risk board image");
    }
    for (Country country : countries.values()) {
      CountryController countryController = new CountryController(country, imagePixelWidth, imagePixelHeight);
      country.registerObserver(countryController);
      getChildren().add(countryController.getView());
    }
  }
}
