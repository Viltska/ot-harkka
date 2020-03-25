package fi.villemanninen.gui;

//JavaFX
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

// scr package imports
import fi.villemanninen.game.Game;

/**
 * Graphical user interface for creating and playing minesweeper.
 *
 * @author Ville Manninen
 */
public class Gui extends Application {

    private static Stage stage;
    private static int length;
    private static Game game = new Game(50);
    private static Label playerTxt;
    private static Label turnTxt;
    private static Button buttons[][];

    /**
     * Executes the graphical user interface.
     *
     * @param primaryStage - Stage window
     * @throws java.lang.Exception -
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene startingScene = getStartScene();
        this.stage = primaryStage;
        this.length = game.getLength();

        stage.setTitle("Minesweeper game 1.0");
        stage.setScene(startingScene);
        stage.show();

    }

    /**
     * Method to generate a starting scene.
     *
     * @return Scene - starting scene
     */
    private Scene getStartScene() {
        // Parent
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(80));
        vb.setSpacing(40);

        // Name label and text field
        Label txt = new Label("Name:");
        TextField textField = new TextField();
        if (playerTxt != null) {
            String currentName = game.getPlayer().getName();
            textField.setText(currentName);
        }

        // New game button
        Button button = new Button("New Game");

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println(e);
            String player = textField.getText();
            game.newGame();
            game.setPlayer(player);
            stage.setScene(getGameScene());
        };
        button.setOnMouseClicked(eventHandler);

        vb.getChildren().add(button);

        // HBox for name label and text field
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(20);
        hb.setPadding(new Insets(40));

        hb.getChildren().add(txt);
        hb.getChildren().add(textField);

        vb.getChildren().add(hb);

        // Scene
        Scene startingScene = new Scene(vb, 800, 800);
        return startingScene;

    }

    /**
     * Method to generate a game scene,
     *
     * @return Scene - the game scene.
     */
    private Scene getGameScene() {
        // Parent 
        VBox parent = new VBox();
        parent.setAlignment(Pos.CENTER);
        parent.setPadding(new Insets(40));
        parent.setSpacing(20);

        // Top labels pane
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(20));

        // Return button
        Button btn = new Button("Return");

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println("Button :" + e.getButton());
            if (e.getButton() == MouseButton.PRIMARY) {
                System.out.println(e);
                stage.setScene(getStartScene());
            }
        };
        btn.setOnMouseClicked(eventHandler);

        // Top labels 
        Label txt1 = new Label("Player: " + game.getPlayer().getName());
        Label txt2 = new Label("Turn: " + game.getTurn());

        bp.setLeft(txt1);
        bp.setCenter(btn);
        bp.setRight(txt2);

        this.playerTxt = txt1;
        this.turnTxt = txt2;

        // Mine grid
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);

        GridPane gp = new GridPane();
        createSquares(gp);
        hb.getChildren().add(gp);

        // Set parent children
        parent.getChildren().add(bp);
        parent.getChildren().add(hb);

        // Scene
        Scene scene = new Scene(parent, 800, 800);
        return scene;
    }

    /**
     * Method to generate a length x length GridPane filled with buttons.
     *
     * @param gp - GridPane to store generated buttons
     */
    private void createSquares(GridPane gp) {
        this.buttons = new Button[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                Button newButton = createButton(i, j);
                gp.add(newButton, i, j);
            }
        }
    }

    /**
     * Method for creating a new Button used inside the grid.
     *
     * @param x - x coordinate of the created Button
     * @param y - y coordinate of the created Button
     * @return Button - JavaFX Button with correct parameters for the game
     */
    private Button createButton(int x, int y) {
        Button newButton = new Button();
        newButton.setFont(Font.font("verdana", FontWeight.BLACK, FontPosture.REGULAR, 12));
        newButton.setPrefHeight(30);
        newButton.setPrefWidth(30);

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println("Button :" + e.getButton());
            // Left click
            if (e.getButton() == MouseButton.PRIMARY) {
                System.out.println(e);
                leftClickButton(x, y, newButton);
            }
            // Right click
            if (e.getButton() == MouseButton.SECONDARY) {
                System.out.println(e);
                rightClickButton(x, y, newButton);
            }
        };
        newButton.setOnMouseClicked(eventHandler);
        // Add button to array
        buttons[x][y] = newButton;

        return newButton;
    }

    /**
     * Method to handle left click.
     *
     * @param x - x coordinate of the pressedButton
     * @param y - y coordinate of the pressedButton
     * @param pressedButton - Button that is pressed
     */
    private void leftClickButton(int x, int y, Button pressedButton) {
        if (game.getGameover() == false) {
            game.nextTurn();

            if (game.isMine(x, y)) {
                // Color red
                pressedButton.setStyle("-fx-background-color: #ff0000; ");
                pressedButton.setText("X");
                this.turnTxt.setText("GAME OVER!");

                if (game.getGameover() == false) {
                    game.setGameOver(true);
                    revealMines();
                }

            } else {
                pressedButton.setText(" ");
                handleEmptySquare(x, y);
                this.turnTxt.setText("Turn: " + game.getTurn());
            }

            pressedButton.setDisable(true);
        }
    }

    /**
     * Method to handle right click.
     *
     * @param pressedButton - Button that is pressed
     */
    private void rightClickButton(int x, int y, Button pressedButton) {
        if (game.getGameover() == false) {
            if (pressedButton.getText().equals("!")) {
                pressedButton.setText("");
                game.setFlagged(x, y, false);
                game.updateGameWon();
            } else {
                pressedButton.setText("!");
                game.setFlagged(x, y, true);
                game.updateGameWon();
            }

        }
    }

    /**
     * Method to reveal all mines.
     *
     */
    private void revealMines() {
        for (int i = 0; i < game.getLength(); i++) {
            for (int j = 0; j < game.getLength(); j++) {
                if (game.isMine(i, j)) {
                    Button current = buttons[i][j];
                    current.setStyle("-fx-background-color: #ff0000; ");
                    current.setText("X");
                }
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
        boolean[][] visited = new boolean[length][length];
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
}
