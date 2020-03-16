package main;

import GUI.GUI;

/**
 * Holds main class
 *
 * @version 1.0
 * @author Ville Manninen
 */
public class Minesweeper {

    /**
     * Main class
     *
     * @since 1.0
     * @param args
     */
    public static void main(String args[]) {
        GUI gui = new GUI();

        System.out.println("Minesweeper version 1.0");
        Game game = new Game();
        game.newGame();
        System.out.println(game.toString());
    }
}
