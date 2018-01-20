/*
* Date: 20.01.2018
* Project Name: Eartrainer
* Names: Angelo Birrer G4L and Tobias Seefeld G4L
* Main Sources: TBD
* Code Management: logic: Tobias Seefeld G4L | view: Angelo Birrer G4L
*/
import view.Menu;
import view.Level;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    Menu menu = new Menu();
    Scene levelScene;
    Stage window;

    Button backToMenu = new Button("Main Menu");
    //Button nextLevel = new Button();
    Button startLevel1 = new Button();
    Button confirm = new Button("Confirm");

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        startLevel1.setOnAction(e -> {
            setUpLevel(1);
        });
        backToMenu.setOnAction(e -> {
            setUpMenu();
        });

        setUpMenu();
        //primaryStage.setMaximized(true);
        window.show();
    }
    private void setUpMenu() {
        Scene menuScene = menu.render(startLevel1);
        window.setTitle("eartrainer - Menu");
        window.setScene(menuScene);
    }
    private void setUpLevel(int nLevel) {
        window.setTitle("eartrainer - Level " + nLevel);
        Button confirm = new Button();
        
        logic.Level level = new logic.Level(nLevel);
        view.Level levelView = new view.Level();
        levelScene = levelView.renderActive(level, confirm);
        window.setScene(levelScene);
        
        confirm.setOnAction(e -> {
            if (level.isFinished()) {
                Scene newScene = levelView.renderFinished(level, backToMenu, startLevel1);
                window.setScene(newScene);
            } else {

                level.increaseN();
                Scene newScene = levelView.renderActive(level, confirm);
                window.setScene(newScene);
            }
            
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
