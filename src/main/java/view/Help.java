package view;

import java.io.File;                                // An abstract representation of file and directory pathnames. [File API]
import java.io.IOException;                         // Signals that an I/O exception of some sort has occurred. [IOException API]
import org.apache.commons.io.FileUtils;             // General file manipulation utilities. [FileUtils API]

import org.commonmark.node.*;                       // AST node types [Commonmark API]
import org.commonmark.parser.Parser;                // Parses input text to a tree of nodes. [Commonmark API]
import org.commonmark.renderer.html.HtmlRenderer;   // Renders a tree of nodes to HTML. [Commonmark API]

import javafx.geometry.HPos;                        // A set of values for describing horizontal positioning and alignment. [JavaFX API]
import javafx.geometry.VPos;                        // A set of values for describing vertical positioning and alignment. [JavaFX API]
import javafx.scene.Node;                           // Base class for scene graph nodes. [JavaFX API]
import javafx.scene.Scene;                          // The JavaFX Scene class is the container for all content in a scene graph. The background of the scene is filled as specified by the fill property. [JavaFX API]
import javafx.scene.layout.HBox;                    // HBox lays out its children in a single horizontal row. If the hbox has a border and/or padding set, then the contents will be layed out within those insets. [JavaFX API]
import javafx.scene.layout.Priority;                // Enumeration used to determine the grow (or shrink) priority of a given node's layout area when its region has more (or less) space available and multiple nodes are competing for that space. [JavaFX API]
import javafx.scene.layout.Region;                  // Region is the base class for all JavaFX Node-based UI Controls, and all layout containers. [JavaFX]
import javafx.scene.web.WebEngine;                  // WebEngine is a non-visual object capable of managing one Web page at a time. [JavaFX API]
import javafx.scene.web.WebView;                    // WebView is a Node that manages a WebEngine and displays its content. [JavaFX API]

/* classdoc

This class renders the help screen. The help screen is basically a browser window. 
The Html content of the window is created from a Markdown file.

-- CLASSES --
The Help class is the only class publicly available and it just has one public method `render()`
that returns a scene.

The Browser is created using javaFX's WebView. 
The Html is created using Commonmarks HtmlRenderer. All contents of the Help view could be described in a markdown file in the resources part.


(Heavenly inspired by [WebView] )
*/

public class Help {
    public Scene render() {
        return new Scene(new Browser());
    }
}

class Browser extends Region {
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
     
    public Browser() {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        String source = "Not Found";
        
        try {
            source = getSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // https://github.com/atlassian/commonmark-java
        Parser parser = Parser.builder().build();
        org.commonmark.node.Node document = parser.parse(source);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        source = renderer.render(document);
        webEngine.loadContent(source);
        //add the web view to the scene
        getChildren().add(browser);
    }

    private String getSource() throws IOException {
        String path = "./resources/md/help.md";
        return FileUtils.readFileToString(new File(path), "UTF-8");
    }

    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override 
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override 
    protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override 
    protected double computePrefHeight(double width) {
        return 500;
    }
}
