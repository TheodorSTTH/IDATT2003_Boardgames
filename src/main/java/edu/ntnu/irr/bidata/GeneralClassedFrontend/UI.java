package edu.ntnu.irr.bidata.GeneralClassedFrontend;

import edu.ntnu.irr.bidata.GeneralClassedFrontend.ChoosAmoutOfPlayers.AmoutOfPlayersPage;
import edu.ntnu.irr.bidata.GeneralClassedFrontend.CreatePlayer.CreatePlayerPage;

public class UI {
  private static final CreatePlayerPage createPlayer = new CreatePlayerPage();
  private static final AmoutOfPlayersPage AmoutOfPlayers = new AmoutOfPlayersPage();

  public UI() {

  }

  public static void AmountOfPlayersChoosen(int plyers){

  }

  public static int askForAmountOfPlayers() {
    toAmountOfPlayersPage();
    int players = 0;
    while (players == 0) {
      
    }
    
    return players;
  }
  
  public static String askForPlayerName() {
    return "hei";
  }

  public static void toAmountOfPlayersPage() {
    MyWindow.getPrimaryStage().hide();
    MyWindow.getPrimaryStage().setMaximized(false);
    MyWindow.getPrimaryStage().setScene(AmoutOfPlayers);
    MyWindow.getPrimaryStage().setMaximized(true);
    MyWindow.getPrimaryStage().show();
  }

  private static void toCreatePlayerPage() {
    MyWindow.getPrimaryStage().hide();
    MyWindow.getPrimaryStage().setMaximized(false);
    MyWindow.getPrimaryStage().setScene(createPlayer);
    MyWindow.getPrimaryStage().setMaximized(true);
    MyWindow.getPrimaryStage().show();
  }


  
}
