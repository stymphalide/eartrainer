package view;


import java.util.*;
import java.text.SimpleDateFormat;

import static java.nio.file.StandardOpenOption.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;                            // This is the main class for using Gson. Gson is typically used by first constructing a Gson instance and then invoking toJson(Object) or fromJson(String, Class) methods on it. [Gson API]
import com.google.gson.GsonBuilder;                     // Use this builder to construct a Gson instance when you need to set configuration options other than the default. [Gson API]

import javafx.collections.FXCollections;                // Utility class that consists of static methods that are 1:1 copies of java.util.Collections methods. [JavaFX API]
import javafx.collections.ObservableList;               // A list that allows listeners to track changes when they occur. [JavaFX API]
import javafx.scene.Group;                              // A Group node contains an ObservableList of children that are rendered in order whenever this node is rendered. [JavaFX API]
import javafx.scene.Scene;                              // The JavaFX Scene class is the container for all content in a scene graph. The background of the scene is filled as specified by the fill property. [JavaFX API]
import javafx.scene.layout.VBox;                        // VBox lays out its children in a single vertical column. If the vbox has a border and/or padding set, then the contents will be layed out within those insets. [JavaFX API]
import javafx.scene.control.Label;                      // Label is a non-editable text control. A Label is useful for displaying text that is required to fit within a specific space, and thus may need to use an ellipsis or truncation to size the string to fit. [JavaFX API]
import javafx.scene.control.TextField;                  // Text input component that allows a user to enter a single line of unformatted text. [JavaFX API]
import javafx.scene.control.TextArea;                   // Text input component that allows a user to enter multiple lines of plain text. [JavaFX API]
import javafx.scene.control.Button;                     // A simple Button Control. Can be a event Target and Contains text and/or graphic [JavaFX API].
import javafx.scene.control.Tab;                        // Tabs are placed within a TabPane, where each tab represents a single 'page'. [JavaFX API]
import javafx.scene.control.TabPane;                    // A control that allows switching between a group of Tabs. [JavaFX API]
import javafx.scene.control.TableView;                  // The TableView control is designed to visualize an unlimited number of rows of data, broken out into columns. [JavaFX API]
import javafx.scene.control.TableColumn;                // A TableView is made up of a number of TableColumn instances. [JavaFX API]
import javafx.scene.control.cell.PropertyValueFactory;  // A convenience implementation of the Callback interface [JavaFX API]
import javafx.util.Callback;                            // The Callback interface is designed to allow for a common, reusable interface to exist for defining APIs that requires a call back in certain situations. [JavaFX API]
import javafx.beans.value.ObservableValue;              // An ObservableValue is an entity that wraps a value and allows to observe the value for changes. [JavaFX API]
import javafx.beans.property.SimpleStringProperty;      // This class provides a full implementation of a Property wrapping a String value. [JavaFX API]


/* classdoc
    Opens a .json file and renders it in a table.
    The static methods can add entries to the table.

    This class inherits from the javaFX Group class.

    -- CLASSES --
    There is the public class Ranking
    and two additionaly classes Leaderboard and RankingVal which represent the values that are saved.

    -- STATIC METHODS --
    This class has two static methods.
    the `Scene getPopUp(Button submit, TextField input)` and
    `void updateRanking(logic.Level level, String username)`

    The first one creates a window where the player can type his username.

    The second one needs a level and a username and saves the new entry into the leaderboard.
    This is done by serialising the ranking file into a leaderboard object
    and creating a RankingVal object of the level and the user. Lastly the leaderboard is sorted and deserialised to the same file.

    The private static method is used to actually open the .json file. 

    -- INSTANCE VARIABLES --
    There is one static variable, that holds the location of the ranking files.
    
    The scene variable that is associated with the leaderboard.
    The header and the table variables are needed to use this variable in different private methods.

    The levelNumber is just a holder for the different levels.
    The leaderboard is the representation of the whole .json file. Which acts as a little non-relational database.

    -- CONSTRUCTOR --
    This method serialises the leaderboard and assigns it to the leaderboard variable.
    It then creates a new TabPane with four tabs.
    In the end the tabPane is added to the group object.

    -- METHODS --
    -- public --



*/

public class Ranking extends Group {
    private static String jsonPath = "./resources/ranking.json";
    private Scene scene;
    private TabPane header;
    private TableView table;
    private int levelNumber;
    private Leaderboard leaderboard;

    public static Scene getPopUp(Button submit, TextField input) {
        Label text = new Label("Add your Name here:");
        input.setPromptText("Username");
        submit.setText("Submit");
        VBox box = new VBox(text, input, submit);
        Scene popup = new Scene(box, 200, 100);
        return popup;
    }

    public static void updateRanking(logic.Level level, String username) {
        
        RankingVal rank = new RankingVal(level, username);
        Leaderboard leaderboard = openRankingFile();
        leaderboard.add(rank, level.getLevelNumber());
        leaderboard.sort();
            
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try (Writer writer = new FileWriter(jsonPath)) {
            gson.toJson(leaderboard, writer);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
    private static Leaderboard openRankingFile() {
        Path file = FileSystems.getDefault().getPath(jsonPath);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Leaderboard leaderboard = new Leaderboard();;
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            leaderboard = gson.fromJson(reader, Leaderboard.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }

    public Ranking() {
        this.leaderboard = openRankingFile();
        this.header = new TabPane();
        setUpTab(1);
        setUpTab(2);
        setUpTab(3);
        setUpTab(4);
        this.getChildren().add(this.header);
    }
    public Scene render() {
        if(this.scene == null) {
            this.scene = new Scene(this, 400, 500);
        } 
        return this.scene;
    }
    private void setUpTab(int i) {
        Tab tab = new Tab("Level " + i);
        this.levelNumber = i;
        setUpTable(i);
        tab.setContent(this.table);
        this.header.getTabs().add(tab);
    }

    private void setUpTable(int levelN) {
        Leaderboard leaderboard = this.leaderboard;
        TableColumn rankCol = new TableColumn("Rank");
        rankCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingVal, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RankingVal, String> value) {
                SimpleStringProperty obsVal = new SimpleStringProperty();
                int rank = leaderboard.get(levelN).indexOf(value.getValue());
                obsVal.set("#" + (rank + 1));
                return obsVal;
            }
        });
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingVal, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RankingVal, String> value) {
                SimpleStringProperty obsVal = new SimpleStringProperty();
                obsVal.set(value.getValue().getName());
                return obsVal;
            }
        });
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingVal, String>, ObservableValue<String>>() {
           public ObservableValue<String> call(TableColumn.CellDataFeatures<RankingVal, String> value) {
                SimpleStringProperty obsVal = new SimpleStringProperty();
                obsVal.set(value.getValue().getDateString());
                return obsVal;
            } 
        });
        TableColumn timeCol = new TableColumn("Time");
        timeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingVal, String>, ObservableValue<String>>() {
           public ObservableValue<String> call(TableColumn.CellDataFeatures<RankingVal, String> value) {
                SimpleStringProperty obsVal = new SimpleStringProperty();
                obsVal.set(value.getValue().getTimeString());
                return obsVal;
            } 
        });
        TableColumn correctCol = new TableColumn("Correct");
        correctCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RankingVal, String>, ObservableValue<String>>() {
           public ObservableValue<String> call(TableColumn.CellDataFeatures<RankingVal, String> value) {
                SimpleStringProperty obsVal = new SimpleStringProperty();
                obsVal.set(value.getValue().getCorrectString());
                return obsVal;
            } 
        });

        this.table = new TableView<>();
        this.table.setItems(getValues(levelN));
        this.table.getColumns().addAll(
            rankCol,
            nameCol, 
            dateCol, 
            timeCol, 
            correctCol);
    }

    private ObservableList getValues(int levelN) {
        ObservableList<RankingVal> values = FXCollections.observableArrayList();
         for (RankingVal value : this.leaderboard.get(levelN)) {
            values.add(value);
        }
        return values;
    }
}

/* classdoc
A Holder for the whole ranking. It consists basically of four lists, one for each level.
It also has methods to add new values and sort itself.


*/
class Leaderboard {
    private List<RankingVal> level1;
    private List<RankingVal> level2;
    private List<RankingVal> level3;
    private List<RankingVal> level4;

    public Leaderboard() {
        this.level1 = new ArrayList<RankingVal>();
        this.level2 = new ArrayList<RankingVal>();
        this.level3 = new ArrayList<RankingVal>();
        this.level4 = new ArrayList<RankingVal>();
    }

    public void add(RankingVal value, int level) {
        switch (level) {
            case 1: addToLevel1(value); break;
            case 2: addToLevel2(value); break;
            case 3: addToLevel3(value); break;
            case 4: addToLevel4(value); break;
        }
    }

    public void sort() {
        Comparator<RankingVal> comp = new Comparator<RankingVal>() {
            public int compare(RankingVal o1, RankingVal o2) {
                if (o1.getCorrect() == o2.getCorrect()) {
                    if(o1.getTime() == o2.getTime()) {
                        if (o1.getDate() == o2.getDate()) {
                            return 0;
                        }
                        if (o1.getDate() < o2.getDate()) {
                            return -1;
                        }
                        return 1;
                    }
                    if(o1.getTime() < o2.getTime()) {
                        return -1;
                    }
                    return 1;
                }
                if (o1.getCorrect() > o2.getCorrect()) {
                    return -1;
                } 
                return 1;
            }
        };
        this.level1.sort(comp);
        this.level2.sort(comp);
        this.level3.sort(comp);
        this.level4.sort(comp);
    }

    private void addToLevel1(RankingVal value) {
        this.level1.add(value);
    }
    private void addToLevel2(RankingVal value) {
        this.level2.add(value);
    }
    private void addToLevel3(RankingVal value) {
        this.level3.add(value);
    }
    private void addToLevel4(RankingVal value) {
        this.level4.add(value);
    }
    public List<RankingVal> get(int n) {
        //List<RankingVal> default = new ArrayList<RankingVal>();
        switch (n) {
            case 1: return getLevel1();
            case 2: return getLevel2();
            case 3: return getLevel3();
            case 4: return getLevel4();
        }
        return null;
    }
    public void set(List<RankingVal> leveln, int n) {
        switch (n) {
            case 1: setLevel1(leveln); break;
            case 2: setLevel2(leveln); break;
            case 3: setLevel3(leveln); break;
            case 4: setLevel4(leveln); break;
        }
    }

    public void setLevel1(List<RankingVal> level1) {
        this.level1 = level1;
    }
    public List<RankingVal> getLevel1() {
        return this.level1;
    }
    public void setLevel2(List<RankingVal> level2) {
        this.level2 = level2;
    }
    public List<RankingVal> getLevel2() {
        return this.level2;
    }
    public void setLevel3(List<RankingVal> level3) {
        this.level3 = level3;
    }
    public List<RankingVal> getLevel3() {
        return this.level3;
    }
    public void setLevel4(List<RankingVal> level4) {
        this.level4 = level4;
    }
    public List<RankingVal> getLevel4() {
        return this.level4;
    }
}

/* classdoc

    This class holds the values of any game. Additionally it takes in
    the username of the player, stores the start time and 

*/
class RankingVal {
    private String name;
    private long date;
    private long time;
    private double correct;
    public RankingVal(String name, 
                      long date, 
                      long time, 
                      double correct) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.correct = correct;
    }
    public RankingVal(logic.Level level, String name) {
        if(level.isFinished()) {
            this.name = name;
            this.date = level.getStartTime().getEpochSecond();
            this.time = level.getDuration().getSeconds();
            this.correct = (double)((double)level.getCorrectAnswers() / (double)level.getTotalAnswers());
        } else {
            throw new java.lang.RuntimeException("The level must be finished.");
        }
    }

    public String getName() {
        return this.name;
    }
    public long getDate() {
        return this.date;
    }
    public long getTime() {
        return this.time;
    }
    public double getCorrect() {
        return this.correct;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(long date) {
        this.date = date;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public void setCorrect(double correct) {
        this.correct = correct;
    }
    public String getDateString() {
        long millis = this.date * 1000;
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC+1"));
        return sdf.format(date);
    }
    public String getTimeString() {
        long minutes = this.time / 60;
        long seconds = this.time % 60;
        return "" + minutes + ":" + seconds;
    } 
    public String getCorrectString() {
        return "" + (int)(this.correct*100) + "%";
    }

}