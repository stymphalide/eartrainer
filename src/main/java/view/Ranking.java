package view;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.text.SimpleDateFormat;

import static java.nio.file.StandardOpenOption.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;


/* classdoc
    Opens a .csv file and renders it.
    The static methods can add an entry to the table.
    
    https://docs.oracle.com/javase/tutorial/essential/io/file.html




*/

public class Ranking extends Group {
    private Scene scene;
    private TableView table;
    private int levelNumber;

    public static void updateRanking(logic.Level level) {
        RankingVal rank = new RankingVal(level, "Angelo");
        try {
            Leaderboard leaderboard = openRankingFile();
            leaderboard.add(rank, level.getLevelNumber());
            
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            

            System.out.println(gson.toJson(leaderboard));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Leaderboard openRankingFile() throws IOException {
        Path file = FileSystems.getDefault().getPath("./resources/ranking.json");

        BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(reader, Leaderboard.class);
    }

    public Ranking() {
        this.levelNumber = 1;
        setUpTable();
    }
    public Scene render() {
        if(this.scene == null) {
            this.scene = new Scene(this, 400, 500);
        } else {
            update();
        }
        return this.scene;
    }
    private void update() {

    }
    private void setUpTable() {
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
        this.table.setItems(getValues());
        this.table.getColumns().addAll(
            nameCol, 
            dateCol, 
            timeCol, 
            correctCol);
        this.getChildren().add(this.table);
    }

    private ObservableList getValues() {
        ObservableList<RankingVal> values = FXCollections.observableArrayList();
        try {
            Leaderboard leaderboard = openRankingFile();
             for (RankingVal value : leaderboard.get(this.levelNumber)) {
                values.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(values.get(0).getDateString());
        return values;
    }
}
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

    private void addToLevel1(RankingVal value) {
        this.level1.add(value);
    }
    private void addToLevel2(RankingVal value) {
        this.level1.add(value);
    }
    private void addToLevel3(RankingVal value) {
        this.level1.add(value);
    }
    private void addToLevel4(RankingVal value) {
        this.level1.add(value);
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
            this.correct = (double)(level.getCorrectAnswers() / level.getWrongAnswers());
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