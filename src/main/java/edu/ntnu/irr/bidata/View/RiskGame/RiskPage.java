package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Controler.UI;
import edu.ntnu.irr.bidata.Model.Risk.Country;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class RiskPage extends Scene {
  RiskBoardView board;
  RiskSidePanelView sidePanel;

  public RiskPage(HashMap<String, Country> countries) {
    super(new HBox());
    if (getClass().getResource("/style.css") != null) {
      this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    } else {
      System.err.println("Warning: style.css not found!");
    }
    this.board = new RiskBoardView(countries);
    this.sidePanel = new RiskSidePanelView();
    updateViews(countries);
    Button saveButton = new Button("Save current game");
    saveButton.setOnAction(e -> {
      UI.saveGame();
    });
    HBox root = (HBox) this.getRoot();
    root.getChildren().addAll(sidePanel, board, saveButton);
  }

  /**
   * Is responsible for updating views on the page like the board and
   * side panel.
   *
   * @param countries is a hashmap which links a specific country name to a country object
   * */
  public void updateViews(HashMap<String, Country> countries) {
    sidePanel.render();
  }
}
