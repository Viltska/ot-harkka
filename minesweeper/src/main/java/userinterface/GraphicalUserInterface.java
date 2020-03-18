package userinterface;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.*;

/**
 * Graphical user interface for creating and playing minesweeper.
 *
 * @author Ville Manninen
 */
public class GraphicalUserInterface extends Application {

    private static Stage stage;
    private int WIDTH;
    private GameLogic game = new GameLogic();
    private Text playerTxt;
    private Text turnTxt;
    private Button buttons[][];

    @Override
    public void start(Stage primaryStage) {
        Scene startingScene = getStartScene();
        this.stage = primaryStage;
        this.WIDTH = game.getWIDTH();
        this.buttons = new Button[WIDTH][WIDTH];

        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        stage.setScene(startingScene);
        stage.show();
    }

    /**
     * Method to generate and return a starting scene. Includes a button "New
     * Game" - on action sets stage scene to gameScene.
     *
     * @return Scene - starting scene
     */
    public Scene getStartScene() {
        TextField txtField = new TextField();
        Button button = new Button("New Game");

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println(e);
            String player = txtField.getText();
            game.newGame();
            game.setPlayer(player);
            stage.setScene(getGameScene());
        };
        button.setOnMouseClicked(eventHandler);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setTop(button);
        root.setCenter(txtField);

        Scene startingScene = new Scene(root, 800, 800);

        return startingScene;

    }

    /**
     * Method to generate and return a game scene. Calls method createSquares();
     *
     * @return Scene - the game scene.
     */
    public Scene getGameScene() {

        BorderPane parent = new BorderPane();
        parent.setPadding(new Insets(10, 20, 10, 20));

        BorderPane bp = new BorderPane();

        Text txt1 = new Text("Player: " + game.getPlayer().getName());
        Text txt2 = new Text("Turn: " + game.getTurn());

        bp.setLeft(txt1);
        bp.setCenter(txt2);

        this.playerTxt = txt1;
        this.turnTxt = txt2;

        GridPane gp = new GridPane();
        createSquares(gp);

        parent.setTop(bp);
        parent.setCenter(gp);

        Scene scene = new Scene(parent, 800, 800);
        return scene;
    }

    /**
     * Method to create a GridPane filled with Buttons. Creates Buttons using
     * method createButton(int x, int y);
     *
     * @param gp - GridPane to store generated buttons
     */
    public void createSquares(GridPane gp) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Button newButton = createButton(i, j);
                gp.add(newButton, i, j);
            }
        }
    }

    /**
     * Method for creating a new Button.
     *
     * @param x - x coordinate of the created Button
     * @param y - y coordinate of the created Button
     * @return Button - JavaFX Button with correct parameters for the game
     */
    public Button createButton(int x, int y) {
        Button newButton = new Button();
        newButton.setFont(Font.font("verdana", FontWeight.BLACK, FontPosture.REGULAR, 12));
        newButton.setPrefHeight(30);
        newButton.setPrefWidth(30);

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println("Button :" + e.getButton());
            if (e.getButton() == MouseButton.PRIMARY) {
                System.out.println(e);
                leftClickButton(x, y, newButton);
            }
            if (e.getButton() == MouseButton.SECONDARY) {
                System.out.println(e);
                rightClickButton(x, y, newButton);
            }
        };
        newButton.setOnMouseClicked(eventHandler);
        //Stores the Buttons coordinates
        buttons[x][y] = newButton;

        return newButton;
    }

    /**
     * Method to handle a button on mouse click
     *
     * @param x - x coordinate of the Button
     * @param y - y coordinate of the Button
     * @param pressedButton - Button that is pressed
     */
    public void leftClickButton(int x, int y, Button pressedButton) {
        game.nextTurn();
        if (game.checkForMine(x, y)) {
            pressedButton.setStyle("-fx-background-color: #ff0000; ");
            pressedButton.setText("X");
            this.turnTxt.setText("Turn : GAME OVER");
            if (game.getGameover()) {
                // 
            }

        } else {
            int neighbouringMines = game.checkNeighbours(x, y);
            pressedButton.setText("");
            if (neighbouringMines > 0) {
                pressedButton.setText("" + neighbouringMines);
            }
            this.turnTxt.setText("Turn: " + game.getTurn());
            pressedButton.setDisable(true);

        }
        pressedButton.setDisable(true);

    }

    /**
     * Method to handle right click on a button.
     *
     * @param x - x coordinate of the Button
     * @param y - y coordinate of the Button
     * @param pressedButton - Button that is clicked
     */
    public void rightClickButton(int x, int y, Button pressedButton) {
        // Right Click - player marks button as a mine
        if (pressedButton.getText().equals("!")) {
            pressedButton.setText("");
        } else {
            pressedButton.setText("!");
        }

    }
}
