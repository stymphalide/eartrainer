package logic;

import java.util.*;

public class Card {
	
	private String instrument;
	private String order;
	private String range;
	private String interval;
	private HashMap intervalSizes;
    
    public Card(String instrument, 
    			String order,
    			String range, 
    			String interval) {
    	this.instrument = instrument;
    	this.order = order;
    	this.range = range;
    	this.interval = interval;
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
    	this.intervalSizes = new HashMap<String, Integer>();
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
    
    //TODO: Implement this.
    public String getNote1() {    	
    	return "C4";
    }
    
    public String getNote2() {
    	return "G4";
    }
}