package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.View.StartPage.StartPage;
import edu.ntnu.irr.bidata.model.Risk.Country;
import edu.ntnu.irr.bidata.model.Risk.Risk;
import javafx.scene.control.Label;
import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;



public class RiskPage extends HBox {
  RiskBoardView board;
  RiskSidePanelView sidePanel;

  public RiskPage(Risk risk) {
    super(new HBox());
    this.board = new RiskBoardView(risk.getBoard().getCountries());
    this.sidePanel = new RiskSidePanelView(risk);
    this.setStyle("-fx-background-color:rgb(72, 163, 255);");
    updateViews(risk.getBoard().getCountries());


    Label bonusesLabel = new Label("Euorupe: 5   Asia: 7   North America: 5\nSouth America: 2   Africa: 3   Australia: 2");
    bonusesLabel.getStyleClass().add("fantasy-text-sidbar");


    Button saveButton = new Button("SAVE");
    saveButton.getStyleClass().add("fantasy-button");

    Button exitGameButton = new Button("Exit game");
    exitGameButton.getStyleClass().addAll("fantasy-button");
    exitGameButton.setOnAction(e -> {
      NavigationManager.navigate(new StartPage());
      });


    HBox underlay = new HBox(bonusesLabel, saveButton, exitGameButton);
    underlay.setAlignment(Pos.CENTER);
    underlay.setSpacing(15);


    VBox mainStage = new VBox(10);
    mainStage.getChildren().addAll(board, underlay);

    saveButton.setOnAction(e -> {
      risk.saveGame();
    });
    getChildren().addAll(sidePanel, mainStage);
  }

  /**
   * Is responsible for updating views on the page like the board and
   * side panel.
   *
   * @param countries is a hashmap which links a specific country name to a country object
   * */
  public void updateViews(HashMap<String, Country> countries) {
  }

  public RiskSidePanelView getSidePanelView() {
    return sidePanel;
  }
}
