package view;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox; 
import javafx.scene.layout.HBox; 
import javafx.stage.Stage;

public class Level {
    private List<ComboBox> cmbs;
    private logic.Level level;
    private VBox root;

    public Level(logic.Level level, Button confirmButton) {
        this.level = level;
        createComboBoxes(); // ComboBoxes (This changes the this.cmbs instance variable)

        // Title Row
        Label title = new Label("Level " + level.getLevelNumber());
        title.setFont(new Font(40));
    
        HBox titleRow = new HBox(50, title);
        titleRow.setAlignment(Pos.CENTER);
        
        // Navigation
        confirmButton.setText("Confirm");

        HBox nav = new HBox(50, confirmButton);
        nav.setAlignment(Pos.CENTER);

        this.root = new VBox(50, titleRow, createScoreBar(), createMainRow(), nav);
    }

    public List<String> getComboBoxValues() {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < this.cmbs.size(); i++) {
            if(this.cmbs.get(i).getValue() == null)  {
                return null;
            } else {
                String value = "" + this.cmbs.get(i).getValue();
                list.add(value);
            }
        }
        return list;
    }
    public Scene renderActive() {      
        Scene scene = new Scene(this.root, 700, 500);
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

        this.root = new VBox(50);
        this.root.getChildren().addAll(titleRow, results, nav);

        Scene scene = new Scene(this.root, 700, 500);
        return scene;
    }



    private HBox createScoreBar() {
        // Level Progress
        Label progress = new Label("Progress: " + level.getTotalAnswers() + "/" +  level.getTotalQuestions());

        double levelProgress = (double)level.getTotalAnswers() / (double)level.getTotalQuestions();
        ProgressBar levelBar = new ProgressBar(levelProgress);

        // Correctness Progress
        double correctnessProgress = (double)level.getCorrectAnswers() / (double)level.getTotalAnswers();
        Label correctness = new Label("Correct: " + level.getCorrectAnswers() + "/" + level.getTotalAnswers());
        ProgressBar correctnessBar = new ProgressBar(correctnessProgress);
        correctnessBar.setStyle("-fx-accent: green;");

        HBox scoreBar = new HBox(50, progress, levelBar, correctness, correctnessBar);
        scoreBar.setAlignment(Pos.CENTER);
        return scoreBar;
    }


    private HBox createMainRow() {
        Button card1Button = new Button("Listen to the first Interval");
        card1Button.setAlignment(Pos.CENTER);
        card1Button.setOnAction(e -> {
            logic.Sound sound = new logic.Sound();
            sound.play(this.level.getActiveQuestion().getCard1());
        });
        
        Button card2Button = new Button("Listen to the second Interval");
        card2Button.setAlignment(Pos.CENTER);
        card2Button.setOnAction(e -> {
            logic.Sound sound = new logic.Sound();
            sound.play(this.level.getActiveQuestion().getCard2());
        });


        VBox comboBoxes = new VBox(20);
        comboBoxes.getChildren().addAll(this.cmbs); // Inspired by [VarArgs]

        return new HBox(50, card1Button, card2Button, comboBoxes);
    }

    private void createComboBoxes() {
        this.cmbs =  new ArrayList<ComboBox>();
        createComboBox("Instrument", level.getAllowedInstruments());
        createComboBox("Order",      level.getAllowedOrders());
        createComboBox("Range",      level.getAllowedRanges());
        createComboBox("Interval",   level.getAllowedIntervals());
    }


    private void createComboBox(String placeholder, List<String> allowedFeatures) {
        ComboBox<String> cmb = new ComboBox<String>();
        double minWidth = 180;
        cmb.setPromptText("Choose " + placeholder);
        for (int i = 0; i <  allowedFeatures.size(); i++) {
            String name = allowedFeatures.get(i);
            cmb.getItems().add(name);
        }
        cmb.setMinWidth(minWidth);
        this.cmbs.add(cmb);
    }
}