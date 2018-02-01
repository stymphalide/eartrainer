package logic;
import org.jfugue.player.Player;

public class Sound {

	public String cardToStaccato(Card card) {
		return "V0 I[card.instrument.getInstrumentName()]";
	}
	
	public void test() {
       Player player = new Player();
       player.play("V0 I[Recorder] E6q C6h. | E6q C6h. | D6q E6q D6q C5majh");      	   
   }
	
}
