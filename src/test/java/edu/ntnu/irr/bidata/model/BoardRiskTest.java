package edu.ntnu.irr.bidata.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import edu.ntnu.irr.bidata.model.risk.BoardRisk;
import edu.ntnu.irr.bidata.model.risk.Country;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link BoardRisk} class. Validates key game logic such as troop allocation,
 * continent bonuses, country ownership, troop transfers, victory conditions, and attack options.
 *
 * <p>Note: this class is made by ChatGPT
 */
class BoardRiskTest {
  BoardRisk board;
  Player player1;
  Player player2;

  @BeforeEach
  void setUp() {
    board = new BoardRisk();
    player1 = new Player("Alice", "Red", 1);
    player2 = new Player("Bob", "Blue", 2);

    // Clear ownerships for controlled setup
    board
        .getCountries()
        .values()
        .forEach(
            c -> {
              c.setOwner(new Player("", "", 0));
              c.setArmies(0);
            });
  }

  @Test
  @DisplayName("Calculate new troops with less than 12 countries")
  void testNewTroops_basic() {
    int count = 0;
    for (Country country : board.getCountries().values()) {
      if (count < 9) {
        country.setOwner(player1);
        count++;
      }
    }
    int troops = board.newTroops(player1);
    assertEquals(3, troops, "With less than 12 countries, troop bonus should be 3");
  }

  @Test
  @DisplayName("Calculate new troops including continent bonus")
  void testNewTroops_withContinentBonus() {
    List<String> australiaCountries =
        List.of("Indonesia", "New Guinea", "Western Australia", "Eastern Australia");
    for (String countryName : australiaCountries) {
      Country c = board.getCountries().get(countryName);
      c.setOwner(player1);
      c.setArmies(1);
    }
    int baseBonus = board.newTroops(player1);
    assertTrue(baseBonus >= 5, "Troops should include continent bonus of Australia");
  }

  @Test
  @DisplayName("Place troops on valid and invalid countries")
  void testPlaceTroops_validAndInvalidCountry() {
    String countryName = "Alaska";
    Country alaska = board.getCountries().get(countryName);
    alaska.setOwner(player1);
    alaska.setArmies(1);

    board.placeTroops(countryName, 5);
    assertEquals(6, alaska.getArmies());

    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class, () -> board.placeTroops("NonExistingCountry", 3));
    assertTrue(thrown.getMessage().contains("Country not found"));
  }

  @Test
  @DisplayName("Board setup fails with invalid player count")
  void testSetUpBoard_invalidPlayerCount() {
    List<Player> onePlayer = List.of(player1);
    List<Player> sevenPlayers = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      sevenPlayers.add(new Player("P" + i, "Color" + i, i));
    }
    assertThrows(IllegalArgumentException.class, () -> board.setUpBoard(onePlayer));
    assertThrows(IllegalArgumentException.class, () -> board.setUpBoard(sevenPlayers));
  }

  @Test
  @DisplayName("Board setup assigns countries to players correctly")
  void testSetUpBoard_valid() {
    List<Player> players = new ArrayList<>(List.of(player1, player2));
    board.setUpBoard(players);

    int totalOwned = 0;
    for (Country c : board.getCountries().values()) {
      assertNotNull(c.getOwner());
      assertTrue(c.getArmies() >= 1);
      totalOwned++;
    }
    assertEquals(board.getCountries().size(), totalOwned);
  }

  @Test
  @DisplayName("Check hasWon and hasLost conditions for players")
  void testHasWonAndHasLost() {
    board.getCountries().values().forEach(c -> c.setOwner(player1));
    assertTrue(board.hasWon(player1));
    assertFalse(board.hasLost(player1));

    board.getCountries().values().forEach(c -> c.setOwner(player1));
    assertFalse(board.hasWon(player2));
    assertTrue(board.hasLost(player2));
  }

  @Test
  @DisplayName("Take control of a country with valid and invalid inputs")
  void testTakeControlOfCountry() {
    String countryName = "Alaska";
    board.takeControlOfCountry(countryName, player1);
    assertEquals(player1.getName(), board.getCountries().get(countryName).getOwner());

    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> board.takeControlOfCountry("NonExistingCountry", player1));
    assertTrue(thrown.getMessage().contains("Country not found"));
  }

  @Test
  @DisplayName("Remove and add troops with valid and invalid countries")
  void testRemoveTroopsAndAddTroops() {
    String countryName = "Alaska";
    Country alaska = board.getCountries().get(countryName);
    alaska.setOwner(player1);
    alaska.setArmies(10);

    board.removeTroops(countryName, 4);
    assertEquals(6, alaska.getArmies());

    board.addTroops(countryName, 3);
    assertEquals(9, alaska.getArmies());

    assertThrows(IllegalArgumentException.class, () -> board.removeTroops("NoCountry", 1));
    assertThrows(IllegalArgumentException.class, () -> board.addTroops("NoCountry", 1));
  }

  @Test
  @DisplayName("Get number of units in a country")
  void testGetUnits() {
    String countryName = "Alaska";
    Country alaska = board.getCountries().get(countryName);
    alaska.setOwner(player1);
    alaska.setArmies(7);
    assertEquals(7, board.getUnits(countryName));

    assertThrows(IllegalArgumentException.class, () -> board.getUnits("NoCountry"));
  }

  @Test
  @DisplayName("Transfer troops between countries successfully and fail cases")
  void testTransferTroops_successAndFail() {
    String from = "Alaska";
    String to = "Northwest Territory";

    Country fromCountry = board.getCountries().get(from);
    Country toCountry = board.getCountries().get(to);
    fromCountry.setOwner(player1);
    fromCountry.setArmies(10);
    toCountry.setOwner(player1);
    toCountry.setArmies(5);

    board.transferTroops(from, to, 5);
    assertEquals(5, fromCountry.getArmies());
    assertEquals(10, toCountry.getArmies());

    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> board.transferTroops(from, to, 6));
    assertTrue(ex.getMessage().contains("Not enough troops"));

    assertThrows(IllegalArgumentException.class, () -> board.transferTroops("NoCountry", to, 1));
    assertThrows(IllegalArgumentException.class, () -> board.transferTroops(from, "NoCountry", 1));
  }

  @Test
  @DisplayName("Check if two countries are controlled by the same player")
  void testControlledBySamePlayer_trueAndFalse() {
    String c1 = "Alaska";
    String c2 = "Northwest Territory";

    Country country1 = board.getCountries().get(c1);
    Country country2 = board.getCountries().get(c2);

    country1.setOwner(player1);
    country2.setOwner(player1);
    assertTrue(board.controlledBySamePlayer(c1, c2));

    country2.setOwner(player2);
    assertFalse(board.controlledBySamePlayer(c1, c2));

    assertThrows(
        IllegalArgumentException.class, () -> board.controlledBySamePlayer("NoCountry", c2));
    assertThrows(
        IllegalArgumentException.class, () -> board.controlledBySamePlayer(c1, "NoCountry"));
  }

  @Test
  @DisplayName("Get valid attack options for player")
  void testGetAttackOptions() {
    Country attacker = board.getCountries().get("Alaska");
    Country defender = board.getCountries().get("Northwest Territory");
    attacker.setOwner(player1);
    attacker.setArmies(3);
    defender.setOwner(player2);
    defender.setArmies(1);

    Map<Country, List<Country>> attackOptions = board.getAttackOptions(player1);
    assertTrue(attackOptions.containsKey(attacker));
    assertTrue(attackOptions.get(attacker).contains(defender));

    attacker.setArmies(1);
    attackOptions = board.getAttackOptions(player1);
    assertFalse(attackOptions.containsKey(attacker));
  }
}
