package view;

import javafx.scene.Scene;              // The JavaFX Scene class is the container for all content in a scene graph. The background of the scene is filled as specified by the fill property. [JavaFX API]
import javafx.scene.layout.VBox;        // VBox lays out its children in a single vertical column. If the vbox has a border and/or padding set, then the contents will be layed out within those insets. [JavaFX API]

public class Help {
    public Scene render() {
        VBox root = new VBox(50);
        return new Scene(root, 300, 200);
    }
}