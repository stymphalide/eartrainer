package view;

/* classdoc
    This class renders the Menu view of the app.
    The View consists of three rows.
        - The Title (This is to be extended).
        - The Levels to choose from
        - A navigation bar with an exit button.
    
    

    The class consists of one public method:
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
import java.util.*;
import java.io.File;                    // An abstract representation of file and directory pathnames. [File API]
import java.io.IOException;             // Signals that an I/O exception of some sort has occurred. [IOException API]
import org.apache.commons.io.FileUtils; // General file manipulation utilities. [FileUtils API]

import javafx.application.Platform;     // Application platform support class. [JavaFX API] (The exit() method from this class is needed.)
import javafx.geometry.Pos;             // A set of values for describing vertical and horizontal positioning and alignment. [JavaFX API]
import javafx.geometry.Insets;          // A set of inside offsets for the 4 side of a rectangular area. [JavaFX API] (Used for setting margins)
import javafx.scene.Scene;              // The JavaFX Scene class is the container for all content in a scene graph. The background of the scene is filled as specified by the fill property. [JavaFX API]
import javafx.scene.text.Font;          // The Font class represents fonts, which are used to render text on screen. [JavaFX API]
import javafx.scene.control.Button;     // A simple Button Control. Can be a event Target and Contains text and/or graphic [JavaFX API].
import javafx.scene.control.Label;      // Label is a non-editable text control. A Label is useful for displaying text that is required to fit within a specific space, and thus may need to use an ellipsis or truncation to size the string to fit. [JavaFX API]
import javafx.scene.layout.VBox;        // VBox lays out its children in a single vertical column. If the vbox has a border and/or padding set, then the contents will be layed out within those insets. [JavaFX API]
import javafx.scene.layout.HBox;        // HBox lays out its children in a single horizontal row. If the hbox has a border and/or padding set, then the contents will be layed out within those insets. [JavaFX API]

public class Menu extends VBox {
    public Menu(List<Button> startLevels) {
        super(50);
        // Title row
        Label title = new Label("Eartrainer"); 
        title.setFont(new Font(40));
        title.setAlignment(Pos.CENTER);

        HBox titleRow = new HBox(50, title);
        titleRow.setAlignment(Pos.CENTER);

        // Level Setup
        VBox levels = new VBox(50);
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
            startLevels.get(i).setText("Level " + (i+1));
            // Inspired by: [Hover Effect Over Icon]
            startLevels.get(i).setOnMouseEntered(e -> {
                    showLevelDescription(levelLabel, levelDescription);
            });
            startLevels.get(i).setOnMouseExited(e -> {
                hideLevelDescription(levelLabel);
            });

            HBox levelRow = new HBox(50);
            levelRow.getChildren().addAll(startLevels.get(i), levelLabel);
            levelRow.setMargin(startLevels.get(i), new Insets(10, 50, 25, 30));
            levels.getChildren().add(levelRow);
        }


        // Navigation bar Setup
        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(e -> {
            Platform.exit();
        });

        HBox nav = new HBox(50, exitButton);
        nav.setMargin(exitButton, new Insets(20, 50, 40, 30));
        nav.setAlignment(Pos.BOTTOM_RIGHT);

        // SetUp the VBox.
        getChildren().addAll(titleRow, levels, nav);
    }

    public Scene render() {
        return new Scene(this, 700, 500);
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
