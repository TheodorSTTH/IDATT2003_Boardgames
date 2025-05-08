package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.controller.risk.CountryController;
import edu.ntnu.irr.bidata.model.risk.Country;
import edu.ntnu.irr.bidata.view.PopUp;
import java.io.InputStream;
import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RiskBoardView is the visual representation of the game board in the Risk game. It displays the
 * background image of the Risk board and overlays the countries on top of it. Each country is
 * represented by a controller that manages the interaction and updates with the corresponding
 * country.
 */
public class RiskBoardView extends Pane {
  private static final Logger log = LoggerFactory.getLogger(RiskBoardView.class);

  /**
   * Constructs a RiskBoardView with the given countries and their respective visual
   * representations. This method loads the Risk board background image and scales it according to
   * the screen size. It also creates visual representations for each country and adds them to the
   * board.
   *
   * @param countries A map containing country names as keys and their respective Country objects as
   *     values.
   */
  public RiskBoardView(HashMap<String, Country> countries) {
    getChildren().clear(); // Clear any existing children from the pane
    double sizeMultiplier = 10.3; // Factor to scale the image to fit the screen
    double imagePixelWidth =
        75 * sizeMultiplier; // Calculating the image width based on the multiplier
    double imagePixelHeight =
        52 * sizeMultiplier; // Calculating the image height based on the multiplier

    // https://commons.wikimedia.org/wiki/File:Risk_board.svg
    InputStream riskImageInputStream = getClass().getResourceAsStream("/risk_board.png");
    if (riskImageInputStream != null) {
      Image riskBoardImage = new Image(riskImageInputStream);
      ImageView imageView = new ImageView(riskBoardImage);

      // Set the fit width and height for the image, based on the scaled values
      imageView.setFitWidth(imagePixelWidth);
      imageView.setFitHeight(imagePixelHeight);

      // Add the background image to the board view
      getChildren().add(imageView);
    } else {
      log.error("Failed to read risk board image");
      PopUp.showError("Error", "Something went wrong reading risk board image");
    }

    // For each country in the countries map, create a CountryController and add the country to
    // the board
    for (Country country : countries.values()) {
      // Create a controller for each country
      CountryController countryController =
          new CountryController(country, imagePixelWidth, imagePixelHeight);
      country.registerObserver(countryController);
      getChildren().add(countryController.getView());
    }
  }
}
