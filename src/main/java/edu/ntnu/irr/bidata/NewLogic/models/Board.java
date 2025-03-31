package edu.ntnu.irr.bidata.NewLogic.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the physical ladder game board with a collection of tiles.
 * */
public class Board<T extends ISpace<?>> {
  private final Map<Integer, T> spaces;

  public Board() {
    spaces = new HashMap<Integer, T>();
  }

  public void setSpace(int id, T space) {
    spaces.put(id, space);
  }

  public T getSpace(int id) {
    return spaces.get(id);
  }
}