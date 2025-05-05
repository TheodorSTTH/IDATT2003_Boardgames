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
    if (getClass().getResource("/style.css") != null) {
      this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    } else {
      System.err.println("Warning: style.css not found!");
    }
    this.board = new RiskBoardView(risk.getBoard().getCountries());
    this.sidePanel = new RiskSidePanelView(risk);
    updateViews(risk.getBoard().getCountries());
    Button saveButton = new Button("Save current game");
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
