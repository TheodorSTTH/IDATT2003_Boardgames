package edu.ntnu.irr.bidata.NewLogic.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the physical ladder game board with a collection of tiles.
 * */
public class Board {
  private final Map<Integer, Tile> tiles;

  public Board() {
    tiles = new HashMap<Integer, Tile>();
  }

  public void setTile(int tileId, Tile tile) {
    tiles.put(tileId, tile);
  }

  public Tile getTile(int tileId) {
    return tiles.get(tileId);
  }
}