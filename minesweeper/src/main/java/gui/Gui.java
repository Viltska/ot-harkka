package gui;

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
import main.Game;

/**
 * Graphical user interface for creating and playing minesweeper.
 *
 * @author Ville Manninen
 */
public class Gui extends Application {

    private static Stage stage;
    private static int length;
    private static Game game = new Game();
    private static Label playerTxt;
    private static Label turnTxt;
    private static Button buttons[][];

    /**
     * Executes the graphical user interface.
     *
     * @param primaryStage - Stage window
     */
    @Override
    public void start(Stage primaryStage) {
        Scene startingScene = getStartScene();
        this.stage = primaryStage;
        this.length = game.getLength();
        this.buttons = new Button[length][length];

        stage.setTitle("Minesweeper game 1.0");
        stage.setScene(startingScene);
        stage.show();
    }

    /**
     * Method to generate and return a starting scene.
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
        TextField txtField = new TextField();

        // New game button
        Button button = new Button("New Game");

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println(e);
            String player = txtField.getText();
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
        hb.getChildren().add(txtField);

        vb.getChildren().add(hb);

        // Scene
        Scene startingScene = new Scene(vb, 800, 800);
        return startingScene;

    }

    /**
     * Method to generate and return a game scene.
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
     * Method to create a GridPane filled with Buttons. calls method
     * createButton(int x, int y);
     *
     * @param gp - GridPane to store generated buttons
     */
    private void createSquares(GridPane gp) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
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
     * Method to handle a mouse events.
     *
     * @param x - x coordinate of the Button
     * @param y - y coordinate of the Button
     * @param pressedButton - Button that is pressed
     */
    private void leftClickButton(int x, int y, Button pressedButton) {
        game.nextTurn();
        if (game.checkForMine(x, y)) {
            //Change button color to red
            pressedButton.setStyle("-fx-background-color: #ff0000; ");
            pressedButton.setText("X");
            this.turnTxt.setText("Turn : GAME OVER");
            

        } else {
            pressedButton.setText("");
            if (game.checkNeighbours(x, y) > 0) {
                pressedButton.setText("" + game.checkNeighbours(x, y));
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
    private void rightClickButton(int x, int y, Button pressedButton) {
        // Right Click - player marks button as a mine
        if (pressedButton.getText().equals("!")) {
            pressedButton.setText("");
        } else {
            pressedButton.setText("!");
        }
    }
}
