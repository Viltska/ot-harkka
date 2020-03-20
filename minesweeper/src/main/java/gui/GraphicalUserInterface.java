package gui;

//JavaFX
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Game
import main.Game;

/**
 * Graphical user interface for creating and playing minesweeper.
 *
 * @author Ville Manninen
 */
public class GraphicalUserInterface extends Application {

    private static Stage stage;
    private static int WIDTH;
    private static Game game = new Game();
    private static Text playerTxt;
    private static Text turnTxt;
    private static Button buttons[][];

    /**
     * Executes the graphical user interface. Overrides method from Application.
     *
     * @param Primarystage - Stage window
     */
    @Override
    public void start(Stage Primarystage) {
        Scene startingScene = getStartScene();
        this.stage = Primarystage;
        this.WIDTH = game.getWIDTH();
        this.buttons = new Button[WIDTH][WIDTH];

        stage.setTitle("Minesweeper");
        stage.setScene(startingScene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(1);
        });
    }

    /**
     * Method to generate and return a starting scene. Creates a new game.
     *
     * @return Scene - starting scene
     */
    private Scene getStartScene() {
        TextField txtField = new TextField();
        Button button = new Button("New Game");
        Text txt = new Text("Player Name");

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println(e);
            String player = txtField.getText();
            game.newGame();
            game.setPlayer(player);
            stage.setScene(getGameScene());
        };
        button.setOnMouseClicked(eventHandler);

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(80));
        vb.setSpacing(40);

        vb.getChildren().add(button);
        vb.getChildren().add(txtField);
        vb.getChildren().add(txt);

        Scene startingScene = new Scene(vb, 800, 800);

        return startingScene;

    }

    /**
     * Method to generate and return a game scene. Calls method createSquares();
     *
     * @return Scene - the game scene.
     */
    private Scene getGameScene() {
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(40));
        vb.setSpacing(20);

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(20));

        Button btn = new Button("Return");

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println("Button :" + e.getButton());
            if (e.getButton() == MouseButton.PRIMARY) {
                System.out.println(e);
                stage.setScene(getStartScene());
            }
        };
        btn.setOnMouseClicked(eventHandler);

        Text txt1 = new Text("Player: " + game.getPlayer().getName());
        Text txt2 = new Text("Turn: " + game.getTurn());

        bp.setLeft(txt1);
        bp.setCenter(btn);
        bp.setRight(txt2);

        this.playerTxt = txt1;
        this.turnTxt = txt2;

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);

        GridPane gp = new GridPane();
        createSquares(gp);

        hb.getChildren().add(gp);

        vb.getChildren().add(bp);
        vb.getChildren().add(hb);

        Scene scene = new Scene(vb, 800, 800);
        return scene;
    }

    /**
     * Method to create a GridPane filled with Buttons. Creates Buttons using
     * method createButton(int x, int y);
     *
     * @param gp - GridPane to store generated buttons
     */
    private void createSquares(GridPane gp) {
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
    private Button createButton(int x, int y) {
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
            //  call method that disables all buttons
            if (!game.getGameover()) {
                // method call
                // set gameover true
            }

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
