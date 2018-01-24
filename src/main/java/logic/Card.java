package logic;


public class Card {
    Instrument instrument;
    Order order;
    Range range;
    Interval interval;
    
    public Card(Instrument instrument, Order order, Range range, Interval interval) {
    	this.instrument = instrument;
    	this.order = order;
    	this.range = range;
    	this.interval = interval;
    }
}