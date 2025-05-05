package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.Player;
import java.util.ArrayList;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class CanvasTileView {
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private final Color color;
  private final int tileNumber;
  private final GraphicsContext graphicsContext;
  private final ArrayList<Player> players;

  /**
   * Constructs object with values passed and renders it.
   * */
  public CanvasTileView(int x, int y, int width, int height, Color color, int tileNumber, GraphicsContext graphicsContext) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
    this.tileNumber = tileNumber;
    this.graphicsContext = graphicsContext;
    this.players = new ArrayList<>();
  }

  /**
   * Draws the tile box.
   * */
  public void drawBox() {
    graphicsContext.setFill(color);
    graphicsContext.fillRect(x, y, width, height);
  }

  /**
   * Draws the number of the tile on the tile.
   * */
  public void drawNumber() {
    int fontSize = (int) Math.ceil(height * 0.3);
    int padding = (int) Math.ceil(height * 0.05);
    graphicsContext.setTextAlign(TextAlignment.LEFT);
    graphicsContext.setTextBaseline(VPos.TOP);
    graphicsContext.setFont(Font.font(fontSize));
    graphicsContext.setFill(color.darker());
    graphicsContext.fillText(Integer.toString(tileNumber), x + padding, y + padding);
  }

  /**
   * Draws all players currently on tile.
   * */
  public void drawPlayers() {
    if (players.isEmpty()) {
      return; // Nothing to draw
    }

    // --- Configuration for Player Circles ---
    final int maxPlayersPerRow = 3; // How many players fit horizontally
    final double playerAreaWidth = width * 0.8;  // Use 80% of tile width for players
    final double playerAreaHeight = height * 0.6; // Use 60% of tile height (leaving space for number)
    final double playerAreaX = x + (width * 0.1); // Center the player area horizontally
    final double playerAreaY = y + (height * 0.3); // Place player area below the number

    // Calculate radius based on available space and max players per row
    double radius = Math.min(playerAreaWidth / maxPlayersPerRow, playerAreaHeight / Math.ceil((double) players.size() / maxPlayersPerRow)) / 2;

    // --- Draw each player ---
    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      Color playerColor = Color.valueOf(player.getColor()); // Assuming Player has getColor()

      // Calculate position within the player area grid
      int row = i / maxPlayersPerRow;
      int col = i % maxPlayersPerRow;

      double centerX = playerAreaX + (col + 0.5) * (playerAreaWidth / maxPlayersPerRow);
      double centerY = playerAreaY + (row + 0.5) * (playerAreaHeight / Math.ceil((double) players.size() / maxPlayersPerRow));

      // Draw the circle
      graphicsContext.setFill(playerColor);
      graphicsContext.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

      // Optional: Draw a border around the player circle for contrast
      graphicsContext.setStroke(Color.BLACK);
      graphicsContext.setLineWidth(1.0);
      graphicsContext.strokeOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
    }
  }

  /**
   * Adds a player to tile view.
   * */
  public void addPlayer(Player player) {
    this.players.add(player);
  }

  public int getCenterX() {
    return x + width / 2;
  }

  public int getCenterY() {
    return y + height / 2;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
