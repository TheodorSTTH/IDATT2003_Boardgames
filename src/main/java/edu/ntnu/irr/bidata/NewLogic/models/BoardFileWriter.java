package edu.ntnu.irr.bidata.NewLogic.models;

import java.nio.file.Path;

public interface BoardFileWriter {
  public void writeBoard(Path path, Board board);
}
