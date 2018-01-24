package logic;


public class Card {
    Instrument instrument;
    Interval interval;
    Range range;
    Order order;
    
    public Card(Instrument instrument, Interval interval, Range range, Order order) {
    	this.instrument = instrument;
    	this.interval = interval;
    	this.range = range;
    	this.order = order;
    }
}