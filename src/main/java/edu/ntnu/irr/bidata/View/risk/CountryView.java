package edu.ntnu.irr.bidata.view.risk;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * CountryView represents the visual representation of a country on the game board. It displays the
 * number of troops, an icon indicating the presence of troops (a sword), and the owner's color.
 * This view extends the Button class, allowing it to be interactive and part of the game's UI.
 */
public class CountryView extends Button {

  // Label showing the number of troops in this country
  private Label text = new Label();

  // The width and height of the board for positioning
  private final double boardWidth;
  private final double boardHeight;

  // HBox container for aligning content (number of troops and sword icon)
  private HBox content = new HBox(5);

  /**
   * Constructs a new CountryView for a given board size. The view will later be rendered with troop
   * count, owner color, and position.
   *
   * @param boardWidth the width of the game board
   * @param boardHeight the height of the game board
   */
  public CountryView(double boardWidth, double boardHeight) {
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
  }

  /**
   * Renders the country view with the specified properties: - The number of troops in the country -
   * The owner's color for the country - The position of the country on the game board (relative X
   * and Y) It also updates the display to show a sword icon representing the presence of troops.
   *
   * @param amountOfTroops the number of troops in this country
   * @param ownerColor the color of the owner of the country
   * @param relativeX the relative X position of the country on the board (0 to 1 scale)
   * @param relativeY the relative Y position of the country on the board (0 to 1 scale)
   */
  public void render(
      Integer amountOfTroops, String ownerColor, double relativeX, double relativeY) {

    // Set the number of troops as text in the label
    text.setText(Integer.toString(amountOfTroops));
    text.getStyleClass().add("country-label");

    // Create a sword icon (representing the presence of troops)
    ImageView swordIcon =
        new ImageView(new Image(getClass().getResource("/images/risk/sword.png").toExternalForm()));
    swordIcon.setFitHeight(12); // Set the size of the sword icon
    swordIcon.setPreserveRatio(true); // Maintain aspect ratio
    swordIcon.setSmooth(true); // Enable smooth rendering for the icon

    // Add the text and sword icon to the content container (HBox)
    content.getChildren().setAll(text, swordIcon);
    content.setAlignment(Pos.CENTER_RIGHT); // Align content to the right
    this.setGraphic(content); // Set the content as the graphic for the button

    // Set the style (background color) of the country view based on the owner's color
    this.getStyleClass().add("country-view");
    this.setStyle("-fx-background-color: " + ownerColor + "; ");

    // Position the country on the board based on the relative X and Y positions
    setLayoutX(boardWidth * relativeX);
    setLayoutY(boardHeight * relativeY);
  }
}
