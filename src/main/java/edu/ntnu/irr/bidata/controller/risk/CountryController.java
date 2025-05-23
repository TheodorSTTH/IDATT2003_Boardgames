package edu.ntnu.irr.bidata.controller.risk;

import edu.ntnu.irr.bidata.model.interfaces.observer.SimpleObserver;
import edu.ntnu.irr.bidata.model.newlogic.risk.Country;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.risk.CountryView;

/**
 * Controller class for managing a single country in the Risk game. This class listens for changes
 * in the country's state and updates the view accordingly. Handles user interactions, such as
 * displaying information about the country when clicked.
 */
public class CountryController implements SimpleObserver {

  private final Country country;
  private final CountryView view;

  /**
   * Constructor for the CountryController. Initializes the view for the country and registers this
   * controller as an observer.
   *
   * @param country The country object that this controller manages.
   * @param boardWidth The width of the game board.
   * @param boardHeight The height of the game board.
   */
  public CountryController(Country country, double boardWidth, double boardHeight) {
    this.view =
        new CountryView(boardWidth, boardHeight); // Initialize the view with the board dimensions.
    this.country = country;
    country.registerObserver(this); // Register this controller as an observer of the country.

    // Set up action when the country is clicked
    view.setOnAction(
        e -> {
          // Display a pop-up with the country's details
          PopUp.showInfo(
              country.getName(),
              country.getName()
                  + "\nOwner: "
                  + country.getArmy().getOwner()
                  + "\nArmies: "
                  + country.getArmy().getTroopCount());
        });

    // Initial update of the country's view
    update();
  }

  /**
   * Called when the observed country changes. Updates the country's visual representation on the
   * game board.
   */
  @Override
  public void update() {
    // Update the view with the current state of the country.
    view.render(
        country.getArmy().getTroopCount(), // The number of armies in the country.
        country.getArmy().getOwner().getColor(), // The color of the owner (representing the player).
        country.getX(), // The relative X coordinate of the country on the board.
        country.getY()); // The relative Y coordinate of the country on the board.
  }

  /**
   * Gets the view associated with this country.
   *
   * @return The CountryView that represents the visual aspect of this country.
   */
  public CountryView getView() {
    return view;
  }
}
