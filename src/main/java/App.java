import logic.Level;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        //System.out.println(new App().getGreeting());
        Level level = new Level();
        System.out.println("You have answered "+level.correctAnswers+" Questions correctly!!");
    }
}
