package view;

import java.util.*;
import java.io.File;                    // An abstract representation of file and directory pathnames. [File API]
import java.io.IOException;             // Signals that an I/O exception of some sort has occurred. [IOException API]
import org.apache.commons.io.FileUtils; // General file manipulation utilities. [FileUtils API]

import javafx.application.Platform;     // Application platform support class. [JavaFX API] (The exit() method from this class is needed.)
import javafx.geometry.Pos;             // A set of values for describing vertical and horizontal positioning and alignment. [JavaFX API]
import javafx.geometry.Insets;          // A set of inside offsets for the 4 side of a rectangular area. [JavaFX API] (Used for setting margins)
import javafx.event.ActionEvent;        // An Event representing some type of action. [JavaFX API]
import javafx.event.EventHandler;       // Handler for events of a specific class / type. [JavaFX API]
import javafx.scene.Scene;              // The JavaFX Scene class is the container for all content in a scene graph. The background of the scene is filled as specified by the fill property. [JavaFX API]
import javafx.scene.text.Font;          // The Font class represents fonts, which are used to render text on screen. [JavaFX API]
import javafx.scene.image.Image;        // The Image class represents graphical images and is used for loading images from a specified URL. [JavaFX]
import javafx.scene.image.ImageView;    // The ImageView is a Node used for painting images loaded with Image class. [JavaFX]
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;   // When mouse event occurs, the top-most node under cursor is picked and the event is delivered to it through capturing and bubbling phases described at EventDispatcher. [JavaFX]
import javafx.scene.control.Button;     // A simple Button Control. Can be a event Target and Contains text and/or graphic [JavaFX API].
import javafx.scene.control.Label;      // Label is a non-editable text control. A Label is useful for displaying text that is required to fit within a specific space, and thus may need to use an ellipsis or truncation to size the string to fit. [JavaFX API]
import javafx.scene.layout.VBox;        // VBox lays out its children in a single vertical column. If the vbox has a border and/or padding set, then the contents will be layed out within those insets. [JavaFX API]
import javafx.scene.layout.HBox;        // HBox lays out its children in a single horizontal row. If the hbox has a border and/or padding set, then the contents will be layed out within those insets. [JavaFX API]

/* classdoc
    This class renders the Menu view of the app. It inherits from the javaFX VBox.

    The View consists of three rows.
        - The title.
        - The levels to choose from
        - A navigation bar

    The class has one constructor:


    The class consists of three public methods:
    - public Scene render();
    
    The render method takes in a Button as argument, the reason the button is not created in this method is because of its event binding.
    The Menu is composed of a VBox and several HBox's that are then added to the scene
    The VBox adds elements in a column, the HBox adds elements in a row.

    Part of the first HBox is the Title of the application.
    The title is just a Label with a text of the application name.

    The second HBox consists of one button (which after click starts a new level).
    When hovering over the button a level description is shown.

    The last HBox consists of the Exit Button.
    The Exit button calls the `Platform.exit()` method, which stops the application.

    And two private methods:
    - private void showLevelDescription(Label label, String description);
    - private void hideLevelDescription(Label label);
    
    These are helper methods that are called from within a lambda expression.
*/
/*
    Main Sources: JavaFX API; 100 JavaFX Tutorials
*/

public class Menu extends VBox {
    private Scene scene;
    private List<Button> startLevels;
    private HBox titleRow;
    private VBox levelCol;
    private HBox nav;
    private ImageView musicToggler;
    private boolean isMusicOn;
    private Button rankingButton;
    private Button helpButton;

    public Menu(List<Button> startLevels, ImageView musicToggler, Button rankingButton, Button helpButton) {
        super(15);
        this.startLevels = startLevels;
        this.musicToggler = musicToggler;
        this.isMusicOn = true;
        this.rankingButton = rankingButton;
        this.helpButton = helpButton;
        // Title row
        Image icon = new Image("file:./resources/img/icon_rot.png", 150, 150, true, true);
        ImageView iconHolder=  new ImageView(icon);
        Label title = new Label("artrainer");
        title.setTextFill(Color.web("#feb76b"));
        title.setFont(new Font(40));

        this.titleRow = new HBox(0, iconHolder, title);
        this.titleRow.setMargin(iconHolder, new Insets(15, 0, -50, 0));
        this.titleRow.setMargin(title, new Insets(-10, 0, -50, -10));
        this.titleRow.setAlignment(Pos.CENTER);

        // Level Setup
        this.levelCol = new VBox(50);
        setUpLevels();
                
        this.nav = new HBox(30);
        setUpNav();
        
        // SetUp the VBox.
        getChildren().addAll(this.titleRow, this.levelCol, this.nav);
    }

    public Scene render() {
        if(this.scene == null) {
            this.scene = new Scene(this, 700, 600);
        } else {
            setUpLevels();
            setUpNav();
        }
        return this.scene;
    }

    public boolean getMusic() {
        return this.isMusicOn;
    }

    public void toggleMusic() {
        Image img;
        if (this.isMusicOn) {
            img = new Image("file:./resources/img/music_off_icon.png");
        } else {
            img = new Image("file:./resources/img/music_on_icon.png");
        }
        this.musicToggler.setImage(img);
        this.isMusicOn = !this.isMusicOn;
    }

    private void setUpNav() {
        // Music toggle icon
        Image img;
        if(this.isMusicOn) {
            img = new Image("file:./resources/img/music_on_icon.png");
        } else {
            img = new Image("file:./resources/img/music_off_icon.png");
        }
        this.musicToggler.setImage(img);
        this.musicToggler.setFitWidth(30);
        this.musicToggler.setFitHeight(30);
        // Help Text
        this.helpButton.setText("Help");
        // Exit 
        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(e -> {
            Platform.exit();
        });

        // Basic set up
        this.nav.setMargin(this.musicToggler, new Insets(0, 50, 40, 30));
        this.nav.setMargin(this.rankingButton, new Insets(0, 50, 40, 30));
        this.nav.setMargin(this.helpButton, new Insets(0, 50, 40, 30));
        this.nav.setMargin(exitButton, new Insets(0, 50, 40, 30));
        this.nav.setAlignment(Pos.BOTTOM_RIGHT);
        this.nav.getChildren().setAll(this.musicToggler, this.rankingButton, this.helpButton, exitButton);
    }

    private void setUpLevels() {
        this.levelCol.getChildren().clear();
        for(int i = 0; i < startLevels.size(); i++) {
            String description = "Not Found Description";
            // Inspired by: [How to Catch Exceptions]
            try {
                description = getLevelDescription(i+1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // In order to be used in a lambda method this needs to be final.
            final String levelDescription = description;
            final Label levelLabel = new Label("");
            //showLevelDescription(levelLabel, levelDescription);
            startLevels.get(i).setText("Level " + (i+1));
            // Inspired by: [Hover Effect Over Icon]
            startLevels.get(i).setOnMouseEntered(e -> {
                    showLevelDescription(levelLabel, levelDescription);
            });
            startLevels.get(i).setOnMouseExited(e -> {
                hideLevelDescription(levelLabel);
            });

            HBox levelRow = new HBox(0);
            levelRow.getChildren().addAll(startLevels.get(i), levelLabel);
            levelRow.setMargin(startLevels.get(i), new Insets(15, 10, 25, 20));
            this.levelCol.getChildren().add(levelRow);
        }
    }

    private void showLevelDescription(Label label, String description) {
        label.setText(description);
    }
    private void hideLevelDescription(Label label) {
        label.setText("");
    }

    private String getLevelDescription(int level) throws IOException {
        String path = "./resources/descriptions/level_" + level + ".txt";
        return FileUtils.readFileToString(new File(path), "UTF-8");
    }
 

}
