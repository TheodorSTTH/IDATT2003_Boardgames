package edu.ntnu.irr.bidata.view.snakesandladders;

import edu.ntnu.irr.bidata.model.Player;
import edu.ntnu.irr.bidata.model.snakesandladders.event.Event;
import edu.ntnu.irr.bidata.model.snakesandladders.event.LadderEvent;
import edu.ntnu.irr.bidata.model.snakesandladders.event.QuizEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

/** Is responsible for displaying board with events and players ready for the user to view. */
public class SnakesAndLaddersCanvasView extends Canvas {

  private final int boardRowsAmount = 10;
  private final int boardColumnAmount = 9;
  private final int boardWidth = 500;
  private final int boardHeight = 500;

  private final ArrayList<CanvasTileView> tileViews =
      new ArrayList<>(boardColumnAmount * boardRowsAmount + 1);

  /** Constructs the board view variables. */
  public SnakesAndLaddersCanvasView() {
    for (int i = 0; i < 91; i++) {
      tileViews.add(null);
    }
    this.setWidth(boardWidth);
    this.setHeight(boardHeight);
  }

  private CanvasTileView getTile(int index) {
    return tileViews.get(index);
  }

  /**
   * Renders the underlying board squares. Not including any events or players. Just tile underlying
   * tile and number.
   */
  public void renderBoard() {
    GraphicsContext gc = getGraphicsContext2D();
    for (int row = 0; row < boardRowsAmount; row++) {
      for (int col = 1; col < boardColumnAmount + 1; col++) {
        final int tileNumber = row * 9 + col;
        int width = boardWidth / boardColumnAmount;
        int height = boardHeight / boardRowsAmount;
        int x;
        int y;
        if (row % 2 == 0) {
          x = (col - 1) * width;
        } else {
          x = width * boardColumnAmount - col * width;
        }
        y = height * (boardRowsAmount - 1) - row * height;
        CanvasTileView tileView = new CanvasTileView(x, y, width, height, tileNumber, gc);
        tileView.drawBox();
        tileView.drawNumber();
        tileViews.set(tileNumber, tileView);
      }
    }
  }

  /** Places players on all tile views without rendering them. */
  public void placePlayers(HashMap<Player, Integer> playerPositions) {
    for (Player player : playerPositions.keySet()) {
      int playerTileIndex = playerPositions.get(player);
      if (playerTileIndex == 0 || playerTileIndex > 90) { // player is not on board
        continue;
      }
      CanvasTileView playerTileView = getTile(playerTileIndex);
      playerTileView.addPlayer(player);
    }
  }

  /** Draws all players on board. */
  public void drawPlayers(HashMap<Player, Integer> playerPositions) {
    for (Player player : playerPositions.keySet()) {
      int playerTileIndex = playerPositions.get(player);
      if (playerTileIndex == 0 || playerTileIndex > 90) { // Player is not on board
        continue;
      }
      CanvasTileView playerTileView = getTile(playerTileIndex);
      playerTileView.drawPlayers();
    }
  }

  /** Draws all snakes and ladders on board. */
  public void drawSnakesAndLadders(HashMap<Integer, Event> events) {
    events
        .keySet()
        .forEach(
            tileIndex -> {
              if (events.get(tileIndex) instanceof LadderEvent event) {
                if (tileIndex >= event.getDestination()) {
                  drawSnake(tileIndex, event.getDestination());
                }
              }
            });

    events
        .keySet()
        .forEach(
            tileIndex -> {
              if (events.get(tileIndex) instanceof LadderEvent event) {
                if (tileIndex < event.getDestination()) {
                  drawLadder(tileIndex, event.getDestination());
                }
              }
            });
  }

  /** Draws all quiz boxes on board. */
  public void drawQuizEvents(HashMap<Integer, Event> events) {
    events
        .keySet()
        .forEach(
            tileIndex -> {
              if (events.get(tileIndex) instanceof QuizEvent event) {
                drawQuizEvent(event.getTileNumber());
              }
            });
  }

  private void drawQuizEvent(int tileIndex) {
    GraphicsContext gc = getGraphicsContext2D();
    CanvasTileView tileView = tileViews.get(tileIndex);

    Image quizImage = new Image(getClass().getResourceAsStream("/quiz_tile_transparent.png"));
    gc.drawImage(
        quizImage, tileView.getX(), tileView.getY(), tileView.getWidth(), tileView.getHeight());
  }

  private void drawSnake(int fromIndex, int toIndex) {
    GraphicsContext gc = getGraphicsContext2D();
    CanvasTileView fromTileView = tileViews.get(fromIndex);
    CanvasTileView toTileView = tileViews.get(toIndex);
    gc.save();

    // center position of from tile
    int fromCenterX = fromTileView.getCenterX();
    int fromCenterY = fromTileView.getCenterY();

    // center position of to tile
    int toCenterX = toTileView.getCenterX();
    int toCenterY = toTileView.getCenterY();

    // vector between from-to
    int dx = toCenterX - fromCenterX;
    int dy = toCenterY - fromCenterY;

    // length of non-normalized vector
    double length = Math.sqrt(dx * dx + dy * dy);
    double snakeWidth = 5;
    gc.setStroke(Color.RED);
    gc.setLineWidth(snakeWidth);
    gc.setLineCap(StrokeLineCap.ROUND);

    DropShadow drop = new DropShadow();
    drop.setOffsetX(2);
    drop.setOffsetY(2);
    drop.setRadius(4);
    drop.setColor(Color.color(0, 0, 0, 0.65));

    Light.Distant light = new Light.Distant();
    light.setAzimuth(-45); // direction of the light
    light.setElevation(60); // angle above the surface
    light.setColor(Color.WHITE);

    Lighting lighting = new Lighting();
    lighting.setLight(light);
    lighting.setSurfaceScale(2.0);
    lighting.setDiffuseConstant(1.0);
    lighting.setSpecularConstant(0.5);
    lighting.setSpecularExponent(10);

    lighting.setContentInput(drop);
    gc.setEffect(lighting);

    int amountOfSteps = 300;
    double amplitude = 10;

    gc.beginPath();
    gc.moveTo(fromCenterX, fromCenterY);

    for (int i = 1; i < amountOfSteps; i++) {
      double t = i / (double) amountOfSteps; // curve param in range 0 to 1
      double distanceTraveled = t * length;
      double pixelsPerWiggle = 60;

      // current angle of snake
      double curveAngle = (distanceTraveled / pixelsPerWiggle) * (2 * Math.PI);
      double offset = amplitude * Math.sin(curveAngle); // from center

      double perpX = -dy / length;
      double perpY = dx / length;

      double x = fromCenterX + dx * t + offset * perpX;
      double y = fromCenterY + dy * t + offset * perpY;

      gc.lineTo(x, y);
    }

    gc.stroke();

    double headWidth = snakeWidth * 3;
    double headHeight = snakeWidth * 3;
    gc.setFill(Color.RED);
    gc.fillOval(fromCenterX - headWidth / 2, fromCenterY - headHeight / 2, headWidth, headHeight);

    gc.restore();
  }

  private void drawLadder(int fromIndex, int toIndex) {
    GraphicsContext gc = getGraphicsContext2D();
    gc.save();

    Light.Distant sun = new Light.Distant();
    sun.setAzimuth(-45);
    sun.setElevation(60);
    sun.setColor(Color.WHITE);

    DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.4), 8, 0.1, 3, 3);

    Lighting lighting = new Lighting();
    lighting.setLight(sun);
    lighting.setSurfaceScale(3);
    lighting.setSpecularConstant(0.3);
    lighting.setDiffuseConstant(1.2);
    lighting.setBumpInput(shadow);

    gc.setEffect(lighting);

    CanvasTileView fromTileView = tileViews.get(fromIndex);
    CanvasTileView toTileView = tileViews.get(toIndex);

    // center position of from tile
    int fromCenterX = fromTileView.getCenterX();
    int fromCenterY = fromTileView.getCenterY();

    // center position of to tile
    int toCenterX = toTileView.getCenterX();
    int toCenterY = toTileView.getCenterY();

    // vector between from-to
    int dx = toCenterX - fromCenterX;
    int dy = toCenterY - fromCenterY;

    double offset = 10;

    double angle = Math.atan2(dy, dx); // vector angle
    double normal = angle - Math.PI / 2;

    // normalized vector between from-to
    double normalVectorX = Math.cos(normal);
    double normalVectorY = Math.sin(normal);

    // left ladder pole point from
    double fromLeftX = fromCenterX - offset * normalVectorX;
    double fromLeftY = fromCenterY - offset * normalVectorY;

    // right ladder pole point from
    double fromRightX = fromCenterX + offset * normalVectorX;
    double fromRightY = fromCenterY + offset * normalVectorY;

    // right ladder pole point to
    double toRightX = toCenterX + offset * normalVectorX;
    double toRightY = toCenterY + offset * normalVectorY;

    // left ladder pole point to
    double toLeftX = toCenterX - offset * normalVectorX;
    double toLeftY = toCenterY - offset * normalVectorY;

    gc.setLineCap(StrokeLineCap.SQUARE);

    gc.setLineWidth(8);
    gc.setStroke(Color.SADDLEBROWN.brighter().desaturate());
    gc.strokeLine(fromRightX, fromRightY, toRightX, toRightY);

    // length of non-normalized vector
    double length = Math.sqrt(dx * dx + dy * dy);

    double distanceBetweenLadderSteps = 20;
    int amountOfSteps = (int) Math.floor(length / distanceBetweenLadderSteps);

    gc.setLineWidth(3);
    // draws all steps on the ladder
    gc.setStroke(Color.SADDLEBROWN.brighter().desaturate());
    for (int i = 1; i < amountOfSteps; i++) {
      double t = i / (double) amountOfSteps; // curve param in range 0 to 1

      // left point from
      double stepLeftX = fromLeftX + t * dx;
      double stepLeftY = fromLeftY + t * dy;

      // right point from
      double stepRightX = fromRightX + t * dx;
      double stepRightY = fromRightY + t * dy;

      gc.strokeLine(stepLeftX, stepLeftY, stepRightX, stepRightY);
    }

    // Set here for layering
    gc.setLineWidth(8);
    gc.setStroke(Color.SADDLEBROWN.brighter().desaturate());
    gc.strokeLine(fromLeftX, fromLeftY, toLeftX, toLeftY);

    gc.setEffect(null);
    gc.restore();
  }
}
