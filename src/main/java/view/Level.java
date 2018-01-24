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
        
        // Listen
        Button card1Button = new Button("Listen to the first Interval");
        card1Button.setAlignment(Pos.CENTER);
        Button card2Button = new Button("Listen to the second Interval");
        card1Button.setAlignment(Pos.CENTER);
        
        // ComboBoxes
        ComboBox<String> cmbInstruments = new ComboBox();
        setUpComboBox("Instruments", cmbInstruments, level.getAllowedInstruments());

        ComboBox<String> cmbOrders = new ComboBox();
        setUpComboBox("Orders", cmbOrders, level.getAllowedOrders());
        
        ComboBox<String> cmbRanges = new ComboBox();
        setUpComboBox("Ranges", cmbRanges, level.getAllowedRanges());
        
        ComboBox<String> cmbIntervals = new ComboBox();
        setUpComboBox("Intervals", cmbIntervals, level.getAllowedIntervals());

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
    private void setUpComboBox(String placeholder, ComboBox<String> cmb, List<? extends logic.Feature> allowedFeatures) {
        Label phLabel = new Label(placeholder);
        for (int i = 0; i <  allowedFeatures.size(); i++) {
            String name = allowedFeatures.get(i).getNameString();
            cmb.getItems().add(name);
        }
    }

}