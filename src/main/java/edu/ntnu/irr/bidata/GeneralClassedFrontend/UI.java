package edu.ntnu.irr.bidata.GeneralClassedFrontend;

import edu.ntnu.irr.bidata.GeneralClassedFrontend.ChoosAmountOfPlayers.AmountOfPlayersPage;
import edu.ntnu.irr.bidata.GeneralClassedFrontend.CreatePlayer.CreatePlayerPage;

public class UI {
  private static final CreatePlayerPage createPlayer = new CreatePlayerPage();
  private static final AmountOfPlayersPage AmountOfPlayers = new AmountOfPlayersPage();

  public UI() {

  }

  public static void AmountOfPlayersChoosen(int plyers) {
    toCreatePlayerPage();
  }
  

  public static void toAmountOfPlayersPage() {
    System.out.println("Switching to AmountOfPlayersPage...");
    try {
      MyWindow.getPrimaryStage().hide();
      MyWindow.getPrimaryStage().setMaximized(false);
      MyWindow.getPrimaryStage().setScene(AmountOfPlayers);
      MyWindow.getPrimaryStage().setMaximized(true);
      MyWindow.getPrimaryStage().show();
  } catch (Exception e) {
      e.printStackTrace();
  }        
}

private static void toCreatePlayerPage() {
    System.out.println("Switching to CreatePlayerPage...");
    MyWindow.getPrimaryStage().hide();
    MyWindow.getPrimaryStage().setMaximized(false);
    MyWindow.getPrimaryStage().setScene(createPlayer);
    MyWindow.getPrimaryStage().setMaximized(true);
    MyWindow.getPrimaryStage().show();
  }
  
}
