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
    public Scene render(logic.Level level) {
        Label label = new Label("level " + level.getLevel());
        VBox root = new VBox(50);
        Label n = new Label("" + level.getN());

        Button button = new Button("Increase N");
        button.setOnAction(e -> {
            level.increaseN();
            n.setText("" + level.getN());
        });


        root.getChildren().addAll(label, button, n);

        Scene scene = new Scene(root, 500, 500);
        return scene;
    }
}