package view;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
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


        // Score Bar
        // Level Progress
        Label progress = new Label("Progress: " + level.getTotalAnswers() + "/" +  level.getTotalQuestions());

        double levelProgress = (double)level.getTotalAnswers() / (double)level.getTotalQuestions();
        ProgressBar levelBar = new ProgressBar(levelProgress);

        // Correctness Progress
        Label correctness = new Label("Correct: " + level.getCorrectAnswers() + "/" + level.getTotalAnswers());

        double correctnessProgress = (double)level.getCorrectAnswers() / (double)level.getTotalAnswers();
        ProgressBar correctnessBar = new ProgressBar(correctnessProgress);
        correctnessBar.setStyle("-fx-accent: green;");
        
        HBox scoreBar = new HBox(50, progress, levelBar, correctness, correctnessBar);
        scoreBar.setAlignment(Pos.CENTER);

        // Main Part
        
        // Listen
        Button card1Button = new Button("Listen to the first Interval");
        card1Button.setAlignment(Pos.CENTER);
        card1Button.setOnAction(e -> {
            logic.Sound sound = new logic.Sound();
            sound.play(level.getActiveQuestion().getCard1());
        });

        Button card2Button = new Button("Listen to the second Interval");
        card2Button.setAlignment(Pos.CENTER);
        card2Button.setOnAction(e -> {
            logic.Sound sound = new logic.Sound();
            sound.play(level.getActiveQuestion().getCard2());
        });

        // ComboBoxes
        double minWidth = 180;
        ComboBox<String> cmbInstruments = new ComboBox();
        setUpComboBox("Instruments", cmbInstruments, level.getAllowedInstruments());
        cmbInstruments.setPromptText("Choose Instrument");
        cmbInstruments.setMinWidth(minWidth);

        ComboBox<String> cmbOrders = new ComboBox();
        setUpComboBox("Orders", cmbOrders, level.getAllowedOrders());
        cmbOrders.setPromptText("Choose Order");
        cmbOrders.setMinWidth(minWidth);
        
        ComboBox<String> cmbRanges = new ComboBox();
        setUpComboBox("Ranges", cmbRanges, level.getAllowedRanges());
        cmbRanges.setPromptText("Choose Range");
        cmbRanges.setMinWidth(minWidth);
        
        ComboBox<String> cmbIntervals = new ComboBox();
        setUpComboBox("Intervals", cmbIntervals, level.getAllowedIntervals());
        cmbIntervals.setPromptText("Choose Interval");
        cmbIntervals.setMinWidth(minWidth);

        VBox comboBoxes = new VBox(20, 
                                   cmbInstruments, 
                                   cmbOrders, 
                                   cmbRanges, 
                                   cmbIntervals);

        HBox mainRow = new HBox(50, card1Button, card2Button, comboBoxes);


        // Navigation
        confirmButton.setText("Confirm");

        HBox nav = new HBox(50, confirmButton);
        nav.setAlignment(Pos.CENTER);


        VBox root = new VBox(50, titleRow, scoreBar, mainRow, nav);

        Scene scene = new Scene(root, 700, 500);
        return scene;
    }
    public Scene renderFinished(logic.Level level, Button backToMenu, Button playAgain) {
        Label title = new Label("Level " + level.getLevelNumber() + " Over");
        title.setFont(new Font(40));

        HBox titleRow = new HBox(50, title);
        titleRow.setAlignment(Pos.CENTER);

        // Results
        // Correctness Progress
        Label correctness = new Label("Correct/Wrong " + level.getCorrectAnswers() + "/" + level.getWrongAnswers());

        double correctnessProgress = (double)level.getCorrectAnswers() / (double)level.getTotalAnswers();
        ProgressBar correctnessBar = new ProgressBar(correctnessProgress);
        correctnessBar.setStyle("-fx-accent: green;");

        HBox results = new HBox(50, correctness, correctnessBar);
        results.setAlignment(Pos.CENTER);

        // Navigation
        playAgain.setText("Play Again");

        HBox nav = new HBox(50, backToMenu, playAgain);
        nav.setAlignment(Pos.CENTER);

        VBox root = new VBox(50);
        root.getChildren().addAll(titleRow, results, nav);

        Scene scene = new Scene(root, 700, 500);
        return scene;
    }
    private void setUpComboBox(String placeholder, ComboBox<String> cmb, List<String> allowedFeatures) {
        Label phLabel = new Label(placeholder);
        for (int i = 0; i <  allowedFeatures.size(); i++) {
            String name = allowedFeatures.get(i);
            cmb.getItems().add(name);
        }
    }
}