package edu.ntnu.irr.bidata.Wiew.LadderGameOverview;

import edu.ntnu.irr.bidata.Model.Player;
import edu.ntnu.irr.bidata.Wiew.Tile;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class OverviewPage extends Scene {
  private static final OverviewLayout layout = new OverviewLayout();

  public OverviewPage() {
    super(layout);

    if (getClass().getResource("/style.css") != null) {
      this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    } else {
      System.err.println("Warning: style.css not found!");
    }
  }
  public OverviewLayout getLayout() {
    return layout;
  }
}