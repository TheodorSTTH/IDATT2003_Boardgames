package edu.ntnu.irr.bidata.View;

import edu.ntnu.irr.bidata.Model.Risk.Country;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RiskBoardView extends Pane {
  public RiskBoardView(HashMap<String, Country> countries) {
    renderBoard(countries);
  }
  public void renderBoard(HashMap<String, Country> countries) {
    getChildren().clear();
    try {
      // https://commons.wikimedia.org/wiki/File:Risk_board.svg
      Image riskBoardImage = new Image(getClass().getResourceAsStream("/risk_board.png"));
      ImageView imageView = new ImageView(riskBoardImage);
      imageView.setFitWidth(750);
      imageView.setFitHeight(520);
      getChildren().add(imageView);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
