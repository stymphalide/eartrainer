package view;

import javafx.application.Platform;
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

public class Menu {
    Scene scene;
    public Scene render(Button level1Start) {
        // This function is being called by the main method.
    	 Label title = new Label("Eartrainer"); 
        title.setFont(new Font(40));
        title.setAlignment(Pos.CENTER);

        // Level 1 Setup
        String level1Description = "Some description";
        final Label level1Label = new Label("");

        // https://stackoverflow.com/questions/20143548/hover-effect-over-icon
        level1Start.setText("Level 1");
        level1Start.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                showLevelDescription(level1Label, level1Description);
            }
        });
        level1Start.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                hideLevelDescription(level1Label);
            }
        });

        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(e -> {
            Platform.exit();
        });

        HBox titleRow = new HBox(50, title);
        titleRow.setAlignment(Pos.CENTER);

        HBox level1Row = new HBox(50, level1Start, level1Label);
        level1Row.setMargin(level1Start, new Insets(0, 50, 0, 50));

        HBox nav = new HBox(50, exitButton);
        nav.setMargin(exitButton, new Insets(0, 50, 0, 50));
        nav.setAlignment(Pos.BOTTOM_RIGHT);

        VBox root = new VBox(50, titleRow, level1Row, nav);

        return new Scene(root, 500, 500);
    }
    private void showLevelDescription(Label label, String description) {
        label.setText(description);
    }
    private void hideLevelDescription(Label label) {
        label.setText("");
    }
}