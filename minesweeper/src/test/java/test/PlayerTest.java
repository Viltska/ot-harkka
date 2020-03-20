package test;


import main.Player;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the class Player.
 *
 * @version 1.0
 * @author Ville Manninen
 */
public class PlayerTest {

    public PlayerTest() {
    }

    /**
     * Test for method getName.
     */
    @Test
    public void testPlayerName() {
        String name = "testName";
        Player player = new Player(name);
        assertEquals("testName", player.getName());
    }

    /**
     * Test for method toString.
     */
    @Test
    public void testToString() {
        String name = "testName";
        Player player = new Player(name);
        assertEquals("Player: testName", player.toString());
    }
}
