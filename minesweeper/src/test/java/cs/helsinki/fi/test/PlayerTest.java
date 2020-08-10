package cs.helsinki.fi.test;

import cs.helsinki.fi.game.Player;
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

    @Test
    public void testPlayerName() {
        String name = "testName";
        Player player = new Player(name);
        assertEquals("testName", player.getName());

        String empty = "";

        Player player2 = new Player(empty);
        assertEquals("", player2.getName());

        String wayTooLong = "AAAAAAAAABBBBBBBBBBBCCCCCCCCCCCCCCCDDDDDDDDDDDDDDDDDDDDDEEEEEEEEEEEEEEEEEE";
        boolean nameTrim = false;
        Player player3 = new Player(wayTooLong);
        if (player3.getName().length() == 20) {
            nameTrim = true;
        }
        assertEquals(true, nameTrim);
    }

    @Test
    public void testToString() {
        String name = "testName";
        Player player = new Player(name);
        assertEquals("Player: testName", player.toString());
    }
}
