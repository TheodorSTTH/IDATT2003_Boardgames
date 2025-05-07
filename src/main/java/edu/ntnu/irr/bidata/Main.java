package edu.ntnu.irr.bidata;

import edu.ntnu.irr.bidata.controler.MyWindow;

/**
 * Main class that serves as the entry point for the application.
 * This class contains the `main` method, which is the starting point of the application.
 * The `main` method initializes the application by 
 * invoking the `main` method of the `MyWindow` class.
 */
public class Main {

  /**
   * Main entry point for the application.
   * This method initializes and starts the application by calling the main method 
   * of the MyWindow class.
   *
   * @param args Command line arguments (not used in this implementation)
   * @throws Exception If any exception occurs during the application startup
   */
  public static void main(String[] args) throws Exception {
    MyWindow.main(args);
  }
}