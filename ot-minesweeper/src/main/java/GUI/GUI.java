/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Game;

/**
 * Graphical user interface for creating and playing minesweeper.
 *
 * @author Ville Manninen
 */
public class GUI extends Application {

    private static Stage stage;
    private Game game = new Game();

    @Override
    public void start(Stage primaryStage) {
        Scene startingScene = getStartScene();
        this.stage = primaryStage;

        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        stage.setScene(startingScene);
        stage.show();
        if (game.getGameover()) {
            stage.setScene(startingScene);
        }
    }

    /**
     * Method to generate and return a starting scene. Includes a button "New
     * Game" - on action sets stage scene to gameScene.
     *
     * @return Scene - starting scene
     */
    public Scene getStartScene() {
        Button newGameBtn = new Button("New Game");
        newGameBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                game.newGame();
                stage.setScene(getGameScene());
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(newGameBtn);

        Scene startingScene = new Scene(root, 800, 800);

        return startingScene;

    }

    /**
     * Method to generate and return a game scene
     *
     * @return Scene - the game scene.
     */
    public Scene getGameScene() {
        VBox vb = new VBox();

        //VBox Children
        Text turnTxt = new Text("Turn: " + game.getTurn());
        GridPane gp = new GridPane();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Button btn;
                if (game.checkForMine(i, j)) {
                    btn = new Button("  ");
                    btn.setPrefHeight(30);
                    btn.setPrefWidth(30);
                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            btn.setText("X");
                            btn.setDisable(true);
                            game.nextTurn();
                            turnTxt.setText("GAME OVER!");
                            game.setGameOver();
                        }
                    });
                } else {
                    btn = new Button("  ");
                    btn.setPrefHeight(30);
                    btn.setPrefWidth(30);
                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            btn.setText("O");
                            btn.setDisable(true);
                            game.nextTurn();
                            turnTxt.setText("Turn: " + game.getTurn());
                        }
                    });
                }
                gp.add(btn, i, j);
            }
        }
        
        vb.getChildren().add(turnTxt);
        vb.getChildren().add(gp);

        Scene gameScene = new Scene(vb, 800, 800);

        return gameScene;
    }
}
