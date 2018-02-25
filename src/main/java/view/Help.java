package view;

import java.io.File;                    // An abstract representation of file and directory pathnames. [File API]
import java.io.IOException;             // Signals that an I/O exception of some sort has occurred. [IOException API]
import org.apache.commons.io.FileUtils; // General file manipulation utilities. [FileUtils API]
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Help {
    public Scene render() {
        return new Scene(new Browser());
    }
}
// https://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm
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
