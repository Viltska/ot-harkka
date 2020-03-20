package main;

import gui.GraphicalUserInterface;
import javafx.application.Application;

/**
 * Holds main class
 *
 * @version 1.0
 * @author Ville Manninen
 */
public class App {

    /**
     * Main class launches JavaFX Application class GUI.java. does not expect
     * any arguments.
     *
     * @since 1.0
     * @param args - Arguments not expected
     */
    public static void main(String args[]) {
        Application.launch(GraphicalUserInterface.class, args);

    }
}
