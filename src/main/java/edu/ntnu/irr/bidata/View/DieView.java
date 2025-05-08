package edu.ntnu.irr.bidata.view;

import edu.ntnu.irr.bidata.model.interfaces.observer.Observer;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Responsible for displaying a die face from 1 to 6. It observes a value (from 1 to 6) and updates
 * the visible dots accordingly.
 */
public class DieView extends Pane implements Observer<Integer> {

  // Circle nodes representing die pips (dots)
  private final Circle center;
  private final Circle topRight;
  private final Circle topLeft;
  private final Circle centerRight;
  private final Circle centerLeft;
  private final Circle bottomRight;
  private final Circle bottomLeft;

  /**
   * Constructs a die view with a given size and colors.
   *
   * @param size the size of the die (width and height in pixels)
   * @param dieColor the background color of the die
   * @param dotColor the color of the dots/pips
   */
  public DieView(int size, Color dieColor, Color dotColor) {
    // Calculate positions and sizes based on provided size
    int padding = (int) Math.floor(size * 0.225);

    int rightX = size - padding;
    int centerX = size / 2;
    int topY = padding;
    int bottomY = size - padding;
    int leftX = padding;
    int centerY = size / 2;
    int radius = size / 10;

    // Initialize each dot (Circle)
    center = new Circle(centerX, centerY, radius, dotColor);
    topLeft = new Circle(leftX, topY, radius, dotColor);
    topRight = new Circle(rightX, topY, radius, dotColor);
    bottomLeft = new Circle(leftX, bottomY, radius, dotColor);
    bottomRight = new Circle(rightX, bottomY, radius, dotColor);
    centerLeft = new Circle(leftX, centerY, radius, dotColor);
    centerRight = new Circle(rightX, centerY, radius, dotColor);


    // Set die background and size
    this.setMaxSize(size, size);
    this.setPrefSize(size, size);
    this.setBackground(new Background(new BackgroundFill(dieColor, new CornerRadii(10), null)));

    // Add all pips to the Pane
    getChildren()
        .addAll(center, topLeft, topRight, centerLeft, centerRight, bottomLeft, bottomRight);

    // Hide all dots initially
    center.setVisible(false);
    setVisible(false);
  }

  /** Hides all dots from the die face. */
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
   * Updates the die to display the given number of dots (1-6). If the value is not valid (outside
   * 1-6), an error is printed.
   *
   * @param numberOfDots the number of dots to display (expected between 1 and 6)
   */
  @Override
  public void update(Integer numberOfDots) {
    hideAllDots(); // Clear previous die face
    setVisible(true); // Show die

    // Show appropriate dots based on number
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
        // Error handling for unexpected input
        System.err.println("Number of dots is " + numberOfDots + ". Max number is 6");
        break;
    }
  }
}
