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
    Scene menuScene;
    Scene levelScene;
    Scene afterLevelScene;
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Menu menu = new Menu();
        Button levelStartButton = new Button();
        menuScene = menu.render(levelStartButton);
        levelStartButton.setOnAction(e -> {
            setUpLevel();
        });

        window.setTitle("eartrainer - Menu");
        window.setScene(menuScene);
        //primaryStage.setMaximized(true);
        window.show();
    }
    private void setUpLevel() {
        window.setTitle("eartrainer - Level 1");
        logic.Level level = new logic.Level(1);
        view.Level levelView = new view.Level();
        Scene levelScene = levelView.render(level);
        window.setScene(levelScene);


/*        viewAfterLevel afterLevel = new view.AfterLevel();
        afterLevelScene = afterLevel.render(level);
        window.setScene(afterLevelScene);*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
