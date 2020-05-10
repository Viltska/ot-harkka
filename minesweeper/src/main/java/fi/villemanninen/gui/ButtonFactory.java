package fi.villemanninen.gui;

import fi.villemanninen.game.Game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Class for creating and managing buttons for the GUI.
 *
 * @author Ville Manninen
 */
public class ButtonFactory {

    private Gui gui;
    private Game game;
    private Button buttons[][];

    private Image happy;
    private Image sad;
    private Image covid;
    private Image sick;

    /**
     * Creates a new ButtonFactory class.
     *
     * @param gui - Graphical user interface class
     * @param game - Game class
     */
    public ButtonFactory(Gui gui, Game game) {
        this.gui = gui;
        this.game = game;
        this.buttons = new Button[game.getLength()][game.getLength()];
        try {
            this.happy = new Image(new FileInputStream("src/main/resources/happyguru.png"));
            this.sad = new Image(new FileInputStream("src/main/resources/sadguru.png"));
            this.covid = new Image(new FileInputStream("src/main/resources/covid.png"));
            this.sick = new Image(new FileInputStream("src/main/resources/sick.png"));

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Method for creating a new Button used inside the grid.
     *
     * @param x - x coordinate of the created Button
     * @param y - y coordinate of the created Button
     * @return Button - JavaFX Button with correct parameters for the game
     */
    public Button createGridButton(int x, int y) {
        Button newButton = new Button();
        newButton.setFont(Font.font("verdana", FontWeight.BLACK, FontPosture.REGULAR, 12));
        newButton.setPrefHeight(30);
        newButton.setPrefWidth(30);

        ImageView iView = new ImageView();
        newButton.setGraphic(iView);

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println("Button :" + e.getButton());

            // Left click
            if (e.getButton() == MouseButton.PRIMARY) {

                leftClickButton(x, y, newButton);
            }
            // Right click
            if (e.getButton() == MouseButton.SECONDARY) {

                rightClickButton(x, y, newButton);
            }
        };
        newButton.setOnMouseClicked(eventHandler);
        buttons[x][y] = newButton;
        return newButton;
    }

    /**
     * Method for creating a middle button (return button).
     *
     * @return - Button - JavaFX Button with desired parameters
     */
    public Button createMiddleButton() {
        Button middleButton = new Button();

        ImageView iView = new ImageView(sad);
        iView.setFitHeight(100);
        iView.setFitWidth(100);
        middleButton.setGraphic(iView);

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println("Button :" + e.getButton());
            if (e.getButton() == MouseButton.PRIMARY) {
                gui.changeToStartScene();
            }
        };

        middleButton.setOnMouseClicked(eventHandler);

        return middleButton;
    }

    /**
     * Method for creating a start button.
     *
     * @return - Button - JavaFX Button with desired parameters
     *
     */
    public Button createStartButton() {
        Button startButton = new Button();

        ImageView iView = new ImageView(happy);
        iView.setFitHeight(100);
        iView.setFitWidth(100);
        startButton.setGraphic(iView);

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            gui.changeToGameScene();
        };

        startButton.setOnMouseClicked(eventHandler);

        return startButton;

    }

    /**
     * Method to handle left click on a square button.
     *
     * @param x - x coordinate of the pressedButton
     * @param y - y coordinate of the pressedButton
     * @param pressedButton - Button that is pressed
     */
    public void leftClickButton(int x, int y, Button pressedButton) {
        if (game.getGameover() == false && game.getGameWon() == false) {
            game.nextTurn();

            if (game.isMine(x, y)) {
                gui.setTurnText("You got Infected :(");

                if (game.getGameover() == false) {
                    game.setGameOver(true);
                    revealMines();
                }

            } else {
                pressedButton.setText(" ");
                handleEmptySquare(x, y);
                gui.setTurnText("Sick people avoided: " + (game.getTurn() - 1));
            }

            pressedButton.setDisable(true);
            updateGameWon();
        }
    }

    /**
     * Method to handle right click on a square button.
     *
     * @param x - width coordinate
     * @param y - height coordinate
     * @param pressedButton - Button that is pressed
     */
    public void rightClickButton(int x, int y, Button pressedButton) {

        if (game.getGameover() == false && game.getGameWon() == false) {
            if (pressedButton.getText().equals("!")) {
                pressedButton.setText("");
            } else {
                pressedButton.setText("!");
            }
        }
    }

    /**
     * Method to handle right click on an empty square. Calls recursive method
     * openSquaresRecursive.
     *
     * @param x - width coordinate of the starting click
     * @param y - height coordinate of the starting click
     */
    public void handleEmptySquare(int x, int y) {
        boolean[][] visited = new boolean[game.getLength()][game.getLength()];
        openSquaresRecursive(x, y, visited);

    }

    /**
     * Recursive method for right clicking connected squares of the starting
     * square.
     *
     * @param x - width coordinate
     * @param y - height coordinate
     * @param visited - Boolean 2D-array of visited squares
     */
    public void openSquaresRecursive(int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 2; j < y + 2; j++) {
                // If inside grid and not visited
                if (game.insideGrid(i, j) && !visited[i][j]) {
                    // If no neighbours and not holding a mine
                    if (game.checkNeighbours(i, j) == 0 && !game.isMine(i, j)) {
                        buttons[i][j].setDisable(true);
                        openSquaresRecursive(i, j, visited);
                    } else {
                        // If button is not disabled and not marked as a mine by user
                        if (!buttons[i][j].isDisable() && !buttons[i][j].getText().equals("!")) {
                            // Edit button text to show number of neighbouring mines
                            if (game.checkNeighbours(i, j) > 0 && !game.isMine(i, j)) {
                                buttons[i][j].setText("" + game.checkNeighbours(i, j));
                                buttons[i][j].setDisable(true);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to reveal all mines.
     *
     */
    public void revealMines() {

        for (int i = 0; i < game.getLength(); i++) {
            for (int j = 0; j < game.getLength(); j++) {
                if (game.isMine(i, j)) {
                    ImageView iView = new ImageView(covid);
                    iView.setFitHeight(14);
                    iView.setFitWidth(14);

                    Button current = buttons[i][j];
                    current.setText("");
                    current.setStyle("-fx-background-color: #ff0000; ");
                    current.setGraphic(iView);
                }
            }
        }

    }

    /**
     * Method for checking if the game has been won.
     */
    public void updateGameWon() {
        int mines = game.getMines();
        int squaresLeft = 0;

        for (int i = 0; i < game.getLength(); i++) {
            for (int j = 0; j < game.getLength(); j++) {
                if (!buttons[i][j].isDisabled()) {
                    squaresLeft++;
                }
            }
        }
        if (squaresLeft == mines) {
            game.setGameWon();
            gui.setTurnText("You avoided everyone! You win!");
        }
    }
}
