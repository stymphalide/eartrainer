package view;


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

    public static void updateRanking(logic.Level level) {
        Path file = "./resources/ranking.csv";

        // Open file
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readline()) != null) {
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


}