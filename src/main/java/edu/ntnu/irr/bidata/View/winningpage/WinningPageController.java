package edu.ntnu.irr.bidata.view.WinPage;

import edu.ntnu.irr.bidata.controller.MyWindow;
import edu.ntnu.irr.bidata.controller.NavigationManager;
import edu.ntnu.irr.bidata.view.StartPage.StartPageController;

public class WinningPageController {
  private final WinningPageView view;

  public WinningPageController(String winner, String winPageClass) {
    this.view = new WinningPageView(winner, winPageClass);
    view.getBackToMenuButton().setOnAction(e -> {
      NavigationManager.navigate(new StartPageController().getView());
    });
    view.getExitButton().setOnAction(e -> {
      MyWindow.closeApplication();
    });
  }

  public WinningPageView getView() {
    return view;
  }
}
