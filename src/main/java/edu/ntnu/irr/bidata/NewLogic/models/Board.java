package edu.ntnu.irr.bidata.NewLogic.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the physical ladder game board with a collection of tiles.
 * */
public class Board<U, T extends ISpace<?>> {
  private final Map<U, T> spaces;

  public Board() {
    spaces = new HashMap<U, T>();
  }

  public void setSpace(U id, T space) {
    spaces.put(id, space);
  }

  public T getSpace(U id) {
    return spaces.get(id);
  }
}