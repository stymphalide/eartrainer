package view;

import java.util.*;

import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox; 
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

public class Level extends VBox {
    private List<ComboBox> cmbs;
    private logic.Level level;
    private Label levelProgress;
    private Label correctnessProgress;
    private ProgressBar levelBar;
    private ProgressBar correctnessBar;
    private Label time;

    public Level(logic.Level level, Button confirmButton) {
        super(50);
        this.level = level;
        createComboBoxes(); // ComboBoxes (This changes the this.cmbs instance variable)

        // Navigation
        confirmButton.setText("Confirm");

        HBox nav = new HBox(50, confirmButton);
        nav.setAlignment(Pos.CENTER);

        getChildren().addAll(
            createTitle("Level " + level.getLevelNumber()), 
            createScoreBar(), 
            createMainRow(), 
            nav);
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

    public Scene render() {
        Scene scene = new Scene(this, 700, 500);
        return scene;
    }

    public void viewFinished(logic.Level level, Button backToMenu, Button playAgain) {

        // Navigation
        playAgain.setText("Play Again");

        HBox nav = new HBox(50, backToMenu, playAgain);
        nav.setAlignment(Pos.CENTER);

        getChildren().setAll(
            createTitle("Level " + level.getLevelNumber() + " Over"), 
            createScoreBar(), 
            nav);
    }

    public void update() {
        updateScores();
        updateComboBoxes();
    }


    private HBox createTitle(String titleString) {
        // Title Row
        Label title = new Label(titleString);
        title.setFont(new Font(40));
    
        HBox titleRow = new HBox(50, title);
        titleRow.setAlignment(Pos.CENTER);
        return titleRow;
    }

    private HBox createScoreBar() {
        // Level Progress
        this.levelProgress = new Label("Progress: " + this.level.getTotalAnswers() + "/" +  this.level.getTotalQuestions());
        double levelProgress = (double)this.level.getTotalAnswers() / (double)this.level.getTotalQuestions();
        this.levelBar = new ProgressBar(levelProgress);

        // Correctness Progress
        double correctnessProgress = (double)this.level.getCorrectAnswers() / (double)this.level.getTotalAnswers();
        this.correctnessProgress = new Label("Correct: " + this.level.getCorrectAnswers() + "/" + this.level.getTotalAnswers());

        this.correctnessBar = new ProgressBar(correctnessProgress);
        this.correctnessBar.setStyle("-fx-accent: green;");

        // Time axis
        this.time = new Label(durationString());
        Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(1000),
            ae -> updateTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        HBox scoreBar = new HBox(50, this.time, this.levelProgress, this.levelBar, this.correctnessProgress, this.correctnessBar);
        scoreBar.setAlignment(Pos.CENTER);
        return scoreBar;
    }

    private void updateTime() {
        this.time.setText(durationString());
    }

    private String durationString() {
        long duration = this.level.getDuration().getSeconds();
        long seconds = duration % 60;
        long minutes = duration / 60;

        return ""  + minutes + ":" + seconds;
    }

    private void updateScores() {
        this.levelProgress.setText("Progress: " + this.level.getTotalAnswers() + "/" +  this.level.getTotalQuestions());
        
        double levelProgress = (double)this.level.getTotalAnswers() / (double)this.level.getTotalQuestions();
        this.levelBar.setProgress(levelProgress);
        
        this.correctnessProgress.setText("Correct: " + this.level.getCorrectAnswers() + "/" + this.level.getTotalAnswers());
        
        double correctnessProgress = (double)this.level.getCorrectAnswers() / (double)this.level.getTotalAnswers();
        this.correctnessBar.setProgress(correctnessProgress);
    }

    private HBox createMainRow() {
        Button card1Button = new Button("Listen to the first Interval");
        card1Button.setAlignment(Pos.CENTER);
        card1Button.setOnAction(e -> {
            this.level.play(this.level.getActiveQuestion().getCard1());
        });
        
        Button card2Button = new Button("Listen to the second Interval");
        card2Button.setAlignment(Pos.CENTER);
        card2Button.setOnAction(e -> {
            this.level.play(this.level.getActiveQuestion().getCard2());
        });


        VBox comboBoxes = new VBox(20);
        comboBoxes.getChildren().addAll(this.cmbs); // Inspired by [VarArgs]

        return new HBox(50, card1Button, card2Button, comboBoxes);
    }


    private void createComboBoxes() {
        this.cmbs = new ArrayList<ComboBox>();
        this.cmbs.add(createComboBox("Instrument", level.getAllowedInstruments()));
        this.cmbs.add(createComboBox("Order",      level.getAllowedOrders()));
        this.cmbs.add(createComboBox("Range",      level.getAllowedRanges()));
        this.cmbs.add(createComboBox("Interval",   level.getAllowedIntervals()));
    }

    private ComboBox<String> createComboBox(String placeholder, List<String> allowedFeatures) {
        double minWidth = 180;
        ComboBox<String> cmb = new ComboBox<String>();
        cmb.getItems().addAll(allowedFeatures);
        cmb.setPromptText("Choose " + placeholder);
        cmb.setMinWidth(minWidth);
        return cmb;
    }

    private void updateComboBoxes() {
        for(int i = 0; i < this.cmbs.size(); i++) {
            this.cmbs.get(i).setEditable(true);
            this.cmbs.get(i).getSelectionModel().clearSelection(-1);
            this.cmbs.get(i).setValue(null);
            this.cmbs.get(i).setEditable(false);
        }
    }
}





