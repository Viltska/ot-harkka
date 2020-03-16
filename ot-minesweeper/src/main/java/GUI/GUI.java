package GUI;

import main.*;

/**
 * Class for a graphical user interface
 *
 * @since 1.0
 * @author Ville Manninen
 */
public class GUI {

    private static boolean running;
    private static Game game;

    public GUI() {
        this.running = false;

    }

    /**
     * Method for starting the GUI
     */
    public void start() {
        running = true;

    }

    /**
     * Method for closing the GUI
     */
    public void close() {
        running = false;
    }

}
