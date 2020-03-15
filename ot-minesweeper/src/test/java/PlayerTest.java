
import main.Player;
import org.junit.Test;
import static org.junit.Assert.*;

/**
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
    }

    @Test
    public void testToString() {
        String name = "testName";
        Player player = new Player(name);
        assertEquals("Player: testName", player.toString());
    }
}
