package test;

import main.Game;
import main.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Holds tests for the class Game.
 *
 * @since 1.0
 * @author Ville Manninen
 */
public class GameTest {

    public GameTest() {
    }

    @Test
    public void testCreatingClass() {
        Game game = new Game();
        Game game1 = new Game(10);
        game.newGame();
        game1.newGame();
    }

    @Test
    public void testPlayer() {
        Game game = new Game();
        assertEquals(null, game.getPlayer());
        game.setPlayer("TestName");
        Player player = game.getPlayer();
        assertEquals("TestName", player.getName());
    }

    @Test
    public void testCheckInsideGrid() {
        Game game = new Game();
        assertEquals(true, game.checkInsideGrid(0, 0));
        assertEquals(true, game.checkInsideGrid(game.getLength() - 1, game.getLength() - 1));
        assertEquals(false, game.checkInsideGrid(-1, 0));
        assertEquals(false, game.checkInsideGrid(0, -1));
        assertEquals(false, game.checkInsideGrid(0, game.getLength()));
        assertEquals(false, game.checkInsideGrid(game.getLength(), 0));

    }

    @Test
    public void testMineCheck() {
        Game game = new Game();
        boolean noMine = game.checkForMine(0, 0);

        boolean mineFound = false;

        game.newGame();
        for (int i = 0; i < game.getLength(); i++) {
            for (int j = 0; j < game.getLength(); j++) {
                if (game.checkForMine(i, j)) {
                    mineFound = true;
                }

            }
        }
        assertEquals(false, noMine);
        assertEquals(true, mineFound);
    }

    @Test
    public void testNewGame() {
        Game game = new Game();
        game.newGame();

        int mines = 0;
        for (int i = 0; i < game.getLength(); i++) {
            for (int j = 0; j < game.getLength(); j++) {
                if (game.checkForMine(i, j)) {
                    mines++;
                }
            }
        }
        Game game2 = new Game(20);
        game.newGame();

        int mines2 = 0;
        for (int i = 0; i < game2.getLength(); i++) {
            for (int j = 0; j < game2.getLength(); j++) {
                if (game.checkForMine(i, j)) {
                    mines2++;
                }
            }
        }
        assertEquals(mines, 30);
        assertEquals(mines2, 20);
    }

    @Test
    public void testNextTurn() {
        Game game = new Game();
        int turn1 = game.getTurn();
        game.nextTurn();
        int turn2 = game.getTurn();

        assertEquals(1, turn1);
        assertEquals(2, turn2);
    }

    @Test
    public void testGameOver() {
        Game game = new Game();
        assertEquals(false, game.getGameover());
        game.setGameOver();
        assertEquals(true, game.getGameover());

    }

    @Test
    public void testGameWon() {
        Game game = new Game();
        assertEquals(false, game.getGameover());
        game.setGameWon();
        assertEquals(true, game.getGameWon());
    }

    @Test
    public void testSetMine() {
        Game game = new Game();
        assertEquals(false, game.checkForMine(0, 0));
        game.setMine(0, 0);
        assertEquals(true, game.checkForMine(0, 0));
        assertEquals(31, game.getMines());
        game.setMine(0, 0);
        assertEquals(31, game.getMines());
    }

    @Test
    public void testNeighbours() {
        Game game = new Game();
        assertEquals(0, game.checkNeighbours(0, 0));
        game.setMine(1, 1);
        game.setMine(2, 1);
        game.setMine(1, 2);

        assertEquals(1, game.checkNeighbours(0, 0));
        assertEquals(2, game.checkNeighbours(1, 0));
        assertEquals(2, game.checkNeighbours(2, 0));
        assertEquals(2, game.checkNeighbours(0, 1));
        assertEquals(2, game.checkNeighbours(0, 2));
        assertEquals(3, game.checkNeighbours(2, 2));
    }

    /**
     * Creates 100 games with 200 mines. Checks if number of neighbouring mines
     * are always between 0 and 8.
     */
    @Test
    public void testCheckNeighboursIterative() {
        int mines = 200;
        boolean inParameters = true;
        for (int i = 0; i < 100; i++) {
            Game game = new Game(mines);
            game.newGame();

            for (int j = 0; j < game.getLength(); j++) {
                for (int l = 0; l < game.getLength(); l++) {
                    int neigh = game.checkNeighbours(j, l);
                    if (neigh > 8) {
                        inParameters = false;
                    }
                    if (neigh < 0) {
                        inParameters = false;
                    }

                }
            }
        }
        assertEquals(true, inParameters);
    }

    @Test
    public void testToString() {
        Game game = new Game();
        game.newGame();
        String toString = game.toString();
        assertEquals(false, toString.isEmpty());
    }
}
