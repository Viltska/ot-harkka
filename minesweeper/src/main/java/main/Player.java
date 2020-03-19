package main;

/**
 * Represents a player of the game.
 *
 * @author Ville Manninen
 */
public class Player {

    private static String name;

    /**
     * Creates a new Player with a name.
     *
     * @param name - name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the Player.
     *
     * @return String name - Player name
     */
    public String getName() {
        return name;
    }

    /**
     * Overrides method toString(). Method converts player class to a String
     * representation.
     *
     * @return String "Player: " + this.name
     */
    @Override
    public String toString() {
        return "Player: " + this.name;
    }
}
