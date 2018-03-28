package logic;

import java.util.*;

/**
 * This class represents a card that stores four features (instrument, order, range, interval).
 * From these features the class calculates the numeric values of two notes.
 * Additionally, it converts the instrument types (e.g. Strings) into instruments playable by jFugue.
 * @author Tobias
 */
public class Card {
	
	private HashMap<String, Integer> intervalSizes = new HashMap<String, Integer>();
	private String instrument;
	private String order;
	private String range;
	private String interval;
	private int note1;
	
    
    /**
     * @param instrument
     * @param order
     * @param range
     * @param interval
     */
    public Card(String instrument, 
    			String order,
    			String range, 
    			String interval) {
    	setIntervalSizes();
    	this.instrument = instrument;
    	this.order = order;
    	this.range = range;
    	this.interval = interval;
    	this.note1 = createNote1();
    }
    
    
    /**
     * @return the instrument type of the actual instrument playing the notes.
     */
    public String getInstrument() {
    	return this.instrument;
    }
    
    /**
     * @return the fact which note is played first or if they are played simultaneously.
     */
    public String getOrder() {
    	return this.order;
    }
    
    /**
     * @return the pitch range (low, medium or high) in which the interval is played.
     */
    public String getRange() {
    	return this.range;
    }
    
    /**
     * @return the interval of the two notes played.
     */
    public String getInterval() {
    	return this.interval;
    }
    
    /**
     * Assigns to every interval the number of semi-tones of the respective interval.
     */
    private void setIntervalSizes() {
    	this.intervalSizes.put("Minor Second", 1);
    	this.intervalSizes.put("Major Second", 2);
    	this.intervalSizes.put("Minor Third", 3);
    	this.intervalSizes.put("Major Third", 4);
    	this.intervalSizes.put("Perfect Fourth", 5);
    	this.intervalSizes.put("Tritone", 6);
        this.intervalSizes.put("Perfect Fifth", 7);
        this.intervalSizes.put("Minor Sixth", 8);
        this.intervalSizes.put("Major Sixth", 9);
        this.intervalSizes.put("Minor Seventh", 10);
        this.intervalSizes.put("Major Seventh", 11);
        this.intervalSizes.put("Perfect Octave", 12);
        this.intervalSizes.put("Minor Ninth", 13);
        this.intervalSizes.put("Major Ninth", 14);
        this.intervalSizes.put("Minor Tenth", 15);
        this.intervalSizes.put("Major Tenth", 16);
    }
    
    
    /**
     * Calculates the actual instrument from the instrument type and the range stored in the card.
     * @return an instrument name that can be used in jFugue.
     */
    public String getActualInstrument() {
    	String actualInstrument = null;
    	
    	if (this.getInstrument() == "Strings") {
    		switch (this.getRange()) {
				case "Low": actualInstrument = "Cello";
				break;
				case "Middle": actualInstrument = "Viola";
				break;
				case "High": actualInstrument = "Violin";
				break;
			}	
			
    	}
    	
    	else if (this.getInstrument() == "Brass") {
    		switch (this.getRange()) {
				case "Low": actualInstrument = "Trombone";
				break;
				case "Middle": actualInstrument = "French_Horn";
				break;
				case "High": actualInstrument = "Trumpet";
				break;
			}
    	}
    	
    	else if (this.getInstrument() == "Saxophone") {
    		switch (this.getRange()) {
				case "Low": actualInstrument = "Baritone_Sax";
				break;
				case "Middle": actualInstrument = "Alto_Sax";
				break;
				case "High": actualInstrument = "Soprano_Sax";
				break;
			}
    	}
    	
    	else if (this.getInstrument() == "Double Reed") {
    		switch (this.getRange()) {
				case "Low": actualInstrument = "Bassoon";
				break;
				case "Middle": actualInstrument = "English_Horn";
				break;
				case "High": actualInstrument = "Oboe";
				break;
			}
    	}
    	
		else if (this.getInstrument() == "Harpsichord") {
    		actualInstrument = "Harpischord"; //This is a typing error in the library.
    	}
		
    	else if (this.getInstrument() == "Organ") {
    		actualInstrument = "Church_Organ";
    	}
    	
    	else {
    		actualInstrument = this.getInstrument();
    	}
    	
    	return actualInstrument;
    }

    /**
     * Since the features do not state a concrete note, a random note in the given range must be created.
     * @return the randomly generated note in the form of an integer, which can be used by jFugue.
     */
    private int createNote1() {
    	Random random = new Random();
    	int semitonesInOneOctave = 12;
		int bound = semitonesInOneOctave*2 - this.intervalSizes.get(this.interval);
    	int note = random.nextInt(bound);
    	
    	if (this.range == "Low") {
    		note += semitonesInOneOctave*2;
    	}
    	else if (this.range == "Middle") {
    		note += semitonesInOneOctave*4;
    	}
    	else {
    		note += semitonesInOneOctave*6;
    	}
    	
    	return note;
    }
    
    /**
     * @return the note generated by the 'createNote1()' method.
     */
    public int getNote1() {
    	return this.note1;
    }
    
    
    /**
     * Adding the number of the interval to the number of note1 gives note2.
     * @return the second note that is derived from the first one.
     */
    public int getNote2() {
    	return this.note1 + this.intervalSizes.get(this.interval);
    }
}