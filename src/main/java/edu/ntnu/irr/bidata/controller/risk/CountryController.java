package edu.ntnu.irr.bidata.controller.risk;

import edu.ntnu.irr.bidata.model.interfaces.observer.SimpleObserver;
import edu.ntnu.irr.bidata.model.risk.Country;
import edu.ntnu.irr.bidata.view.PopUp;
import edu.ntnu.irr.bidata.view.risk.CountryView;

public class CountryController implements SimpleObserver {
  private final Country country;
  private final CountryView view;

  public CountryController(Country country, double boardWidth, double boardHeight) {
    this.view = new CountryView(boardWidth, boardHeight);
    this.country = country;
    country.registerObserver(this);

    view.setOnAction(
        e -> {
          PopUp.showInfo(
              country.getName(),
              country.getName()
                  + "\nOwner: "
                  + country.getOwner()
                  + "\nArmies: "
                  + country.getArmies());
        });
    update();
  }

  @Override
  public void update() {
    view.render(
        country.getArmies(),
        country.getOwnerColor(),
        country.getRelativeX(),
        country.getRelativeY());
  }

  public CountryView getView() {
    return view;
  }
}
