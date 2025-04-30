package edu.ntnu.irr.bidata.Controler;
import edu.ntnu.irr.bidata.Model.Risk.Risk;
import edu.ntnu.irr.bidata.View.LadderGameOverview.OverviewPage;
import edu.ntnu.irr.bidata.View.PopUp;
import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.View.RiskGame.RiskPage;
import java.util.HashMap;
import java.util.List;


// TODO: Consider making class have non static fields
public class UIRisk extends UI {
  private static Risk risk;
  private static RiskPage riskPage;

  public static void setRisk(Risk risk) {
    UIRisk.risk = risk;
  }
  public static void setRiskPage(RiskPage riskPage) {
    UIRisk.riskPage = riskPage;
  }

  public static void updateRiskGamePage(HashMap<String, Country> countries) {
  }

  public static void updateAttackMenu(HashMap<Country, List<Country>> attackOptions) {
  }
}
