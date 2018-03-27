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
import javafx.scene.paint.Color;        // The Color class is used to encapsulate colors in the default sRGB color space. [JavaFX]
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

    -- INSTANCE VARIABLES --
    The class has nine private instance variables:
    1. Scene scene

    2. VBox levelCol
    3. HBox nav

    4. List startLevels
    5. ImageView musicToggler
    6. boolean isMusicOn
    7. Button rankingButton
    8. Button helpButton

    The first attribute is the scene that is going to hold an instantiated Menu object.

    The next attributes is a holder for all the buttons to start the levels and their descriptions.
    Another important variable is the nav variable, which is going to hold the navigation bar.
    Those two attributes are needed since they are used in some helper methods.

    Next are just all the buttons that can be pressed in the screen. With the exception of the musicToggler, but this basically acts like a button as well.
    And the `isMusicOn` which holds the value whether the music should be playing. This value is crucial in order for the application when to start playing music
    and which icon to show.

    -- CONSTRUCTORS --
    The class has got one constructor:
    As arguments it mainly gets button objects. Those objects already have event handles attached to them. However they are not yet visible.
    This is why they are incorporated in the creation of the object.

    The constructor first assigns the arguments to the instance variables.
    It also sets the music to playing at the start.

    Then the title row is created. This is basically an image and a label in some fancy colour and spacing.

    Next the levelCol is instantiated and the private method `setUpLevels()` is called.

    Similarly the navigation bar is created in the next two lines.

    Lastly all those parts are added to the VBox that sits underneath the Menu object.

    -- METHODS --
    -- public --
    The class consists of three public methods:
    - Scene render()
    - boolean getMusic()
    - void toggleMusic()

    The render method creates a new Scene and assigns it to the instance variable if there is not already an existing one. Otherwise it just
    calls the setUp methods for the levels and the navigation bar. This makes sure, that all buttons are positioned correctly.
    In the end the scene is returned.
    Note that there is only one Menu created during one application.

    -- private --
    It also has five private methods:
    - void setUpNav()
    - void setUpLevels()
    - void showLevelDescription(Label label, String description)
    - void hideLevelDescription(Label label)
    - void getLevelDescription(int level)

    The `setUpNav` is responsible for correctly setting up the navigation bar, which consists of the music icon. 
    This changes depending on whether the music is currently playing. Next the help button gets a text. 
    The exit button calls the `Platform.exit()` method, which stops the application.
    All buttons get some margins applied.
    In the end all are added to a HBox which hold the buttons together.

    The `setUpLevels` method first clears the levelCol, which holds all the buttons and descriptions and then loops over all level Buttons.
    Each button gets a hover event listener, that shows the description for each level.
    Buttons and Descriptions are joind in a HBox and then the HBox is added to the levelCol.

    The last three methods are just methods that are called from within the event listeners of the hovers, to properly display and hide the descriptions.
*/
/*
    Main Sources: JavaFX API; 100 JavaFX Tutorials
*/

public class Menu extends VBox {
    private Scene scene;
    private HBox titleRow;
    private VBox levelCol;
    private HBox nav;
    private List<Button> startLevels;
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
