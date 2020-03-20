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

    @Test
    public void testPlayerName() {
        String name = "testName";
        Player player = new Player(name);
        assertEquals("testName", player.getName());

        String wayTooLong = "AAAAAAAAABBBBBBBBBBBCCCCCCCCCCCCCCCDDDDDDDDDDDDDDDDDDDDDEEEEEEEEEEEEEEEEEE";
        boolean nameTrim = false;
        Player player2 = new Player(wayTooLong);
        if (player2.getName().length() == 20) {
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
