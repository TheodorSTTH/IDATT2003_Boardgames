package edu.ntnu.irr.bidata.view.risk;

import edu.ntnu.irr.bidata.controller.risk.CountryController;
import edu.ntnu.irr.bidata.model.risk.Country;
import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * RiskBoardView is the visual representation of the game board in the Risk game. It displays the
 * background image of the Risk board and overlays the countries on top of it. Each country is
 * represented by a controller that manages the interaction and updates with the corresponding
 * country.
 */
public class RiskBoardView extends Pane {

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

    try {
      // Load the Risk board background image from the resources
      Image riskBoardImage = new Image(getClass().getResourceAsStream("/risk_board.png"));
      ImageView imageView = new ImageView(riskBoardImage);

      // Set the fit width and height for the image, based on the scaled values
      imageView.setFitWidth(imagePixelWidth);
      imageView.setFitHeight(imagePixelHeight);

      // Add the background image to the board view
      getChildren().add(imageView);

      // For each country in the countries map, create a CountryController and add the country to
      // the board
      for (Country country : countries.values()) {
        // Create a controller for each country
        CountryController countryController =
            new CountryController(country, imagePixelWidth, imagePixelHeight);

        // Register the controller as an observer to listen to updates from the country model
        country.registerObserver(countryController);

        // Add the country's visual representation to the board
        getChildren().add(countryController.getView());
      }
    } catch (Exception e) {
      // Print any exceptions that occur during loading the image or setting up the board
      e.printStackTrace();
    }
  }
}
