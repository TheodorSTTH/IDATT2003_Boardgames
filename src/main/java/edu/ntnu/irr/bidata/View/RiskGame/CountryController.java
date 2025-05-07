package edu.ntnu.irr.bidata.View.RiskGame;

import edu.ntnu.irr.bidata.Model.Risk.Country;
import edu.ntnu.irr.bidata.Model.interfaces.observer.ISimpleObserver;
import edu.ntnu.irr.bidata.View.PopUp;

public class CountryController implements ISimpleObserver {
  private final Country country;
  private final CountryView view;

  public CountryController(Country country, double boardWidth, double boardHeight) {
    this.view = new CountryView(boardWidth, boardHeight);
    this.country = country;
    country.registerObserver(this);

    view.setOnAction(e -> {
      PopUp.showInfo(country.getName(),
          country.getName() + "\nOwner: " + country.getOwner() + "\nArmies: " + country.getArmies());
    });
    update();
  }

  @Override
  public void update() {
    view.render(country.getArmies(), country.getOwnerColor(), country.getRelativeX(), country.getRelativeY());
  }

  public CountryView getView() {
    return view;
  }
}
