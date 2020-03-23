package fi.villemanninen.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Class for error screen if main Application fails to launch.
 *
 * @author Ville Manninen
 */
public class ErrorScreen extends Application {

    /**
     * Starts the error window.
     *
     * @param stage - primaryStage
     * @throws Exception - Error message
     */
    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Error in launching Minesweeper.");
        BorderPane bp = new BorderPane();
        bp.setCenter(label);
        Scene scene = new Scene(bp, 400, 400);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
}
