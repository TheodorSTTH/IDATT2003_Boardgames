package edu.ntnu.irr.bidata.NewLogic.models;

import java.nio.file.Path;

public interface BoardFileReader {
  public Board readBoard(Path path);
}
