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

/* classdoc
    Opens a .csv file and renders it.
    The static methods can add an entry to the table.
    
    https://docs.oracle.com/javase/tutorial/essential/io/file.html




*/

public class Ranking extends Group {
    Scene scene;
    TableView table;
    Leaderboard leaderboard;

    public static void updateRanking(logic.Level level) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        RankingVal rank;
        System.out.println(level);
        rank = new RankingVal(level, "Angelo");
        System.out.println(gson.toJson(rank));
        Path file = FileSystems.getDefault().getPath("./resources/ranking.json");
        // Open file
        Leaderboard leaderboard;
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
             leaderboard = gson.fromJson(reader, Leaderboard.class);
             leaderboard.add(rank, level.getLevelNumber());
             System.out.println(gson.toJson(leaderboard));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Ranking() {
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
        TableColumn levelCol = new TableColumn("Level");
        TableColumn dateCol = new TableColumn("Date");
        TableColumn timeCol = new TableColumn("Time");
        TableColumn correctCol = new TableColumn("Correct");

        this.table = new TableView<>();
        this.table.setItems(getValues());
        this.table.getColumns().addAll(
            nameCol, 
            levelCol, 
            dateCol, 
            timeCol, 
            correctCol);
        this.getChildren().add(this.table);
    }

    private ObservableList getValues() {
        ObservableList values = FXCollections.observableArrayList();
        
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
            case 1: addToLevel1(value);
            case 2: addToLevel1(value);
            case 3: addToLevel1(value);
            case 4: addToLevel1(value);
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
    public String getTime() {
        long minutes = this.time / 60;
        long seconds = this.time % 60;
        return "" + minutes + ":" + seconds;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC+1"));
        return sdf.format(date);
    }

}