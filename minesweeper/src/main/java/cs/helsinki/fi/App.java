package cs.helsinki.fi;

import cs.helsinki.fi.gui.Gui;
import javafx.application.Application;

/**
 * Class to create and launching minesweeper like Application.
 *
 * @version 1.1
 * @author Ville Manninen
 */
public class App {

    /**
     * Main class launches JavaFX Application class GUI.java.does not expect any
     * arguments.
     *
     * @throws java.lang.Exception - Exception in method Application.launch()
     * @since 1.0
     * @param args - Arguments not expected
     */
    public static void main(String args[]) throws Exception {
        try {
            Application.launch(Gui.class);
        } catch (Exception e) {
            System.out.println("Unfortunate error");
            System.out.println(e);
        }
    }
}
