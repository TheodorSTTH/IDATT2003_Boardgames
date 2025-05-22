package edu.ntnu.irr.bidata.model.newlogic.risk;

import edu.ntnu.irr.bidata.model.newlogic.PlayerManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiskFactory {

  private static RiskBoard createDefaultBoard() {
    RiskBoard board = new RiskBoard();

    List<Country> countries = List.of(
        new Country("Alaska", 0.07, 0.16),
        new Country("Northwest Territory", 0.16, 0.14),
        new Country("Alberta", 0.13, 0.23),
        new Country("Ontario", 0.23, 0.24),
        new Country("Greenland", 0.32, 0.09),
        new Country("Quebec", 0.29, 0.26),
        new Country("Western United States", 0.14, 0.34),
        new Country("Eastern United States", 0.23, 0.34),
        new Country("Central America", 0.14, 0.42),
        new Country("Venezuela", 0.23, 0.53),
        new Country("Peru", 0.25, 0.65),
        new Country("Brazil", 0.31, 0.64),
        new Country("Argentina", 0.24, 0.78),
        new Country("Iceland", 0.39, 0.20),
        new Country("Scandinavia", 0.47, 0.20),
        new Country("Great Britain", 0.39, 0.28),
        new Country("Northern Europe", 0.48, 0.30),
        new Country("Western Europe", 0.41, 0.45),
        new Country("Southern Europe", 0.50, 0.42),
        new Country("Ukraine", 0.57, 0.28),
        new Country("North Africa", 0.42, 0.57),
        new Country("Egypt", 0.53, 0.53),
        new Country("East Africa", 0.55, 0.64),
        new Country("Congo", 0.53, 0.71),
        new Country("South Africa", 0.52, 0.82),
        new Country("Madagascar", 0.61, 0.815),
        new Country("Ural", 0.67, 0.25),
        new Country("Siberia", 0.71, 0.12),
        new Country("Yakutsk", 0.79, 0.10),
        new Country("Irkutsk", 0.79, 0.25),
        new Country("Kamchatka", 0.88, 0.14),
        new Country("Mongolia", 0.79, 0.33),
        new Country("China", 0.77, 0.41),
        new Country("Afghanistan", 0.63, 0.37),
        new Country("Middle East", 0.59, 0.47),
        new Country("India", 0.71, 0.50),
        new Country("Siam", 0.79, 0.56),
        new Country("Japan", 0.89, 0.37),
        new Country("Indonesia", 0.80, 0.72),
        new Country("New Guinea", 0.88, 0.65),
        new Country("Western Australia", 0.83, 0.81),
        new Country("Eastern Australia", 0.93, 0.85)
    );

    Map<String, Country> c = new HashMap<>();
    for (Country country : countries) {
      c.put(country.getName(), country);
    }

    c.get("Alaska").setNeighbours(List.of(
        c.get("Northwest Territory"), c.get("Alberta"), c.get("Kamchatka")
    ));
    c.get("Northwest Territory").setNeighbours(List.of(
        c.get("Alaska"), c.get("Alberta"), c.get("Ontario"), c.get("Greenland")
    ));
    c.get("Alberta").setNeighbours(List.of(
        c.get("Alaska"), c.get("Northwest Territory"), c.get("Ontario"), c.get("Western United States")
    ));
    c.get("Ontario").setNeighbours(List.of(
        c.get("Northwest Territory"), c.get("Alberta"), c.get("Greenland"),
        c.get("Quebec"), c.get("Eastern United States"), c.get("Western United States")
    ));
    c.get("Greenland").setNeighbours(List.of(
        c.get("Northwest Territory"), c.get("Ontario"), c.get("Quebec"), c.get("Iceland")
    ));
    c.get("Quebec").setNeighbours(List.of(
        c.get("Greenland"), c.get("Ontario"), c.get("Eastern United States")
    ));
    c.get("Western United States").setNeighbours(List.of(
        c.get("Alberta"), c.get("Ontario"), c.get("Eastern United States"), c.get("Central America")
    ));
    c.get("Eastern United States").setNeighbours(List.of(
        c.get("Ontario"), c.get("Quebec"), c.get("Western United States"), c.get("Central America")
    ));
    c.get("Central America").setNeighbours(List.of(
        c.get("Western United States"), c.get("Eastern United States"), c.get("Venezuela")
    ));
    c.get("Venezuela").setNeighbours(List.of(
        c.get("Central America"), c.get("Peru"), c.get("Brazil")
    ));
    c.get("Peru").setNeighbours(List.of(
        c.get("Venezuela"), c.get("Brazil"), c.get("Argentina")
    ));
    c.get("Brazil").setNeighbours(List.of(
        c.get("Venezuela"), c.get("Peru"), c.get("Argentina"), c.get("North Africa")
    ));
    c.get("Argentina").setNeighbours(List.of(
        c.get("Peru"), c.get("Brazil")
    ));
    c.get("Iceland").setNeighbours(List.of(
        c.get("Greenland"), c.get("Scandinavia"), c.get("Great Britain")
    ));
    c.get("Scandinavia").setNeighbours(List.of(
        c.get("Iceland"), c.get("Great Britain"), c.get("Northern Europe"), c.get("Ukraine")
    ));
    c.get("Great Britain").setNeighbours(List.of(
        c.get("Iceland"), c.get("Scandinavia"), c.get("Northern Europe"), c.get("Western Europe")
    ));
    c.get("Northern Europe").setNeighbours(List.of(
        c.get("Scandinavia"), c.get("Great Britain"), c.get("Western Europe"),
        c.get("Southern Europe"), c.get("Ukraine")
    ));
    c.get("Western Europe").setNeighbours(List.of(
        c.get("Great Britain"), c.get("Northern Europe"), c.get("Southern Europe"), c.get("North Africa")
    ));
    c.get("Southern Europe").setNeighbours(List.of(
        c.get("Northern Europe"), c.get("Western Europe"), c.get("Ukraine"),
        c.get("Middle East"), c.get("Egypt"), c.get("North Africa")
    ));
    c.get("Ukraine").setNeighbours(List.of(
        c.get("Scandinavia"), c.get("Northern Europe"), c.get("Southern Europe"),
        c.get("Ural"), c.get("Afghanistan"), c.get("Middle East")
    ));
    c.get("North Africa").setNeighbours(List.of(
        c.get("Brazil"), c.get("Western Europe"), c.get("Southern Europe"),
        c.get("Egypt"), c.get("East Africa"), c.get("Congo")
    ));
    c.get("Egypt").setNeighbours(List.of(
        c.get("Southern Europe"), c.get("North Africa"), c.get("East Africa"), c.get("Middle East")
    ));
    c.get("East Africa").setNeighbours(List.of(
        c.get("North Africa"), c.get("Egypt"), c.get("Congo"),
        c.get("South Africa"), c.get("Madagascar"), c.get("Middle East")
    ));
    c.get("Congo").setNeighbours(List.of(
        c.get("North Africa"), c.get("East Africa"), c.get("South Africa")
    ));
    c.get("South Africa").setNeighbours(List.of(
        c.get("Congo"), c.get("East Africa"), c.get("Madagascar")
    ));
    c.get("Madagascar").setNeighbours(List.of(
        c.get("South Africa"), c.get("East Africa")
    ));
    c.get("Ural").setNeighbours(List.of(
        c.get("Ukraine"), c.get("Siberia"), c.get("China"), c.get("Afghanistan")
    ));
    c.get("Siberia").setNeighbours(List.of(
        c.get("Ural"), c.get("China"), c.get("Mongolia"), c.get("Irkutsk"), c.get("Yakutsk")
    ));
    c.get("Yakutsk").setNeighbours(List.of(
        c.get("Siberia"), c.get("Irkutsk"), c.get("Kamchatka")
    ));
    c.get("Irkutsk").setNeighbours(List.of(
        c.get("Siberia"), c.get("Yakutsk"), c.get("Kamchatka"), c.get("Mongolia")
    ));
    c.get("Kamchatka").setNeighbours(List.of(
        c.get("Alaska"), c.get("Japan"), c.get("Mongolia"), c.get("Irkutsk"), c.get("Yakutsk")
    ));
    c.get("Mongolia").setNeighbours(List.of(
        c.get("Siberia"), c.get("China"), c.get("Irkutsk"), c.get("Japan"), c.get("Kamchatka")
    ));
    c.get("China").setNeighbours(List.of(
        c.get("Ural"), c.get("Siberia"), c.get("Mongolia"),
        c.get("Afghanistan"), c.get("India"), c.get("Siam")
    ));
    c.get("Afghanistan").setNeighbours(List.of(
        c.get("Ukraine"), c.get("Ural"), c.get("China"), c.get("India"), c.get("Middle East")
    ));
    c.get("Middle East").setNeighbours(List.of(
        c.get("Southern Europe"), c.get("Ukraine"), c.get("Afghanistan"),
        c.get("India"), c.get("East Africa"), c.get("Egypt")
    ));
    c.get("India").setNeighbours(List.of(
        c.get("China"), c.get("Afghanistan"), c.get("Middle East"), c.get("Siam")
    ));
    c.get("Siam").setNeighbours(List.of(
        c.get("China"), c.get("India"), c.get("Indonesia")
    ));
    c.get("Japan").setNeighbours(List.of(
        c.get("Mongolia"), c.get("Kamchatka")
    ));
    c.get("Indonesia").setNeighbours(List.of(
        c.get("Siam"), c.get("New Guinea"), c.get("Western Australia")
    ));
    c.get("New Guinea").setNeighbours(List.of(
        c.get("Indonesia"), c.get("Eastern Australia"), c.get("Western Australia")
    ));
    c.get("Western Australia").setNeighbours(List.of(
        c.get("Indonesia"), c.get("New Guinea"), c.get("Eastern Australia")
    ));
    c.get("Eastern Australia").setNeighbours(List.of(
        c.get("New Guinea"), c.get("Western Australia")
    ));

    board.setCountries((HashMap<String, Country>) c);

    Continent oceania = new Continent((ArrayList<Country>) List.of(
      c.get("Indonesia"),
      c.get("New Guinea"),
      c.get("Western Australia"),
      c.get("Eastern Australia")),
      2
    );

    Continent southAmerica = new Continent(
      new ArrayList<>(List.of(
        c.get("Venezuela"),
        c.get("Peru"),
        c.get("Brazil"),
        c.get("Argentina")
      )),
      2
    );

    Continent europe = new Continent(
      new ArrayList<>(List.of(
        c.get("Iceland"),
        c.get("Scandinavia"),
        c.get("Great Britain"),
        c.get("Northern Europe"),
        c.get("Western Europe"),
        c.get("Southern Europe"),
        c.get("Ukraine")
      )),
      5
    );

    Continent africa = new Continent(
      new ArrayList<>(List.of(
        c.get("North Africa"),
        c.get("Egypt"),
        c.get("East Africa"),
        c.get("Congo"),
        c.get("South Africa"),
        c.get("Madagascar")
      )),
      3
    );

    Continent asia = new Continent(
      new ArrayList<>(List.of(
        c.get("Ural"),
        c.get("Siberia"),
        c.get("Yakutsk"),
        c.get("Irkutsk"),
        c.get("Kamchatka"),
        c.get("Mongolia"),
        c.get("China"),
        c.get("Afghanistan"),
        c.get("Middle East"),
        c.get("India"),
        c.get("Siam"),
        c.get("Japan")
      )),
      7
    );

    Continent northAmerica = new Continent(
      new ArrayList<>(List.of(
        c.get("Alaska"),
        c.get("Northwest Territory"),
        c.get("Greenland"),
        c.get("Alberta"),
        c.get("Ontario"),
        c.get("Quebec"),
        c.get("Western United States"),
        c.get("Eastern United States"),
        c.get("Central America")
      )),
      5
    );

    board.setContinents(
      new ArrayList<>(List.of(
        northAmerica,
        southAmerica,
        europe,
        africa,
        asia,
        oceania
      ))
    );

    return board;
  }

  public static RiskGame createRiskGame(PlayerManager playerManager) {
    return new RiskGame(playerManager, createDefaultBoard());
  }
}
