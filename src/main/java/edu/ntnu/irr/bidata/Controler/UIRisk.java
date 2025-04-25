package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Wiew.PopUp;
import edu.ntnu.irr.bidata.Model.Risk.Country;
import java.util.HashMap;
import java.util.List;



public class UIRisk {
  private static Risk risk;

  public static void setRisk(Risk risk) {
    UIRisk.risk = risk;
  }

  public static void toRiskGamePage(HashMap<String, Country> countrys) {
  }

  public static void updateRiskGamePage(HashMap<String, Country> countrys) {
  }

  public static void openPlaceTropesMenu(int tropes, List<Country> countryNames) {
  }
  
  public static void openAttackMenu(HashMap<Country, List<Country>> attackOptions) {
  }

  public static void updateAttackMenu(HashMap<Country, List<Country>> attackOptions) {
  }

  public static void toTransferMenu() {
  }
  
  public static void placeTropes(String Contery, int tropesPlased) {
    if (!risk.placeTropes(Contery, tropesPlased)) {
      PopUp.showInfo("To few trops", "You can not place more tropes than you have available");
    }
  }
  
  public static void attackOnce(String attacker, String defender) {
    risk.attackOnce(attacker, defender);
  }

  public static void attackUntilWin(String attacker, String defender) {
    risk.attackUntilWin(attacker, defender);
  }

  public static void transferTroops(String fromCountry, String toCountry, int troops) {
    if (risk.transferTroops(fromCountry, toCountry, troops)){
      risk.endTurn();
    }

  }
}
