package edu.ntnu.irr.bidata.View.LadderGameOverview;

import javafx.scene.Scene;

public class OverviewPage extends Scene {
  private final OverviewLayout layout;
  

  public OverviewPage() {
    super(new OverviewLayout());
    this.layout = (OverviewLayout) getRoot();

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