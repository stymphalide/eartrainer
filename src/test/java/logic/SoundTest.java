package logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class SoundTest {
    @Test public void testCardToStaccato() {
    	Card card = new Card("Piano", "Upwards", "Middle", "Perfect Fifth");
        Sound classUnderTest = new Sound();
        assertEquals("the following Staccato-String is expected", "V0 I[Piano] C4h Rh V1 I[Piano] Rh G4h", classUnderTest.cardToStaccato(card));
    }
}
