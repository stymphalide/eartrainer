package view;

import static java.nio.file.StandardOpenOption.*;
import java.io.*;
import java.nio.file.*;

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

        RankingVal rank = new RankingVal(level);

        Path file = FileSystems.getDefault().getPath("./resources/ranking.csv");
        Charset charset = Charset.forName("UTF-8");
        // Open file
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            boolean set = false;
            // Read file
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            if(!set) {
                // Write to file

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }
}
class RankingVal {
    private String name;
    private int level;
    private String date;
    private long time;
    private double correct;
    public RankingVal(String name, 
                      int level, 
                      String date, 
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
            this.date = level.getStartTime().toString();
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
        this.date = values[2];
        this.time = Long.parseLong(values[3]);
        this.correct = Double.parseDouble(values[4]);
    }
    public boolean greater(RankingVal rank2) {
        if(this.level == rank2.getLevel()) {
            if (this.correct == rank2.getCorrect()) {
                if(this.time > rank2.getTime()) {
                    return false;
                } else {
                    return true;
                }
            } else if (this.correct < rank2.getCorrect()) {
                return false;
            } else {
                return true;
            }
        } else if(this.level < rank2.getLevel()) {
            return false;
        } else {
            return true;
        }
        return false;
    }

    public String getName() {
        return this.name;
    }
    public int getLevel() {
        return this.level;
    }
    public String getDate() {
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
    public void setLevel(int level) {
        this.level = level;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public void setCorrect(double correct) {
        this.correct = correct;
    }


}