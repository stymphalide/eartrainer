package view;

import logic.Level;

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

public class AfterLevel {
    public Scene render(logic.Level level) {
        Label label = new Label("Game Over");
        VBox root = new VBox(50);
        root.getChildren().add(label);

        Scene scene = new Scene(root, 500, 500);
        return scene;
    }
}