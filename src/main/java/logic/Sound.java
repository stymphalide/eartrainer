package logic;
import org.jfugue.*;

public class Sound extends Thread {
    private Card card;
    public void run() {
        while(true) {
            try {
                synchronized(this) {
                    while(card == null) wait();

                    Player player = new Player();
                    String stac = this.cardToStaccato(card);
                    this.card = null;
                    //System.out.println(stac);
                    player.play(stac);
                }
            
            } catch (InterruptedException e){ e.printStackTrace();}
        }
    }

    public String cardToStaccato(Card card) {
        String staccato = null;
        switch (card.getOrder()) {
        case "Upwards": staccato = "V0 I["+card.getActualInstrument()+"] ["+card.getNote1()+"]h ["+card.getNote2()+"]h";
        break;
        case "Downwards": staccato = "V0 I["+card.getActualInstrument()+"] ["+card.getNote2()+"]h ["+card.getNote1()+"]h";
        break;
        case "Chordal": staccato = "V0 I["+card.getActualInstrument()+"] ["+card.getNote1()+"]h.+["+card.getNote2()+"]h.";
        break;
        }
        return staccato;
    }
    
    public synchronized void play(Card card) {
        this.card = card;
        this.notify();
    }
}
