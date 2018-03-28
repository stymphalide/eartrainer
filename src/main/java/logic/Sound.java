package logic;
import org.jfugue.*;

/**
 * This class turns the features of a given card into a MusicString.
 * This MusicString is the means of generating sound with jFugue.
 * The sound is played on a separate, constantly running thread, which ensures that nothing is interrupted by the playing of sound.
 * @author Tobias
 */
public class Sound extends Thread {
    private Card card;
    public void run() {
        while(true) {
            try {
                synchronized(this) {
                    while(card == null) wait();

                    Player player = new Player();
                    player.play(this.cardToMusicString(card));
                    this.card = null;
                }
            
            } catch (InterruptedException e){ e.printStackTrace();}
        }
    }

    /**
     * @param card
     * @return a MusicString based on the actual instrument, the order and the two notes.
     */
    public String cardToMusicString(Card card) {
        String musicString = null;
        switch (card.getOrder()) {
        case "Upwards": musicString = "V0 I["+card.getActualInstrument()+"] ["+card.getNote1()+"]h ["+card.getNote2()+"]h";
        break;
        case "Downwards": musicString = "V0 I["+card.getActualInstrument()+"] ["+card.getNote2()+"]h ["+card.getNote1()+"]h";
        break;
        case "Chordal": musicString = "V0 I["+card.getActualInstrument()+"] ["+card.getNote1()+"]h.+["+card.getNote2()+"]h.";
        break;
        }
        return musicString;
    }
    
    /**
     * Plays the sound described by the features of the card.
     * @param card
     */
    public synchronized void play(Card card) {
        this.card = card;
        this.notify();
    }
}
