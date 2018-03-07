package logic;
import org.jfugue.*;

public class Sound extends Thread {
	private Card card;
	@Override
	public void run() {
		synchronized(this) {
			while(true) {
				while(card == null) wait();

				Player player = new Player();
				String stac = this.cardToStaccato(card);
				this.card = null;
				//System.out.println(stac);
				player.play(stac);
			}
		}
	}

	public String cardToStaccato(Card card) {
		String staccato;
		if (card.getOrder() == "Upwards") {
			staccato = "V0 I["+card.getActualInstrument()+"] ["+card.getNote1()+"]h ["+card.getNote2()+"]h";
		}
		else if (card.getOrder() == "Downwards") {
			staccato = "V0 I["+card.getActualInstrument()+"] ["+card.getNote2()+"]h ["+card.getNote1()+"]h";
		}
		else {
			staccato = "V0 I["+card.getActualInstrument()+"] ["+card.getNote1()+"]h.+["+card.getNote2()+"]h.";
		}
		return staccato;
	}
	
	public void play(Card card) {
		this.card = card;
		this.notify();
	}
}
