package edu.ntnu.irr.bidata.View.RiskWin;

import edu.ntnu.irr.bidata.Controler.MyWindow;
import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.View.StartPage.StartPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RiskWinningPage extends VBox {
  public RiskWinningPage(String winner) {
    getStyleClass().add("risk-win-page");
    setAlignment(Pos.TOP_RIGHT);
    Label winLabel = new Label("You win " + winner + "!");
    VBox.setMargin(winLabel, new Insets(10, 20, 10, 20));
    winLabel.getStyleClass().add("fantasy-title");


    Button backToMenuButton = new Button("Back to the start page");
    backToMenuButton.getStyleClass().addAll("fantasy-button");
    VBox.setMargin(backToMenuButton, new Insets(25, 25, 25, 25));
    backToMenuButton.setOnAction(e -> {
      NavigationManager.navigate(new StartPage());
    });
      

    Button exitButton = new Button("Leave Runeborne");
    exitButton.getStyleClass().add("fantasy-button");
    VBox.setMargin(exitButton, new Insets(25, 25, 25, 25));
    exitButton.setOnAction(e -> {
      MyWindow.closeApplication();
    });
    

    getChildren().addAll(winLabel, backToMenuButton, exitButton);
  }
}
