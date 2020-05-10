package fi.villemanninen.game;

import java.util.Random;

/**
 * Class for creating a new game of minesweeper represented by a 2D-array.
 *
 *
 * @author Ville Manninen
 */
public class Game {

    private int[][] grid;
    private Player player;
    private int length = 20;
    private int mines;
    private int turn;
    private boolean gameOver;
    private boolean gameWon;
    private boolean[][] flagged;

    /**
     * Creates a default game of minesweeper with WIDTH wide square grid. Width
     * is equal to 10. holding 30 mines starting at turn 1.
     */
    public Game() {
        this.grid = new int[this.length][this.length];
        this.flagged = new boolean[this.length][this.length];
        this.mines = 30;
        this.gameOver = false;
        this.gameWon = true;
        this.turn = 0;

    }

    /**
     * Creates a game of minesweeper with custom number of mines.
     *
     * @param mines - number of mines inside the gird
     */
    public Game(int mines) {
        this.grid = new int[length][length];
        this.mines = mines;
        this.gameOver = false;
        this.turn = 0;
    }

    /**
     * Fills the grid with int value 0 and then places 30 mines randomly. 1
     * represents a mine and 0 represents an empty square.
     *
     */
    public void newGame() {
        turn = 1;
        gameOver = false;
        gameWon = false;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                grid[i][j] = 0;
            }
        }
        int minesLeft = this.mines;
        Random random = new Random();
        while (minesLeft > 0) {
            int iRandom = random.nextInt(length);
            int jRandom = random.nextInt(length);
            if (grid[iRandom][jRandom] == 0) {
                grid[iRandom][jRandom] = 1;
                minesLeft--;
            }
        }
    }

    /**
     * Method for manually placing a mine on the grid. (Used mainly for
     * testing).
     *
     * @param x - width coordinate of the mine
     * @param y - height coordinate of the mine
     */
    public void putMine(int x, int y) {
        if (!isMine(x, y)) {
            grid[x][y] = 1;
            mines++;
        }
    }

    /**
     * Method for setting flagged value for a square.
     *
     * @param x - width coordinate
     * @param y - height coordinate
     * @param value - Boolean value
     */
    public void setFlagged(int x, int y, boolean value) {
        if (insideGrid(x, y)) {
            flagged[x][y] = value;
        }
    }

    /**
     * Method to advance to next turn. If incremented turn is equal to empty
     * squares of the game method calls setGameOver(); ending the game.
     *
     */
    public void nextTurn() {
        this.turn++;
    }

    /**
     * Method for checking if a square holds a mine.
     *
     * @param x - height coordinate
     * @param y - width coordinate
     * @return Boolean - true if square if a mine else false
     */
    public boolean isMine(int x, int y) {
        return grid[x][y] == 1;
    }

    /**
     * Method for checking the number on neighbouring squares that hold a mine.
     *
     * @param x - height coordinate
     * @param y - width coordinate
     * @return minesFound - number of neighbour squares that hold a mine.
     */
    public int checkNeighbours(int x, int y) {
        int minesFound;

        // Checks if middle coordinate holds a mine
        if (isMine(x, y)) {
            minesFound = -1;
        } else {
            minesFound = 0;
        }

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (insideGrid(i, j)) {
                    if (isMine(i, j)) {
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
     * @return Boolean - true if x and y values are inside the grid
     */
    public boolean insideGrid(int x, int y) {
        return x >= 0 && x < length && y >= 0 && y < length;
    }

    public int getTurn() {
        return this.turn;
    }

    public int getMines() {
        return this.mines;
    }

    public void setGameOver(boolean bool) {
        this.gameOver = bool;
    }

    public void setGameWon() {
        this.gameWon = true;
    }

    public boolean getGameover() {
        return this.gameOver;
    }

    public boolean getGameWon() {
        return this.gameWon;
    }

    public int getLength() {
        return length;
    }

    public void setPlayer(String name) {
        Player newPlayer = new Player(name);
        this.player = newPlayer;

    }

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
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                sb.append(grid[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
