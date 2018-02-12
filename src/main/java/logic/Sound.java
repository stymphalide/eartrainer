package logic;
import org.jfugue.player.Player;

public class Sound {
	
	public String cardToStaccato(Card card) {
		return "V0 I["+card.instrument+"] "+card.getNote1()+"h Rh V1 I["+card.instrument+"] Rh "+card.getNote2()+"h";
	}
	
	public void play(Card card) {
		Sound sound = new Sound();
		Player player = new Player();
		player.play(sound.cardToStaccato(card));
	}
}
