package logic;


public class Card {
    Instrument instrument;
    Order order;
    Range range;
    Interval interval;
    
    public Card(Instrument instrument, 
                Order order,
                Range range, 
                Interval interval) {
    	this.instrument = instrument;
    	this.order = order;
    	this.range = range;
    	this.interval = interval;
    }
    
    public Card(String instrument, 
            	String order,
            	String range, 
            	String interval) {
    	this(new Instrument(instrument),
    		 new Order(order),
    		 new Range(range),
    		 new Interval(interval));
    }
    
    public String getNote1() {
    	return "C4";
    }
    
    public String getNote2() {
    	return "G4";
    }
    
}