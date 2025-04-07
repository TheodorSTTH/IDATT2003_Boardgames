package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.Wiew.Message;
import edu.ntnu.irr.bidata.Model.Risk.Country;
import java.util.HashMap;



public class UIRisk {
  private static Risk risk;

  public static void setRisk(Risk risk) {
    UIRisk.risk = risk;
  }

  public static void toRiskGamePage(HashMap<String, Country> countrys) {
  }

  public static void openPlaceTropesMenu(int tropes) {

  }
  
  public static void openAttackMenu() {
  }
  
  public static void placeTropes(String Conteris, int tropesPlased) {
    if (!risk.placeTropes(Conteris, tropesPlased)) {
      Message.showInfo("To few trops", "You can not place more tropes than you have available");
    }
  }
  
  public static void attack(String attacker, String defender, int attackerTroops) {
  }




  
  
}
