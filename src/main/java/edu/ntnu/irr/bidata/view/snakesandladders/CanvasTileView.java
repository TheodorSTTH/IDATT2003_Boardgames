package edu.ntnu.irr.bidata.view.snakesandladders;

import edu.ntnu.irr.bidata.model.Player;
import edu.ntnu.irr.bidata.view.PopUp;
import java.util.ArrayList;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The CanvasTileView class is responsible for rendering and managing individual tiles on the Snakes
 * and Ladders board, including drawing the tile, the tile number, and any players that may be on
 * the tile.
 */
public class CanvasTileView {
  private static final Logger log = LoggerFactory.getLogger(CanvasTileView.class);
  private final int xcoordinate; // X-coordinate of the top-left corner of the tile
  private final int ycoordinate; // Y-coordinate of the top-left corner of the tile
  private final int width; // Width of the tile
  private final int height; // Height of the tile
  private final int tileNumber; // Unique identifier for the tile
  private final GraphicsContext graphicsContext; // Graphics context for drawing on the canvas
  private final ArrayList<Player> players; // List of players currently on the tile

  /**
   * Constructs a CanvasTileView object and renders it on the canvas.
   *
   * @param x the X-coordinate of the top-left corner of the tile
   * @param y the Y-coordinate of the top-left corner of the tile
   * @param width the width of the tile
   * @param height the height of the tile
   * @param tileNumber the unique number representing the tile
   * @param graphicsContext the GraphicsContext used for rendering on the canvas
   */
  public CanvasTileView(
      int x, int y, int width, int height, int tileNumber, GraphicsContext graphicsContext) {
    this.xcoordinate = x;
    this.ycoordinate = y;
    this.width = width;
    this.height = height;
    this.tileNumber = tileNumber;
    this.graphicsContext = graphicsContext;
    this.players = new ArrayList<>();
  }

  /**
   * Draws the tile box on the canvas. The tile image alternates based on whether the tile number is
   * even or odd.
   */
  public void drawBox() {
    Image tileImage;
    if (tileNumber % 2 == 0) {
      tileImage = new Image(getClass().getResourceAsStream("/images/snakesandladders/grass.png"));
    } else {
      tileImage =
          new Image(getClass().getResourceAsStream("/images/snakesandladders/grass_dark.png"));
    }
    // Draw the tile image at the specified position and size
    graphicsContext.drawImage(tileImage, xcoordinate, ycoordinate, width, height);
  }

  /**
   * Draws the tile number on the tile. The number is centered with padding from the top-left
   * corner.
   */
  public void drawNumber() {
    int fontSize = (int) Math.ceil(height * 0.3); // Font size based on tile height
    int padding =
        (int) Math.ceil(height * 0.05); // Padding to keep the number from touching the edge
    graphicsContext.setTextAlign(TextAlignment.LEFT); // Set text alignment for number drawing
    graphicsContext.setTextBaseline(VPos.TOP); // Align text to the top of the font
    graphicsContext.setFont(
        Font.font("Papyrus", FontWeight.BLACK, fontSize)); // Set font for the number
    graphicsContext.setFill(
        Paint.valueOf("rgba(255, 255, 255, 0.8)")); // Set the fill color for the number
    graphicsContext.fillText(
        Integer.toString(tileNumber),
        xcoordinate + padding,
        ycoordinate + padding); // Draw the number
  }

  /**
   * Draws all players currently on the tile. Players are drawn as circles within the available
   * area.
   */
  public void drawPlayers() {
    if (players.isEmpty()) {
      return; // No players to draw
    }

    // --- Configuration for Player Circles ---
    final int maxPlayersPerRow = 3; // Max number of players that can fit horizontally
    final double playerAreaWidth = width * 0.8; // Area width for players (80% of the tile width)
    final double playerAreaHeight =
        height * 0.6; // Area height for players (60% of the tile height)
    final double playerAreaX =
        xcoordinate + (width * 0.1); // Horizontal offset to center the player area
    final double playerAreaY =
        ycoordinate + (height * 0.3); // Vertical offset to place players below the number

    // Calculate size of each player based on available space and number of players
    double size =
        Math.min(
                playerAreaWidth / maxPlayersPerRow,
                playerAreaHeight / Math.ceil((double) players.size() / maxPlayersPerRow))
            + 7;

    // --- Draw each player ---
    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      Image playerImage = null;

      // Load player image based on player color
      switch (player.getColor().toLowerCase()) {
        case "red":
          playerImage =
              new Image(
                  getClass()
                      .getResourceAsStream("/images/snakesandladders/figures/wizard_red.png"));
          break;
        case "yellow":
          playerImage =
              new Image(
                  getClass()
                      .getResourceAsStream("/images/snakesandladders/figures/wizard_yellow.png"));
          break;
        case "blue":
          playerImage =
              new Image(
                  getClass()
                      .getResourceAsStream("/images/snakesandladders/figures/wizard_blue.png"));
          break;
        case "green":
          playerImage =
              new Image(
                  getClass()
                      .getResourceAsStream("/images/snakesandladders/figures/wizard_green.png"));
          break;
        case "white":
          playerImage =
              new Image(
                  getClass()
                      .getResourceAsStream("/images/snakesandladders/figures/wizard_white.png"));
          break;
        default:
          log.error("Player color not defined.");
          PopUp.showError(
              "Player image not defined.", "The color " + player.getColor() + " was not found.");
          System.exit(0);
      }

      // Calculate player position in a grid within the player area
      int row = i / maxPlayersPerRow;
      int col = i % maxPlayersPerRow;

      double centerX = playerAreaX + (col + 0.5) * (playerAreaWidth / maxPlayersPerRow);
      double centerY =
          playerAreaY
              + (row + 0.5)
                  * (playerAreaHeight / Math.ceil((double) players.size() / maxPlayersPerRow));

      // Draw the player image at the calculated position and size
      graphicsContext.drawImage(
          playerImage, centerX - size / 2, centerY - size / 2 - 10, size * 2, size * 2);
    }
  }

  /**
   * Adds a player to the tile. This player will be drawn when calling the drawPlayers method.
   *
   * @param player the player to be added to the tile
   */
  public void addPlayer(Player player) {
    this.players.add(player);
  }

  // Getter methods for tile coordinates and dimensions

  public int getCenterX() {
    return xcoordinate + width / 2; // Returns the center X-coordinate of the tile
  }

  public int getCenterY() {
    return ycoordinate + height / 2; // Returns the center Y-coordinate of the tile
  }

  public int getX() {
    return xcoordinate; // Returns the X-coordinate of the top-left corner
  }

  public int getY() {
    return ycoordinate; // Returns the Y-coordinate of the top-left corner
  }

  public int getWidth() {
    return width; // Returns the width of the tile
  }

  public int getHeight() {
    return height; // Returns the height of the tile
  }
}
