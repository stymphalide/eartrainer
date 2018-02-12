/*
    Date: 20.01.2018
    Project Name: Eartrainer
    Names: Angelo Birrer G4L and Tobias Seefeld G4L
    Main Sources: TBD
    Code Management: logic: Tobias Seefeld G4L | view: Angelo Birrer G4L
*/
/* classdoc
    This class holds the whole application together. It inherits from the javafx Application.
    The class has the following attributes:
    - Menu menu;
    - Stage window;
    - Button backToMenu;
    - Button startLevel1;
    - Button confirm;

    There are two public methods:
    - void main(String[] args);
    - void start(Stage primaryStage);

    The content of the main method is just `Application.launch(args)`. This statement starts the javafx application lifecycle.
    The lifecycle consists of five steps:
        1. Instanciation of the Application class.
        2. The init phase: call the init() method. 
        3. The start phase:
            This calls the public `start(Stage primaryStage)` method.
            This method needs to be overriden in the class.
            The Stage is the X Window where the contents of the application live in.
            The method in general adds a Scene to primaryStage.
        4. The exit phase:
            Makes sure the process stops after all windows have been closed or after `Platform.exit()` is being called somewhere.
        5. Calls the stop() method:
            'This method is called when the application should stop, 
            and provides a convenient place to prepare for application exit and destroy resources.'
            [JavaFX API]

    The `void start(Stage primaryStage)` method handles the view of the game.
    - First the argument of the method (primaryStage) is set to the class attribute window, 
        this allows it to be referenced from several parts of the class.
    - Next it sets an EventHandler for the startLevel1 Button. This makes sure that every time this button is pressed,
        the setUpLevel1 is pressed.
    - Next it sets an EventHandler for the backToMenu Button. This makes sure the setUpMenu() method is called upon pressing.
    - Then a menu is actually rendered (setUpMenu).
    - Last it tells the window to show itself: Attempts to show this Window by setting visibility to true [JavaFX API].

    There are two private methods:
    - void setUpMenu();
    - void setUpLevel1();
    Those methods serve as helpers for the `start()` method.
    
    The `setUpMenu()` method renders a Menu view (as defined in view.Menu).
    And adds it to the window. Which means it is being shown in the X window of the application.

    The `setUpLevel1()` method renders a Level view (as defined in view.Level).
    It furthermore creates a new instance of the logic.Level(1) class.
    It also adds EventListeners to the confirm button.
    This allows the view to be re-rendered after a question has been answered and
    gives the possibility to check whether the level is over and then render the afterGame View.
*/
/*
    Main Source: JavaFX API
*/

import javafx.application.Application; // Application class from which JavaFX application extend. [JavaFX API]
import javafx.scene.control.Button;    // A simple Button Control. Can be a event Target and Contains text and/or graphic [JavaFX API].
import javafx.scene.Scene;             // The JavaFX Scene class is the container for all content in a scene graph. The background of the scene is filled as specified by the fill property. [JavaFX API]
import javafx.stage.Stage;             // The JavaFX Stage class is the top level JavaFX container. The primary Stage is constructed by the platform. Additional Stage objects may be constructed by the application. [JavaFX API]

public class App extends Application {
    Stage window;
    view.Menu menu = new view.Menu();

    Button backToMenu = new Button("Main Menu");
    //Button nextLevel = new Button();
    Button startLevel1 = new Button();
    Button confirm = new Button("Confirm");

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        startLevel1.setOnAction(e -> {
            setUpLevel1();
        });
        backToMenu.setOnAction(e -> {
            setUpMenu();
        });

        setUpMenu();
        //primaryStage.setMaximized(true); // Maximise the window.
        window.show();
    }
    private void setUpMenu() {
        Scene menuScene = menu.render(startLevel1);
        window.setTitle("eartrainer - Menu");
        window.setScene(menuScene);
    }
    private void setUpLevel1() {
        window.setTitle("eartrainer - Level 1");
        
        logic.Level level = new logic.Level1();
        view.Level levelView = new view.Level();
        Scene levelScene = levelView.renderActive(level, this.confirm);
        window.setScene(levelScene);
        
        this.confirm.setOnAction(e -> {
            if (level.isFinished()) {
                window.setTitle("eartrainer - Game Over");
                Scene newLevelScene = levelView.renderFinished(level, backToMenu, startLevel1);
                window.setScene(newLevelScene);
            } else {
                level.nextQuestion();
                Scene newLevelScene = levelView.renderActive(level, confirm);
                window.setScene(newLevelScene);
            }
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
}
