package view;

import java.util.*;

import javafx.util.Duration;                // A class that defines a duration of time. [JavaFX API]
import javafx.geometry.Pos;                 // A set of values for describing vertical and horizontal positioning and alignment. [JavaFX API]
import javafx.scene.Scene;                  // The JavaFX Scene class is the container for all content in a scene graph. The background of the scene is filled as specified by the fill property. [JavaFX API]
import javafx.scene.text.Font;              // The Font class represents fonts, which are used to render text on screen. [JavaFX API]
import javafx.scene.control.Button;         // A simple Button Control. Can be a event Target and Contains text and/or graphic [JavaFX API].
import javafx.scene.control.Label;          // Label is a non-editable text control. A Label is useful for displaying text that is required to fit within a specific space, and thus may need to use an ellipsis or truncation to size the string to fit. [JavaFX API]
import javafx.scene.control.ComboBox;       // An implementation of the ComboBoxBase abstract class for the most common form of ComboBox [JavaFX API]
import javafx.scene.control.ProgressBar;    // A specialization of the ProgressIndicator which is represented as a horizontal bar. [JavaFX API]
import javafx.scene.image.ImageView;        // The ImageView is a Node used for painting images loaded with Image class. [JavaFX API]
import javafx.scene.layout.VBox;            // VBox lays out its children in a single vertical column. If the vbox has a border and/or padding set, then the contents will be layed out within those insets. [JavaFX API]
import javafx.scene.layout.HBox;            // HBox lays out its children in a single horizontal row. If the hbox has a border and/or padding set, then the contents will be layed out within those insets. [JavaFX API]
import javafx.animation.Animation;          // The class Animation provides the core functionality of all animations used in the JavaFX runtime. [JavaFX API]
import javafx.animation.Timeline;           // A Timeline can be used to define a free form animation of any WritableValue, e.g. all JavaFX Properties. [JavaFX API]
import javafx.animation.KeyFrame;           // Defines target values at a specified point in time for a set of variables that are interpolated along a Timeline. [JavaFX API]


/* classdoc
    This class is responsible for showing the level screen. It inherits from the VBox component.

    The view has four rows. The title, the score bar, the main row and a navigation bar.



    -- INSTANCE VARIABLES --
    there are six variables:
    - List<ComboBox> cmbs
    - Label levelProgress
    - Label correctnessProgress
    - ProgressBar levelBar
    - ProgressBar correctnessBar
    - Label time
    - logic.Level level

    the first five variables are javaFX components that are needed in different parts of the level.
    The last variable is the logic.Level object that is associated with that view.

    -- CONSTRUCTOR --
    Here the class is constructed giving the logic.Level class and the confirmButton which already has a button
    attached to it.
    It assigns the level to the instance variable.
    Then creates the four combo boxes.
    Adds the confirm button to a HBox.

    In the end the title row is created, as well as the score bar, the main row and the navigation bar.
    All those components are HBoxes that are added to the view.Level class as children.

    -- METHODS --
    -- public --
    There are three public methods:
    - Scene render()
    - void viewFinished(logic.Level level, Button backToMenu, Button playAgain, ImageView musicToggler)
    - void update()

    The render method creates a scene for that level and adds the view.Level object to it.

    The viewFinished method drastically changes the look of the window.
    In the end it shows a title, the output of the level and a navigation bar.
    It gets some buttons as inputs, those are used in the navigation bar.
    Then it sets the children of the view.Menu object to a new title, adds the score bar and adds the new navigation bar.

    the update method calls the two private methods that update the score and reset the combo boxes.
    -- private --
    There are nine private methods.
    1. HBox createTitle(String titleString)
    2. HBox createScoreBar()
    3. void updateTime()
    4. String durationString()
    5. void updateScores()
    6. HBox createMainRow()
    7. void createComboBoxes()
    8. ComboBox<String> createComboBox(String placeholder, List<String> allowedFeatures)
    9. void updateComboBoxes()

    The first method creates a label with the string given as an argument.

    The second method creates the score bar, this consists of a timer and two progress bars. 
    One to show how many questions have been answered and one to show how many have been correct
    Everything that changes over time and after one question is saved in an instance variable.

    The third and fourth methods are helper methods for the timer. They are responsible for updating the timer every second.
    
    The fifth method changes the correctness label and the number of answered questions.

    The sixth method is responsible for creating the main row of the level. It consists of two buttons for the intervals (card1, card2)
    and four comboboxes.
    The two cards both have events attached that cause the interval to play.
    The four comboboxes are just a list of comboboxes one for each feature.

    This list is created in the seventh method and written to the cmbs instance variable.
    The last method is a helper method for creating the comboBoxes. Those are created by reading the allowed features for each of those four features.

*/
public class Level extends VBox {
    private List<ComboBox> cmbs;
    private Label levelProgress;
    private Label correctnessProgress;
    private ProgressBar levelBar;
    private ProgressBar correctnessBar;
    private Label time;
    private logic.Level level;

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
        Scene scene = new Scene(this, 700, 600);
        return scene;
    }

    public void viewFinished(Button backToMenu, Button playAgain, ImageView musicToggler) {

        // Navigation
        playAgain.setText("Play Again");

        HBox nav = new HBox(50, musicToggler, backToMenu, playAgain);
        nav.setAlignment(Pos.CENTER);

        getChildren().setAll(
            createTitle("Level " + this.level.getLevelNumber() + " Over"), 
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

