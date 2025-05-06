package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.View.PopUp;
import java.util.HashMap;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class RiskPage extends HBox {
  RiskBoardView board;
  RiskSidePanelView sidePanel;

  public RiskPage(Risk risk) {
    super(new HBox());
    this.board = new RiskBoardView(risk.getBoard().getCountries());
    this.sidePanel = new RiskSidePanelView(risk);
    updateViews(risk.getBoard().getCountries());
    Button saveButton = new Button("S\nA\nV\nE");
    saveButton.getStyleClass().add("fantasy-button");

    saveButton.setOnAction(e -> {
      risk.saveGame();
      PopUp.showInfo("Game saved", "Game has been saved as " + risk.getGameName());
    });
    getChildren().addAll(sidePanel, board, saveButton);
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
