package edu.ntnu.irr.bidata.View.WinPage;

import edu.ntnu.irr.bidata.Controler.MyWindow;
import edu.ntnu.irr.bidata.Controler.NavigationManager;
import edu.ntnu.irr.bidata.View.CreatePlayer.CreatePlayerPageView;
import edu.ntnu.irr.bidata.View.StartPage.StartPageController;

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
