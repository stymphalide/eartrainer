package logic;

import java.util.*;

public class Card {
	String instrument;
	String order;
	String range;
	String interval;
    
    public Card(String instrument, 
    			String order,
    			String range, 
    			String interval) {
    	this.instrument = instrument;
    	this.order = order;
    	this.range = range;
    	this.interval = interval;
    }
    
    
    //TODO: Implement getters for the features.
    
    private void setIntervalSizes() {
    	this.intervalSizes = new HashMap<String, Integer>();
        this.intervalSizes.put("perfect_fourth", 5);
        this.intervalSizes.put("perfect_fifth", 7);
        this.intervalSizes.put("perfect_octave", 12);
    }
    
    //TODO: Implement this.
    public String getNote1() {    	
    	return "C4";
    }
    
    public String getNote2() {
    	return "G4";
    }
    
}