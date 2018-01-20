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
    public Scene render(logic.Level level, Button confirmButton) {
        if(level.isFinished()) return renderFinishedLevel(level);
        else return renderActiveLevel(level, confirmButton); 
    }
    private Scene renderActiveLevel(logic.Level level, Button confirmButton) {
        Label label = new Label("level " + level.getLevel());
        VBox root = new VBox(50);
        Label n = new Label("" + level.getN());

        confirmButton.setText("Increase N");

        root.getChildren().addAll(label, confirmButton, n);

        Scene scene = new Scene(root, 500, 500);
        return scene;
    }
    private Scene renderFinishedLevel(logic.Level level) {
        VBox root = new VBox(50);

        Label label = new Label("Game Finished");

        root.getChildren().addAll(label);

        Scene scene = new Scene(root, 500, 500);
        return scene;
    }
}