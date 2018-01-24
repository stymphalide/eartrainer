package view;

//private logic.Level logicLevel;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.layout.VBox; 
import javafx.scene.layout.HBox; 
import javafx.stage.Stage;

public class Level {
    public Scene renderActive(logic.Level level, Button confirmButton) {

        // Title Row
        Label title = new Label("Level " + level.getLevelNumber());
        title.setFont(new Font(40));
    
        HBox titleRow = new HBox(50, title);
        titleRow.setAlignment(Pos.CENTER);

        // Main Part
        //  Card 1
        /*"
        logic.Question question = level.getQuestion();
        logic.Card card1 = question.getCard1();
        logic.Card card2 = question.getCard2();
    */
        Button card1Button = new Button("Listen to the first Interval");
        Button card2Button = new Button("Listen to the second Interval");


        HBox mainRow = new HBox(50, card1Button, card2Button);


        // Navigation
        confirmButton.setText("Confirm");

        HBox nav = new HBox(50, confirmButton);
        nav.setAlignment(Pos.CENTER);


        VBox root = new VBox(50, titleRow, mainRow, nav);

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