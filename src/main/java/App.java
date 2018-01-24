import logic.*;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        //System.out.println(new App().getGreeting());
        Level level = new Level1();
        Sound sound = new Sound();
        sound.test();
    }
}
