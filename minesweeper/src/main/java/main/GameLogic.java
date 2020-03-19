package main;

import java.util.Random;
import java.util.Timer;

/**
 * Class to execute game logic
 *
 * @author Ville Manninen
 */
public class GameLogic {

    private static int[][] grid;
    private static Player player;
    private static int WIDTH = 20;
    private static int mines;
    private static int turn;
    private static boolean gameOver;
    private Timer timer;
    private int seconds;

    /**
     * Creates a default game of minesweeper with WIDTH wide square grid. Width
     * is equal to 10. holding 30 mines starting at turn 1.
     */
    public GameLogic() {
        this.grid = new int[this.WIDTH][this.WIDTH];
        this.mines = 30;
        this.gameOver = false;
        this.turn = 1;
    }

    /**
     * Creates a game of minesweeper with custom number of mines.
     *
     * @param mines - number of mines inside the gird
     */
    public GameLogic(int mines) {
        this.grid = new int[WIDTH][WIDTH];
        this.mines = mines;
        this.gameOver = false;
        this.turn = 1;
    }

    /**
     * Fills the grid with int value 0 and then places 30 mines randomly. 1
     * represents a mine and 0 represents an empty square.
     *
     */
    public void newGame() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                grid[i][j] = 0;
            }
        }
        int minesLeft = this.mines;
        Random random = new Random();
        while (minesLeft > 0) {
            int iRandom = random.nextInt(WIDTH);
            int jRandom = random.nextInt(WIDTH);
            if (grid[iRandom][jRandom] == 0) {
                grid[iRandom][jRandom] = 1;
                minesLeft--;
            }
        }
    }

    /**
     * Sets game clock to 0.
     */
    public void resetClock() {
        this.seconds = 0;

    }

    /**
     * Method to get the current game time in seconds.
     *
     * @return seconds - elapsed game time in seconds
     */
    public int getClock() {
        return this.seconds;
    }

    /**
     * Method to advance to next turn. If incremented turn is equal to empty
     * squares of the game method calls setGameOver(); ending the game.
     *
     */
    public void nextTurn() {
        turn++;
        if (turn == ((WIDTH * WIDTH) - this.mines)) {
            setGameOver();
        }
    }

    public int getTurn() {
        return this.turn;
    }

    public void setGameOver() {
        this.gameOver = true;
    }

    /**
     * Returns true if game is over else returns false.
     *
     * @return boolean - return Game parameter gameOver
     */
    public boolean getGameover() {
        return this.gameOver;
    }

    /**
     * Method for checking if a square holds a mine.
     *
     * @param x - height coordinate
     * @param y - width coordinate
     * @return boolean - true if square if a mine else false
     */
    public boolean checkForMine(int x, int y) {
        return grid[y][x] == 1;
    }

    /**
     * Method for checking the number on neighbouring squares that hold a mine.
     *
     * @param x - height coordinate
     * @param y - width coordinate
     * @return minesFound - int number of neighbour squares that hold a mine.
     */
    public int checkNeighbours(int x, int y) {
        int minesFound;

        // Checks if middle coordinate holds a mine
        if (checkForMine(x, y)) {
            minesFound = -1;
        } else {
            minesFound = 0;
        }

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (checkInsideGrid(i, j)) {
                    if (checkForMine(i, j)) {
                        minesFound++;
                    }
                }
            }
        }
        return minesFound;
    }

    /**
     * Checks if given coordinate values are inside the game grid.
     *
     * @param x - height coordinate
     * @param y - width coordinate
     * @return boolean - true if x and y values are inside the grid
     */
    public boolean checkInsideGrid(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < WIDTH;
    }

    /**
     * Method to get WIDTH of the game.
     *
     * @return WIDTH - game width
     */
    public int getWIDTH() {
        return WIDTH;
    }

    /**
     * Creates a player
     *
     * @param name - name of the player
     */
    public void setPlayer(String name) {
        Player player = new Player(name);
        this.player = player;
    }

    /**
     * Method to get the player of the game.
     *
     * @return Player - player of the game
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns a String representation of the current game 1 equals a mine and 0
     * Used to print game situation to console.
     *
     * number 1 represents a mine and 0 a empty square.
     *
     * @return String - game situation as a String representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                sb.append(grid[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
