package edu.ntnu.irr.bidata.RiskBackend;
import edu.ntnu.irr.bidata.GeneralClassesBackend.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BoardRisk {
  private HashMap<String, Country> countries = new HashMap<String, Country>();
  private HashMap<String, List<String>> continens = new HashMap<String, List<String>>();
  private HashMap<String, Integer> continentBonus = new HashMap<String, Integer>();


  public BoardRisk() {
    this.setUpClasicRisk();
  }

  private void setUpClasicRisk() {
    countries.put("Alaska", new Country("Alaska", List.of("Northwest Territory", "Alberta", "Kamchatka")));
    countries.put("Northwest Territory",
        new Country("Northwest Territory", List.of("Alaska", "Alberta", "Ontario", "Greenland")));
    countries.put("Alberta",
        new Country("Alberta", List.of("Alaska", "Northwest Territory", "Ontario", "Western United States")));
    countries.put("Ontario",
        new Country("Ontario", List.of("Northwest Territory", "Alberta", "Greenland", "Quebec", "Eastern United States",
            "Western United States")));
    countries.put("Greenland",
        new Country("Greenland", List.of("Northwest Territory", "Ontario", "Quebec", "Iceland")));
    countries.put("Quebec",
        new Country("Quebec", List.of("Greenland", "Ontario", "Eastern United States")));
    countries.put("Eastern United States",
        new Country("Eastern United States", List.of("Ontario", "Quebec", "Western United States", "Central America")));
    countries.put("Western United States",
        new Country("Western United States",
            List.of("Alberta", "Ontario", "Eastern United States", "Central America")));
    countries.put("Central America",
        new Country("Central America", List.of("Western United States", "Eastern United States", "Venezuela")));
    countries.put("Venezuela",
        new Country("Venezuela", List.of("Central America", "Peru", "Brazil")));
    countries.put("Peru", new Country("Peru", List.of("Venezuela", "Brazil", "Argentina")));
    countries.put("Brazil",
        new Country("Brazil", List.of("Venezuela", "Peru", "Argentina", "North Africa")));
    countries.put("Argentina", new Country("Argentina", List.of("Peru", "Brazil")));
    countries.put("Iceland", new Country("Iceland", List.of("Greenland", "Scandinavia", "Great Britain")));
    countries.put("Scandinavia",
        new Country("Scandinavia", List.of("Iceland", "Great Britain", "Northern Europe", "Ukraine")));
    countries.put("Great Britain",
        new Country("Great Britain", List.of("Iceland", "Scandinavia", "Northern Europe", "Western Europe")));
    countries.put("Northern Europe",
        new Country("Northern Europe",
            List.of("Scandinavia", "Great Britain", "Western Europe", "Southern Europe", "Ukraine")));
    countries.put("Western Europe",
        new Country("Western Europe", List.of("Great Britain", "Northern Europe", "Southern Europe", "North Africa")));
    countries.put("Southern Europe",
        new Country("Southern Europe",
            List.of("Northern Europe", "Western Europe", "Ukraine", "Middle East", "Egypt", "North Africa")));
    countries.put("Ukraine",
        new Country("Ukraine", List.of("Scandinavia", "Northern Europe", "Southern Europe", "Ural", "Afghanistan",
            "Middle East")));
    countries.put("North Africa",
        new Country("North Africa", List.of("Brazil", "Western Europe", "Southern Europe", "Egypt", "East Africa",
            "Congo")));
    countries.put("Egypt",
        new Country("Egypt", List.of("Southern Europe", "North Africa", "East Africa", "Middle East")));
    countries.put("East Africa",
        new Country("East Africa",
            List.of("North Africa", "Egypt", "Congo", "South Africa", "Madagascar", "Middle East")));
    countries.put("Congo",
        new Country("Congo", List.of("North Africa", "East Africa", "South Africa")));
    countries.put("South Africa",
        new Country("South Africa", List.of("Congo", "East Africa", "Madagascar")));
    countries.put("Madagascar", new Country("Madagascar", List.of("South Africa", "East Africa")));
    countries.put("Ural",
        new Country("Ural", List.of("Ukraine", "Siberia", "China", "Afghanistan")));
    countries.put("Siberia",
        new Country("Siberia", List.of("Ural", "China", "Mongolia", "Irkutsk", "Yakutsk")));
    countries.put("China",
        new Country("China", List.of("Ural", "Siberia", "Mongolia", "Afghanistan", "India", "Siam")));
    countries.put("Afghanistan",
        new Country("Afghanistan", List.of("Ukraine", "Ural", "China", "India", "Middle East")));
    countries.put("Middle East",
        new Country("Middle East",
            List.of("Southern Europe", "Ukraine", "Afghanistan", "India", "East Africa", "Egypt")));
    countries.put("India",
        new Country("India", List.of("China", "Afghanistan", "Middle East", "Siam")));
    countries.put("Siam", new Country("Siam", List.of("China", "India", "Indonesia")));
    countries.put("Mongolia",
        new Country("Mongolia", List.of("Siberia", "China", "Irkutsk", "Japan", "Kamchatka")));
    countries.put("Irkutsk",
        new Country("Irkutsk", List.of("Siberia", "Yakutsk", "Kamchatka", "Mongolia")));
    countries.put("Yakutsk",
        new Country("Yakutsk", List.of("Siberia", "Irkutsk", "Kamchatka")));
    countries.put("Kamchatka",
        new Country("Kamchatka", List.of("Alaska", "Japan", "Mongolia", "Irkutsk", "Yakutsk")));
    countries.put("Japan", new Country("Japan", List.of("Mongolia", "Kamchatka")));
    countries.put("Indonesia", new Country("Indonesia", List.of("Siam", "New Guinea", "Western Australia")));
    countries.put("New Guinea",
        new Country("New Guinea", List.of("Indonesia", "Eastern Australia", "Western Australia")));
    countries.put("Western Australia",
        new Country("Western Australia", List.of("Indonesia", "New Guinea", "Eastern Australia")));
    countries.put("Eastern Australia",
        new Country("Eastern Australia", List.of("New Guinea", "Western Australia")));

    continens.put("North America", List.of("Alaska", "Northwest Territory", "Alberta", "Ontario", "Greenland", "Quebec",
        "Eastern United States", "Western United States", "Central America"));
    continens.put("South America", List.of("Venezuela", "Peru", "Brazil", "Argentina"));
    continens.put("Europe", List.of("Iceland", "Scandinavia", "Great Britain", "Northern Europe", "Western Europe",
        "Southern Europe", "Ukraine"));
    continens.put("Africa", List.of("North Africa", "Egypt", "East Africa", "Congo", "South Africa", "Madagascar"));
    continens.put("Asia", List.of("Ural", "Siberia", "China", "Afghanistan", "Middle East", "India", "Siam", "Mongolia",
        "Irkutsk", "Yakutsk", "Kamchatka", "Japan"));
    continens.put("Australia", List.of("Indonesia", "New Guinea", "Western Australia", "Eastern Australia"));

    continentBonus.put("North America", 5);
    continentBonus.put("South America", 2);
    continentBonus.put("Europe", 5);
    continentBonus.put("Africa", 3);
    continentBonus.put("Asia", 7);
    continentBonus.put("Australia", 2);
  }

  public List<Country> getCountrysControldByPlayer(Player player) {
    List<Country> countriesControlled = new ArrayList<Country>();
    for (Country country : countries.values()) {
      if (country.getOwner() == player) {
        countriesControlled.add(country);
      }
    }
    return countriesControlled;
  }




  
}
