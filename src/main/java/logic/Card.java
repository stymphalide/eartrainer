package logic;

import java.util.*;

public class Card {
	
	private HashMap<String, Integer> intervalSizes = new HashMap<String, Integer>();
	private String instrument;
	private String order;
	private String range;
	private String interval;
	private int note1;
	
    
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
    
    
    public String getInstrument() {
    	return this.instrument;
    }
    
    public String getOrder() {
    	return this.order;
    }
    
    public String getRange() {
    	return this.range;
    }
    
    public String getInterval() {
    	return this.interval;
    }
    
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
    
    
    public String getActualInstrument() {
    	String actualInstrument;
    	
    	if (this.getInstrument() == "Strings") {
    		if (this.getRange() == "Low") {
    			actualInstrument = "Cello";
    		}
    		else if (this.getRange() == "Middle") {
    			actualInstrument = "Viola";
    		}
    		else {
    			actualInstrument = "Violin";
    		}
    	}
    	
    	else if (this.getInstrument() == "Brass") {
    		if (this.getRange() == "Low") {
    			actualInstrument = "Trombone";
    		}
    		else if (this.getRange() == "Middle") {
    			actualInstrument = "French_Horn";
    		}
    		else {
    			actualInstrument = "Trumpet";
    		}
    	}
    	
    	else if (this.getInstrument() == "Saxophone") {
    		if (this.getRange() == "Low") {
    			actualInstrument = "Baritone_Sax";
    		}
    		else if (this.getRange() == "Middle") {
    			actualInstrument = "Alto_Sax";
    		}
    		else {
    			actualInstrument = "Soprano_Sax";
    		}
    	}
    	
    	else if (this.getInstrument() == "Double Reed") {
    		if (this.getRange() == "Low") {
    			actualInstrument = "Bassoon";
    		}
    		else if (this.getRange() == "Middle") {
    			actualInstrument = "English_Horn";
    		}
    		else {
    			actualInstrument = "Oboe";
    		}
    	}
    	
    	else if (this.getInstrument() == "Organ") {
    		actualInstrument = "Church_Organ";
    	}
    	
    	else {
    		actualInstrument = this.getInstrument();
    	}
    	
    	return actualInstrument;
    }

    private int createNote1() {
    	Random random = new Random();
    	int bound = 24 - this.intervalSizes.get(this.interval);
    	int note = random.nextInt(bound);
    	
    	if (this.range == "Low") {
    		note += 24;
    	}
    	else if (this.range == "Middle") {
    		note += 48;
    	}
    	else {
    		note += 72;
    	}
    	
    	return note;
    }
    
    public int getNote1() {
    	return this.note1;
    }
    
    public int getNote2() {
    	return this.note1 + this.intervalSizes.get(this.interval);
    }
}