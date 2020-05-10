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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// scr package imports
import fi.villemanninen.game.Game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Graphical user interface for creating and playing minesweeper.
 *
 * @author Ville Manninen
 */
public class Gui extends Application {

    private Stage stage;
    private int length;
    private Game game = new Game(10);
    private Label playerTxt;
    private Label turnTxt;
    private ButtonFactory buttonFactory;
    private Image logo;
    private Image sick;

    /**
     * Executes the graphical user interface.
     *
     * @param primaryStage - Stage window
     * @throws java.lang.Exception -
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.length = game.getLength();
        this.buttonFactory = new ButtonFactory(this, game);

        try {
            this.logo = new Image(new FileInputStream("src/main/resources/biohazard.png"));
            this.sick = new Image(new FileInputStream("src/main/resources/sick.png"));
            stage.getIcons().add(logo);

        } catch (FileNotFoundException e) {
            System.out.println(e);

        }
        stage.setTitle("Corona Sweeper");
        stage.setScene(getStartScene());
        stage.show();
    }

    /**
     * Method to generate a starting scene.
     *
     * @return Scene - starting scene
     */
    public Scene getStartScene() {
        game.newGame();
        // Parent
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10));
        vb.setSpacing(5);

        //Header
        VBox vb2 = new VBox();
        vb2.setAlignment(Pos.CENTER);
        vb2.setPadding(new Insets(80));
        vb2.setSpacing(40);
        Label header1 = new Label("Corona Sweeper!");

        VBox headerVB = new VBox();
        headerVB.setAlignment(Pos.CENTER);
        headerVB.setSpacing(16);

        Label header2 = new Label("Do your best to avoid infected people");

        ImageView iView = new ImageView(sick);
        iView.setFitHeight(40);
        iView.setFitWidth(40);

        headerVB.getChildren().add(header2);
        headerVB.getChildren().add(iView);

        header1.setStyle("-fx-font-weight: bold");
        header2.setStyle("-fx-font-weight: bold");

        header1.setScaleX(4);
        header1.setScaleY(4);

        header2.setScaleX(2);
        header2.setScaleY(2);

        ImageView iView2 = new ImageView(logo);
        iView2.setFitHeight(180);
        iView2.setFitWidth(180);

        vb2.getChildren().add(iView2);
        vb2.getChildren().add(header1);
        vb2.getChildren().add(headerVB);

        vb.getChildren().add(vb2);

        // Name label and text field
        Label txt = new Label("Your name: ");
        txt.setScaleX(1.5);
        txt.setScaleY(1.5);

        TextField textField = new TextField();

        // New game button
        Button button = new Button("OK");

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println(e);
            game.setPlayer(textField.getText());
            stage.setScene(getGameScene());
        };
        button.setOnMouseClicked(eventHandler);
        vb.getChildren().add(button);

        // HBox for name label and text field
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(20);
        hb.setPadding(new Insets(10));

        hb.getChildren().add(txt);
        hb.getChildren().add(textField);

        vb.getChildren().add(hb);

        // Scene
        Scene startingScene = new Scene(vb, 1000, 800);
        return startingScene;

    }

    /**
     * Method for setting scene back to start scene.
     *
     */
    public void changeToStartScene() {
        stage.setScene(getStartScene());

    }

    /**
     * Method to generate a game scene.
     *
     * @return Scene - the game scene.
     */
    public Scene getGameScene() {
        // Parent 
        VBox parent = new VBox();
        parent.setAlignment(Pos.CENTER);
        parent.setPadding(new Insets(40));
        parent.setSpacing(10);

        // Top labels pane
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(20));

        // Return button
        Button btn = buttonFactory.createMiddleButton();

        // Top labels 
        Label txt1 = new Label("Player: " + game.getPlayer().getName());
        Label txt2 = new Label("People avoided: 0");

        txt1.setScaleX(1.5);
        txt1.setScaleY(1.5);

        txt2.setScaleX(1.5);
        txt2.setScaleY(1.5);

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
        Scene scene = new Scene(parent, 1000, 800);
        return scene;
    }

    /**
     * Method for setting scene back to start scene.
     *
     */
    public void changeToGameScene() {
        stage.setScene(getGameScene());

    }

    /**
     * Method to generate a length x length GridPane filled with buttons.
     *
     * @param gp - GridPane to store generated buttons
     */
    public void createSquares(GridPane gp) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                Button newButton = buttonFactory.createGridButton(i, j);
                gp.add(newButton, i, j);
            }
        }
    }

    /**
     * Method for changing the turn text on the GUI.
     *
     * @param text - Text that updated to the GUI
     */
    public void setTurnText(String text) {
        this.turnTxt.setText(text);
    }

    /**
     * Returns the GUI turn text as a String.
     *
     * @return String - turn text
     */
    public String getTurnText() {
        if (turnTxt != null) {
            return this.turnTxt.getText();
        }
        return "Sick people avoided: 0";
    }

    /**
     * Method for setting the player text on the GUI.
     *
     * @param text - Given text that is updated to the GUI
     */
    public void setPlayerText(String text) {
        this.playerTxt.setText(text);
    }

    /**
     * Returns player text as a String.
     *
     * @return String - player text of the GUI
     */
    public String getPlayerText() {
        if (playerTxt != null) {
            return this.playerTxt.getText();
        }
        return "";
    }
}
