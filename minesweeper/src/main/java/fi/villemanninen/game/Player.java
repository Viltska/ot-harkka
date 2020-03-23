package fi.villemanninen.game;

/**
 * Represents a player of the game.
 *
 * @author Ville Manninen
 */
public class Player {

    private static String name;

    /**
     * Creates a new Player with a name. only uses first 20 letters of the name.
     *
     * @param name - name of the player
     */
    public Player(String name) {
        if (!name.isEmpty() && name.length() > 20) {
            this.name = name.substring(0, 20);
        } else {
            this.name = name;
        }
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
