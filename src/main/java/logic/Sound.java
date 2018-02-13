package logic;
import org.jfugue.player.Player;

public class Sound {
	
	public String cardToStaccato(Card card) {
		String staccato;
		if (card.getOrder() == "upwards") {
			staccato = "V0 I["+card.getInstrument()+"] "+card.getNote1()+"h Rh V1 I["+card.getInstrument()+"] Rh "+card.getNote2()+"h";
		}
		else if (card.getOrder() == "downwards") {
			staccato = "V0 I["+card.getInstrument()+"] "+card.getNote2()+"h Rh V1 I["+card.getInstrument()+"] Rh "+card.getNote1()+"h";
		}
		else {
			staccato = "V0 I["+card.getInstrument()+"] "+card.getNote1()+"h V1 I["+card.getInstrument()+"] "+card.getNote2()+"h";
		}
		return staccato;
	}
	
	public void play(Card card) {
		Sound sound = new Sound();
		Player player = new Player();
		player.play(sound.cardToStaccato(card));
	}
}
