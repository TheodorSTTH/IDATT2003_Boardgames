package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Model.Risk.Risk;



public class UIRisk {
  private static Risk risk;

  public static void setRisk(Risk risk) {
    UIRisk.risk = risk;
  }

  public static void toRiskGamePage() {
  }
  
  public static void placeTropes(String Conteris, int tropesPlased) {
    risk.placeTropes(Conteris, tropesPlased);
  }




  
  
}
