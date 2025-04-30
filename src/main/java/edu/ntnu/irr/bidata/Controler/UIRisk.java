package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.View.LadderGameOverview.OverviewPage;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.View.RiskGame.RiskPage;
import java.util.HashMap;
import java.util.List;



public class UIRisk extends UI {
  private static Risk risk;

  public static void setRisk(Risk risk) {
    UIRisk.risk = risk;
  }

  public static void updateRiskGamePage(HashMap<String, Country> countries) {
  }

  public static void openPlaceTroopsMenu(int troops, List<Country> countryNames) {
  }

  public void startSavedGame() { // TODO: Fix, doesn't work
    UIRisk.setRisk(risk);
    NavigationManager.switchScene(new RiskPage(risk.getBoard().getCountries()));
  }
  
  public static void openAttackMenu(HashMap<Country, List<Country>> attackOptions) {
  }

  public static void updateAttackMenu(HashMap<Country, List<Country>> attackOptions) {
  }

  public static void toTransferTroopsMenu() {
  }
  
  public static void placeTropes(String country, int troopsPlaced) {
    if (!risk.placeTropes(country, troopsPlaced)) {
      PopUp.showInfo("Too few troops", "You can not place more tropes than you have available");
    }
  }
  
  public static void attackOnce(String attacker, String defender) {
    risk.attackOnce(attacker, defender);
  }

  public static void attackUntilResult(String attacker, String defender) {
    risk.attackUntilResolt(attacker, defender);
  }

  public static void transferTroops(String fromCountry, String toCountry, int troops) {
    if (risk.transferTroops(fromCountry, toCountry, troops)){
      risk.endTurn();
    }
  }
}
