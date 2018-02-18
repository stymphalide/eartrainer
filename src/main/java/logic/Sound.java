package logic;
import org.jfugue.player.Player;

public class Sound {
	
	public String cardToStaccato(Card card) {
		String staccato;
		if (card.getOrder() == "upwards") {
			staccato = "V0 I["+card.getActualInstrument()+"] ["+card.getNote1()+"]h ["+card.getNote2()+"]h";
		}
		else if (card.getOrder() == "downwards") {
			staccato = "V0 I["+card.getActualInstrument()+"] ["+card.getNote2()+"]h ["+card.getNote1()+"]h";
		}
		else {
			staccato = "V0 I["+card.getActualInstrument()+"] ["+card.getNote1()+"]h.+["+card.getNote2()+"]h.";
		}
		return staccato;
	}
	
	public void play(Card card) {
		Sound sound = new Sound();
		Player player = new Player();
		player.play(sound.cardToStaccato(card));
	}
}
