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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
     * @return
     */
    public Scene getGameScene() {
        GridPane gp = new GridPane();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button btn = new Button(i + ":" + j);
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    
                    @Override
                    public void handle(ActionEvent event) {
                    }
                });
                
                gp.add(btn, i, j);
            }
        }
        Scene gameScene = new Scene(gp, 800, 800);
        
        return gameScene;
    }
}
