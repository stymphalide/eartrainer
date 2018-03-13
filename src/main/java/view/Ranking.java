package view;


import java.io.File;                    // An abstract representation of file and directory pathnames. [File API]
import java.io.IOException;             // Signals that an I/O exception of some sort has occurred. [IOException API]
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.charset.Charset;

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

    public static void updateRanking(logic.Level level) {
        Path file = FileSystems.getDefault().getPath("./resources/ranking.csv");
        Charset charset = Charset.forName("UTF-8");
        // Open file
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read from file.

        // Write to file

        // Close file

    }

    public Ranking() {
        setUpTable();
    }
    public Scene render() {
        if(this.scene == null) {
            this.scene = new Scene(this, 700, 500);
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
        table.setItems(getValues());
        table.getColums().addAll(
            nameCol, 
            levelCol, 
            dateCol, 
            timeCol, 
            correctCol);
        this.getChildren().add(this.table);

    }

    private ObservableList getValues() {
        ObservableList values = FXCollections.observableArrayList();
        Path file = FileSystems.getDefault().getPath("./resources/ranking.csv");
        Charset charset = Charset.forName("UTF-8");
        // Open file
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            boolean header = true;
            while ((line = reader.readLine()) != null) {
                if(header) {
                    header = !header;
                } else {
                    values.add(new RankingVal(line));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return values;
}
private class RankingVal {
    private String name;
    private int level;
    private long date;
    private long time;
    private double correct;
    public RankingVal(String name, 
                      int level, 
                      long date, 
                      long time, 
                      double correct) {
        this.name = name;
        this.level = level;
        this.date = date;
        this.time = time;
        this.correct = correct;
    }
    public RankingVal(logic.Level level) throws Exception {
        if(level.isFinished()) {
            this.name = "Some Name";
            this.level = level.getLevelNumber();
            this.date = level.getStartTime().getEpochSeconds();
            this.time = level.getDuration().getSeconds();
            this.correct = (double)(level.getCorrectAnswers() / level.getWrongAnswers());
        } else {
            throw new java.lang.RuntimeException("The level must be finished.");
        }
    }
    public RankingVal(String line) {
        String[] values = line.split(",");
        this.name = values[0];
        this.level = Integer.parseInt(values[1]);
        this.date = Long.parseLong(values[2]);
        this.time = Long.parseLong(values[3]);
        this.correct = Double.parseDouble(values[4]);
    }

    public String getName() {
        return this.name;
    }
    public int getLevel() {
        return this.level;
    }
    public String getDate() {
        return "No Date";
    }
    public String getTime() {
        return "No Time";
    }
    public double getCorrect() {
        return this.correct;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLevel(int level) {
        this.level = level;
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


}