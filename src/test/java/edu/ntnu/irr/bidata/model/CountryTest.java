package edu.ntnu.irr.bidata.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CountryTest {
    Player Johan;
    Player Birgitte;
  
    @BeforeEach
    void setUp() {
      Johan = new Player("Johan", "White", 10);
      Birgitte = new Player("Birgitte", "Green", 20);
    }


  @Test
  @DisplayName("EventMakker")
  void placeTropesTest() {
  }


  @Test
  @DisplayName("EventMakker")
  void loseTropesTest() {
  }


  @Test
  @DisplayName("EventMakker")
  void setOwnerTest() {
  }


  
}
