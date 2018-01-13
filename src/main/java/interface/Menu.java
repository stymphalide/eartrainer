import javafx.application.Application;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.layout.GridPane; 
import javafx.stage.Stage; 


public class Menu extends Application {
	public void print() {
		System.out.println("Hello World");
	}
	@Override
	public void start(Stage primaryStage) { 
        System.out.println("start() called"); 
        primaryStage.setTitle("Hello World!"); 
        Button butt = new Button(); 
        butt.setText("Click here!"); 
        final Label label = new Label(""); 
        label.setAlignment(Pos.CENTER); 
        label.setStyle("-fx-border-width:1px; -fx-border-color: black;"); 
        label.setMinSize(87, 20); 
        butt.setOnAction(new EventHandler<ActionEvent>() { 
            @Override 
            public void handle(ActionEvent event) { 
                label.setText("Hello World!"); 
            } 
        }); 
         
        GridPane grid = new GridPane(); 
        grid.setAlignment(Pos.CENTER); 
        grid.setHgap(5); 
        grid.setVgap(5); 
        grid.add(label, 0, 0); 
        grid.add(butt, 0, 1); 
        grid.setGridLinesVisible(false); 

        Scene scene = new Scene(grid, 250, 150); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    }
    public void start_app() {
    	launch();
    }
}