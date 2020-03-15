package main;

import java.util.Random;

public class Game {

    private static int[][] grid;
    private static Player player;
    private static final int WIDTH = 10;
    private static int mines;
    private static int turn;
    private static boolean gameover;

    /**
     * Creates a single instance of a game of Minesweeper method newGame() needs
     * to be called to initialize the game correctly
     */
    public Game() {
        this.grid = new int[WIDTH][WIDTH];
        this.mines = 30;
        this.gameover = false;
        this.turn = 1;

    }

    /**
     * Starts a new game of Minesweeper 20x20 int 2D-array filled with 30 mines
     * mines are represented with a number 1 and empty squares as 0
     *
     * Mines are placed randomly using java.util.Random with a int bound of
     * WIDTH
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
     * Creates a player for the game
     *
     * @param name - name of the Player
     */
    public void setPlayer(String name) {
        Player player = new Player(name);
        this.player = player;
    }

    /**
     * Method returns player of the game and returns null if no player is set
     *
     * @return Player - player of the game
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Adds a new turn, if all empty squares are found calls method
     * setGameOver();
     */
    public void newTurn() {
        turn++;
        if (turn == ((WIDTH * WIDTH) - this.mines)) {
            setGameOver();
        }
    }

    /**
     * Returns the current number of turn
     *
     * @return turn - current turn
     */
    public int getTurn() {
        return this.turn;
    }

    /**
     * Method to set gameover to true;
     */
    public void setGameOver() {
        this.gameover = true;
    }

    /**
     * Returns true if game is over
     *
     * @return boolean - return Game parameter gameover
     */
    public boolean getGameover() {
        return this.gameover;
    }

    /**
     * Method for checking if a square holds a mine
     *
     * @param x int value for the height coordinate
     * @param y int value for the width coordinate
     * @return true if square if a mine else returns false
     */
    public boolean checkForMine(int x, int y) {

        return grid[y][x] == 1;

    }

    /**
     * Method for checking the number on neighbouring squares that hold a mine
     *
     * @param x int value for the height coordinate
     * @param y int value for the width coordinate
     * @return int number of neighbour squares that hold a mine
     */
    public int checkNeighbours(int x, int y) {
        int mines = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (checkInsideGrid(i, j)) {

                    if (checkForMine(i, j)) {
                        mines++;
                    }
                }
            }
        }
        if (checkForMine(x, y)) {
            mines--;
        }
        return mines;
    }

    /**
     * Checks if given coordinate values are inside the game grid
     *
     * @param x int value for the height coordinate
     * @param y int value for the width coordinate
     * @return true if x and y values are inside the grid
     */
    public boolean checkInsideGrid(int x, int y) {
        return x >= 0 && x <= WIDTH && y >= 0 && y <= WIDTH;
    }

    /**
     * Prints the game to console
     */
    public void consolePrintGame() {

        if (getPlayer() != null) {
            System.out.println("Player: " + player.getName() + " Turn: " + this.turn);
        } else {
            System.out.println("Turn: " + this.turn);
        }
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * Returns a String representation of the current game 1 equals a mine and 0
     * equals no mine Overrides the method toString()
     *
     * @return String representation of the game
     */
    @Override
    public String toString() {
        return "PLACE HOLDER";
    }
}
