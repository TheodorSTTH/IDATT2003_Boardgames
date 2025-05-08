package edu.ntnu.irr.bidata.view.snakesandladders;

import edu.ntnu.irr.bidata.controller.MyWindow;
import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import edu.ntnu.irr.bidata.model.interfaces.observer.SimpleObserver;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Is responsible for showing a die.
 * */
public class DieView extends Pane implements Observer<Integer> {
  private static final Logger log = LoggerFactory.getLogger(DieView.class);

  private final Circle center;
  private final Circle topRight;
  private final Circle topLeft;
  private final Circle centerRight;
  private final Circle centerLeft;
  private final Circle bottomRight;
  private final Circle bottomLeft;

  public DieView(int size, Color dieColor, Color dotColor) {
    int padding = (int) Math.floor(size * 0.225);
    int topY = padding;
    int leftX = padding;
    int rightX = size - padding;
    int centerX = size / 2;
    int centerY = size / 2;
    int bottomY = size - padding;
    int radius = size / 10;

    center = new Circle(centerX, centerY, radius, dotColor);
    topLeft = new Circle(leftX, topY, radius, dotColor);
    topRight = new Circle(rightX, topY, radius, dotColor);
    centerLeft = new Circle(leftX, centerY, radius, dotColor);
    centerRight = new Circle(rightX, centerY, radius, dotColor);
    bottomLeft = new Circle(leftX, bottomY, radius, dotColor);
    bottomRight = new Circle(rightX, bottomY, radius, dotColor);

    this.setMaxSize(size, size);
    this.setPrefSize(size, size);
    this.setBackground(new Background(
        new BackgroundFill(dieColor,
            new CornerRadii(10),
            null))
    );
    getChildren().addAll(center, topLeft, topRight, centerLeft, centerRight, bottomLeft, bottomRight);
    center.setVisible(false);
    setVisible(false);
  }

  private void hideAllDots() {
    center.setVisible(false);
    topLeft.setVisible(false);
    topRight.setVisible(false);
    centerLeft.setVisible(false);
    centerRight.setVisible(false);
    bottomLeft.setVisible(false);
    bottomRight.setVisible(false);
  }

  /**
   * Displays the die roll. If an invalid number is given then nothing is displayed.
   *
   * @param numberOfDots should be between 1 and 6.
   * */
  public void update(Integer numberOfDots) {
    hideAllDots();
    setVisible(true);
    switch (numberOfDots) {
      case 1:
        center.setVisible(true);
        break;
      case 2:
        bottomLeft.setVisible(true);
        topRight.setVisible(true);
        break;
      case 3:
        bottomLeft.setVisible(true);
        center.setVisible(true);
        topRight.setVisible(true);
        break;
      case 4:
        topLeft.setVisible(true);
        bottomLeft.setVisible(true);
        bottomRight.setVisible(true);
        topRight.setVisible(true);
        break;
      case 5:
        topLeft.setVisible(true);
        bottomLeft.setVisible(true);
        bottomRight.setVisible(true);
        topRight.setVisible(true);
        center.setVisible(true);
        break;
      case 6:
        topLeft.setVisible(true);
        bottomLeft.setVisible(true);
        bottomRight.setVisible(true);
        topRight.setVisible(true);
        centerLeft.setVisible(true);
        centerRight.setVisible(true);
        break;
      default:
        log.warn("Number of dots ({}) is above 6 or below 1. Displaying no dots", numberOfDots);
        break;
    }
  }
}
