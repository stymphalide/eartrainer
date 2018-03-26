import java.util.*;

import javafx.application.Application;      // Application class from which JavaFX application extend. [JavaFX API]
import javafx.stage.Stage;                  // The JavaFX Stage class is the top level JavaFX container. The primary Stage is constructed by the platform. Additional Stage objects may be constructed by the application. [JavaFX API]
import javafx.event.ActionEvent;            // An Event representing some type of action. [JavaFX]
import javafx.event.EventHandler;           // Handler for events of a specific class / type. [JavaFX]
import javafx.scene.control.Button;         // A simple Button Control. Can be a event Target and Contains text and/or graphic [JavaFX API].
import javafx.scene.Scene;                  // The JavaFX Scene class is the container for all content in a scene graph. The background of the scene is filled as specified by the fill property. [JavaFX API]
import javafx.scene.input.MouseEvent;       // When mouse event occurs, the top-most node under cursor is picked and the event is delivered to it through capturing and bubbling phases described at EventDispatcher. [JavaFX]
import javafx.scene.image.Image;            // The Image class represents graphical images and is used for loading images from a specified URL. [JavaFX]
import javafx.scene.image.ImageView;        // The ImageView is a Node used for painting images loaded with Image class. [JavaFX]
import javafx.scene.control.TextField;      // Text input component that allows a user to enter a single line of unformatted text. [JavaFX]
/*
    Date: 28.03.2018
    Project Name: Eartrainer
    Names: Angelo Birrer G4L and Tobias Seefeld G4L
    Main Sources: JavaFX API
    Code Management: logic: Tobias Seefeld G4L | view: Angelo Birrer G4L
*/
/* classdoc

    -- INSTANCE VARIABLES --
    This class holds the whole application together. It inherits from the javafx Application class.
    The class has the following attributes:
     1. Stage window
    
     2. Button backToMenu
     3. List<Button> startLevels
     4. Button confirm
     5. Button viewRanking
     6. TextField userInput
     7. Button help
     8. ImageView musicToggler

     9. view.Menu menu
    10. logic.Level level
    11. view.Music musicThread

    The first attribute is the the actual window, where most parts of the game are rendered in.
    The next seven instance variables are control elements, that are used in several methods or that are passed to other objects as parameters.
    Most of them have some kind of action set that is called when clicked.

    The last three are objects of other classes we have created. 
    The menu is the holder for what one sees in the menu screen.
    The level variable holds the level object when one clicks on a startLevel button.
    The musicThread variable is a thread that is independent from the main javaFX thread, this allows for playing music without the menu being frozen.


    -- METHODS ---
    -- public --
    There are four public methods:
    - void main(String[] args)
    - void init()
    - void start(Stage primaryStage)
    - void stop()

    The content of the main method is just `Application.launch(args)`. This statement starts the javafx application lifecycle.
    The lifecycle consists of five steps:
        1. Instanciation of the Application class.
        2. The init phase: call the init() method.
            This calls the public `init()` method.
            This method can be overriden in the class.
            It then can be used to set any instance variables of the application, since there cannot be a custom constructor.
        3. The start phase:
            This calls the public `start(Stage primaryStage)` method.
            This method needs to be overriden in the class.
            The Stage is the X Window where the contents of the application live in.
            The method in general adds a Scene to primaryStage.
        4. The exit phase:
            Makes sure the process stops after all windows have been closed or after `Platform.exit()` is being called somewhere.
        5. Calls the stop() method:
            Here all other open threads are destroyed.
            Since '[t]his method is called when the application should stop, 
            and provides a convenient place to prepare for application exit and destroy resources.'
            [JavaFX API]

    The `void init()` method handles the proper initialisation of the application. 
    Most things that are usually defined in an object's constructor are defined here.
    The init method:
    - populates the startLevels list and adds event listentes to those buttons.
    - adds events to all other buttons including a click event for the sound image.
    - instantiates the menu object.

    One thing to note here is that most events are actually just calling a private method of the class. 
    This is to keep the code there a little bit tighter.


    The `void start(Stage primaryStage)` method handles the view of the game.
    - First the argument of the method (primaryStage) is set to the class attribute window, 
        this allows it to be referenced from all parts of the class.
    - Then it starts the music by calling the private `startMusic()` method
    - Next it calls the `setUpMenu` method, which is responsible for properly rendering the menu screen.
    - Lastly it calls window.show() which makes the Stage object show its contents.


    -- private --
    There are six private methods:
    1. void startMusic()
    2. void stopMusic
    3. void setUpMenu()
    4. void setUpLevel(int n)
    5. void setUpRanking()
    6. void setUpHelp()

    Those methods serve as helpers for the `start()`, `init()` and the `stop()` methods respectively.

    The `startMusic()` method creates a new thread, where the music is played. 
    However, this thread is only created if the player did not turn the music off.

    The `stopMusic()` cancels the music if it is still playing.

    The `setUpMenu()` makes the already instantiated menu create a Scene object and adds the scene to the window.
    Which means it is being shown in the X window of the application. Additionally it changes the title of the window.

    The `setUpLevel(int n)`:
    - stops the music thread.
    - instantiates a logic.Level object.
    - changes the title of the window.
    - creates a view.Level object, which is basically a VBox.
    - sets an eventListener for the confirm button. Which if clicked:
        - Gets the answer the user gave. And checks whether all values are given.
        - It then sets the players answer as the answer
        - calls the `level.nextQuestion()` method, which validates the users answer and sets a new question.
        - If the level is over, it:
            - opens a popup window that asks for the name
            - After hitting submit it closes the window and opens the leaderboard, where the game should be present.
            - In the after game screen the music should play again.
            - the title of the window changes again.
            - the level view is transformed to the after game screen.
        - Otherwise it just updates the level view.
    - makes the view.level object render a scene and sets it as the main scene of the window.

    The `setUpRanking()` method opens a new window called "Leaderboard" that shows a ranking scene as described in the view.Ranking class.

    The `setUpHelp()` method opens a new window called "Help!" that shows a help text as described in the view.Help class.
*/
/*
    Main Source: JavaFX API
*/


public class App extends Application {
    Stage window;
    Button backToMenu = new Button("Main Menu");
    List<Button> startLevels;

    view.Menu menu;
    Button confirm = new Button("Confirm");
    Button viewRanking = new Button("Leaderboard");
    TextField userInput;
    Button help = new Button("Help");
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
        this.viewRanking.setOnAction(e -> {
            setUpRanking();
        });
        this.help.setOnAction(e -> {
            setUpHelp();
        });
        this.menu = new view.Menu(this.startLevels, this.musicToggler, this.viewRanking, this.help);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        startMusic();
        setUpMenu();
        window.show();
    }

    @Override
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
        this.window.setTitle("eartrainer - Menu");
        this.window.setScene(this.menu.render());
    }
    
    private void setUpLevel(int n) {
        stopMusic();
        this.level = new logic.Level(n);
        this.window.setTitle("eartrainer - Level " + level.getLevelNumber());
        
        view.Level levelView = new view.Level(level, this.confirm);

        this.confirm.setOnAction(e -> {
            List<String> values = levelView.getComboBoxValues();
            if(values == null) {
                System.out.println("Set All Combobox Values!");
            } else {
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
                    popUp.setTitle("Name");
                    popUp.show();
                    window.setTitle("eartrainer - Game Over");
                    startMusic();
                    levelView.viewFinished(level, backToMenu, startLevels.get(n - 1), this.musicToggler);                
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
        ranking.setTitle("Leaderboard");
        ranking.show();
    }
    
    private void setUpHelp() {
        final Stage helpView = new Stage();
        view.Help helpScene = new view.Help();
        helpView.setScene(helpScene.render());
        helpView.setTitle("Help!");
        helpView.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
