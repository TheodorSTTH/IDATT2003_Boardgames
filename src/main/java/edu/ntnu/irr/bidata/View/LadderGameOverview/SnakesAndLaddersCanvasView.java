package edu.ntnu.irr.bidata.View.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.LadderGame.Event.Event;
import edu.ntnu.irr.bidata.Model.LadderGame.Event.LadderEvent;
import edu.ntnu.irr.bidata.Model.LadderGame.Event.QizzEvent;
import edu.ntnu.irr.bidata.Model.LadderGame.LaderGame;
import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Model.interfaces.observer.IObserver;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class SnakesAndLaddersCanvasView extends Canvas implements IObserver<LaderGame> {

  private final int boardRowsAmount = 10;
  private final int boardColumnAmount = 9;
  private final int boardWidth = 600;
  private final int boardHeight = 600;

  private final ArrayList<CanvasTileView> tileViews = new ArrayList<>(boardColumnAmount * boardRowsAmount);

  public SnakesAndLaddersCanvasView(LaderGame snakesAndLadders) {
    for (int i = 0; i < 90; i++) {
      tileViews.add(null);
    }
    this.setWidth(boardWidth);
    this.setHeight(boardHeight);
    update(snakesAndLadders);
    snakesAndLadders.registerObserver(this);
  }

  private CanvasTileView getTile(int index) {
    return tileViews.get(index);
  }

  public void renderBoard(LaderGame snakesAndLadders) {
    GraphicsContext gc = getGraphicsContext2D();
    for (int row = 0; row < boardRowsAmount; row++) {
      for (int col = 1; col < boardColumnAmount + 1; col++) {
        final int tileNumber = row*9 + col;
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
        Color color = tileNumber % 2 == 0 ? Color.valueOf("#CECECE") : Color.valueOf("#EAE9E1");
        CanvasTileView tileView = new CanvasTileView(x, y, width, height, color, tileNumber, gc);
        tileView.drawBox();
        tileView.drawNumber();
        tileViews.set(tileNumber - 1, tileView);
      }
    }
  }

  private void placePlayers(LaderGame snakesAndLadders) {
    HashMap<Player, Integer> playerPositions = snakesAndLadders.getPlayerPositions();
    for (Player player : playerPositions.keySet()) {
      int playerTileIndex = playerPositions.get(player);
      CanvasTileView playerTileView = getTile(playerTileIndex);
      playerTileView.addPlayer(player);
    }
  }

  private void drawPlayers(LaderGame snakesAndLadders) {
    HashMap<Player, Integer> playerPositions = snakesAndLadders.getPlayerPositions();
    for (Player player : playerPositions.keySet()) {
      int playerTileIndex = playerPositions.get(player);
      CanvasTileView playerTileView = getTile(playerTileIndex);
      playerTileView.drawPlayers();
    }
  }

  private void drawSnakesAndLadders(LaderGame snakesAndLadders) {
    GraphicsContext gc = getGraphicsContext2D();
    HashMap<Integer, Event> events = snakesAndLadders.getBoard().getEvents();
    events.keySet().forEach(tileIndex -> {
      if (events.get(tileIndex) instanceof LadderEvent event) {
        drawSnakeOrLadder(tileIndex, event.getDestination());
      }
    });
  }

  private void drawQuizEvents(LaderGame snakesAndLadders) {
    GraphicsContext gc = getGraphicsContext2D();
    HashMap<Integer, Event> events = snakesAndLadders.getBoard().getEvents();
    events.keySet().forEach(tileIndex -> {
      if (events.get(tileIndex) instanceof QizzEvent event) {
        drawQuizEvent(event.getTileNumber());
      }
    });
  }

  private void drawQuizEvent(int tileIndex) {
    GraphicsContext gc = getGraphicsContext2D();
    CanvasTileView tileView = tileViews.get(tileIndex - 1);
    gc.setFill(Color.DARKBLUE);
    gc.fillRect(
        tileView.getX(),
        tileView.getY(),
        tileView.getWidth(),
        tileView.getHeight()
    );

    int fontSize = (int) Math.ceil(tileView.getHeight() * 0.5);
    int x = tileView.getCenterX();
    int y = tileView.getCenterY();
    gc.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
    gc.setFill(Color.WHITE);
    gc.setTextAlign(TextAlignment.CENTER);
    gc.setTextBaseline(VPos.CENTER);
    gc.fillText("?", x, y);
  }

  private void drawSnakeOrLadder(int fromIndex, int toIndex) {
    GraphicsContext gc = getGraphicsContext2D();
    CanvasTileView fromTileView = tileViews.get(fromIndex);
    CanvasTileView toTileView = tileViews.get(toIndex);
    int fromCenterX = fromTileView.getCenterX();
    int fromCenterY = fromTileView.getCenterY();
    int toCenterX = toTileView.getCenterX();
    int toCenterY = toTileView.getCenterY();
    int dx = toCenterX - fromCenterX;
    int dy = toCenterY - fromCenterY;
    double angle = Math.atan2(dy, dx);
    double normal = angle - Math.PI / 2;
    double normalVectorX = Math.cos(normal);
    double normalVectorY = Math.sin(normal);
    double length = Math.sqrt(dx * dx + dy * dy);

    if (fromIndex < toIndex) { // We render a ladder
      gc.setStroke(Color.BROWN);
      gc.setLineWidth(3.0);
      gc.setLineCap(StrokeLineCap.SQUARE);

      double offset = 10;

      double fromLeftX = fromCenterX - offset * normalVectorX;
      double fromLeftY = fromCenterY - offset * normalVectorY;
      double fromRightX = fromCenterX + offset * normalVectorX;
      double fromRightY = fromCenterY + offset * normalVectorY;

      double toLeftX = toCenterX - offset * normalVectorX;
      double toLeftY = toCenterY - offset * normalVectorY;
      double toRightX = toCenterX + offset * normalVectorX;
      double toRightY = toCenterY + offset * normalVectorY;

      double distanceBetweenLadderSteps = 20;
      int amountOfSteps = (int) Math.floor(length / distanceBetweenLadderSteps);
      for (int i = 1; i < amountOfSteps; i++) {
        double t = i / (double) amountOfSteps; // curve param in range 0 to 1

        double stepLeftX = fromLeftX + t * dx;
        double stepLeftY = fromLeftY + t * dy;

        double stepRightX = fromRightX + t * dx;
        double stepRightY = fromRightY + t * dy;

        gc.strokeLine(stepLeftX, stepLeftY, stepRightX, stepRightY);
      }

      gc.strokeLine(fromLeftX, fromLeftY, toLeftX, toLeftY);
      gc.strokeLine(fromRightX, fromRightY, toRightX, toRightY);
    } else { // draw snake
      gc.setStroke(Color.GREEN);
      gc.setLineWidth(5.0);
      gc.setLineCap(StrokeLineCap.ROUND);

      int amountOfSteps = 300;
      double amplitude = 10;

      gc.beginPath();
      gc.moveTo(fromCenterX, fromCenterY);

      for (int i = 1; i < amountOfSteps; i++) {
        double t = i / (double) amountOfSteps; // curve param in range 0 to 1

        double distanceTraveled = t * length;
        double pixelsPerWiggle = 60;
        double curveAngle = (distanceTraveled / pixelsPerWiggle) * (2 * Math.PI);
        double offset = amplitude * Math.sin(curveAngle);
        double perpX = -dy / length;
        double perpY = dx / length;
        double x = fromCenterX + dx * t + offset * perpX;
        double y = fromCenterY + dy * t + offset * perpY;

        gc.lineTo(x, y);
      }

      gc.stroke();
    }
  }

  /**
   * Re-renders the current board and places players on the board. Effectively updating
   * the board for the user.
   *
   * @param snakesAndLadders is the current snakes and ladders game object.
   * */
  @Override
  public void update(LaderGame snakesAndLadders) {
    renderBoard(snakesAndLadders);
    //renderSnakes(snakesAndLadders);
    //renderLadders(snakesAndLadders);
    drawQuizEvents(snakesAndLadders);
    drawSnakesAndLadders(snakesAndLadders);
    placePlayers(snakesAndLadders);
    drawPlayers(snakesAndLadders);
  }
}
