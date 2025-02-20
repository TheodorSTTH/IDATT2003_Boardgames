package edu.ntnu.irr.bidata;

/**
 * Player class i responsible for managing player piece/user.
 * */
public class Player {
  private final String name;
  /// private Tile currentTile;

  public Player(String name) { // Tile currentTile
    this.name = name;
    /// this.currentTile = currentTile
  }

  public String getName() {
    return name;
  }
  /// public String getCurrentTile() {
  ///   return currentTile;
  /// }
  /// public void setCurrentTile(String currentTile) {
  ///   this.currentTile = currentTile;
  /// }
}
