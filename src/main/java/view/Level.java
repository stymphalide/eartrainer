package view;

//private logic.Level logicLevel;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent; 
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.layout.VBox; 
import javafx.scene.layout.HBox; 
import javafx.stage.Stage;

public class Level {
    public Scene renderActive(logic.Level level, Button confirmButton) {
        Label label = new Label("level " + level.getLevelNumber());
        VBox root = new VBox(50);
        Label n = new Label("1");

        confirmButton.setText("Increase N");

        root.getChildren().addAll(label, confirmButton, n);

        Scene scene = new Scene(root, 700, 500);
        return scene;
    }
    public Scene renderFinished(logic.Level level, Button backToMenu, Button playAgain) {
        VBox root = new VBox(50);

        playAgain.setText("Play Again");

        Label label = new Label("Game Finished");

        root.getChildren().addAll(label, backToMenu, playAgain);

        Scene scene = new Scene(root, 700, 500);
        return scene;
    }
}