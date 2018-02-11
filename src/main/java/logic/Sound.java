package logic;
import org.jfugue.player.Player;

public class Sound {
	
	public String cardToStaccato(Card card) {
		return "V0 I["+card.instrument.getInstrumentName()+"] "+card.getNote1()+"h Rh V1 I["+card.instrument.getInstrumentName()+"] Rh "+card.getNote2()+"h";
	}
	
}
