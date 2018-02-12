import logic.*;

public class App {

    public static void main(String[] args) {
        Level level = new Level1();
        Card card = new Card("Piano", "upwards", "C4-B5", "perfect_fifth");
        Sound sound = new Sound();
        sound.play(card);
    }
}
