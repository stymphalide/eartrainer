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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.*;
import javafx.scene.control.TextField;

public class App extends Application {
    Stage window;
    Button backToMenu = new Button("Main Menu");
    List<Button> startLevels;

    view.Menu menu;
    Button confirm = new Button("Confirm");
    Button viewRanking = new Button("Leaderboard");
    TextField userInput;
    logic.Level level;

    view.Music musicThread;
    ImageView musicToggler;

    @Override
    public void init() throws Exception {
        Button startLevel1 = new Button();
        startLevel1.setOnAction(e -> {
            setUpLevel(1);
        });
        Button startLevel2 = new Button();
        startLevel2.setOnAction(e  -> {
            setUpLevel(2);
        });
        Button startLevel3 = new Button();
        startLevel3.setOnAction(e  -> {
            setUpLevel(3);
        });
        Button startLevel4 = new Button();
        startLevel4.setOnAction(e  -> {
            setUpLevel(4);
        });
        this.startLevels = new ArrayList<Button>();
        this.startLevels.add(startLevel1);
        this.startLevels.add(startLevel2);
        this.startLevels.add(startLevel3);
        this.startLevels.add(startLevel4);

        this.backToMenu.setOnAction(e -> {
            setUpMenu();
        });
        this.musicToggler = new ImageView();
        this.musicToggler.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(musicThread.getState());
                if (musicThread.getState() == Thread.State.WAITING) {
                    stopMusic();
                    menu.toggleMusic();
                } else {
                    menu.toggleMusic();
                    startMusic();
                }
                System.out.println(event);
                event.consume();
            }
        });
        this.menu = new view.Menu(startLevels, this.musicToggler, this.viewRanking);
        this.viewRanking.setOnAction(e -> {
            setUpRanking();
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        startMusic();
        setUpMenu();
        //primaryStage.setMaximized(true); // Maximise the window.
        window.show();
    }

    public void stop() throws Exception {
        stopMusic();
    }

    private void startMusic() {
        if (this.menu.getMusic()) {
            this.musicThread = new view.Music();
            this.musicThread.start();
        }
    }

    private void stopMusic() {
        if(this.musicThread != null) {
            this.musicThread.cancel();
        }
    }

    private void setUpMenu() {
        window.setTitle("eartrainer - Menu");
        window.setScene(this.menu.render());
    }
    private void setUpLevel(int n) {
        stopMusic();
        this.level = new logic.Level(n);

        window.setTitle("eartrainer - Level " + level.getLevelNumber());

        view.Level levelView = new view.Level(level, this.confirm);

        this.confirm.setOnAction(e -> {
            List<String> values = levelView.getComboBoxValues();
            logic.Card answer = new logic.Card(values.get(0), values.get(1), values.get(2), values.get(3));
            level.setAnswer(answer);
            level.nextQuestion();
            if (level.isFinished()) {
                this.userInput = new TextField();
                Button submit = new Button();
                final Stage popUp = new Stage();
                submit.setOnAction(event -> {
                    String username = userInput.getText();
                    view.Ranking.updateRanking(level, username);
                    popUp.close();
                    setUpRanking();
                });
                popUp.setScene(view.Ranking.getPopUp(submit, this.userInput));
                popUp.show();
                window.setTitle("eartrainer - Game Over");
                startMusic();
                levelView.viewFinished(level, backToMenu, startLevels.get(n - 1), this.musicToggler);                
            } else {
                if (values == null) { // Only advance if all comboboxes are set.
                    System.out.println("Set All Combobox Values!");
                } else {
                    levelView.update();
                }
            }
        });
        window.setScene(levelView.render());
    }

    private void setUpRanking() {
        final Stage ranking = new Stage();
        view.Ranking rankingScene = new view.Ranking();
        ranking.setScene(rankingScene.render());
        ranking.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
