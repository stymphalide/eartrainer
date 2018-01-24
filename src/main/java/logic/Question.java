package logic;

import java.util.*;

public class Question {
    Card card1;
    Card card2;
    
    public Question(List<Instrument> allowedInstruments,
    				List<Order> allowedOrders,
    				List<Range> allowedRanges,
    				List<Interval> allowedIntervals) {
    	Random random = new Random();
    	int[] card1 = {random.nextInt(3), random.nextInt(3), random.nextInt(3), random.nextInt(3)};
    	int[] card2 = new int[4];
    	do {
    		card2[0] = random.nextInt(3);
    		card2[1] = random.nextInt(3);
    		card2[2] = random.nextInt(3);
    		card2[3] = random.nextInt(3);
    	}while(card1 == card2);
    	this.card1 = new Card(allowedInstruments.get(card1[0]),
    						  allowedOrders.get(card1[1]),
    						  allowedRanges.get(card1[2]),
    						  allowedIntervals.get(card1[3]));
    	this.card2 = new Card(allowedInstruments.get(card2[0]),
				  			  allowedOrders.get(card2[1]),
				  			  allowedRanges.get(card2[2]),
				  			  allowedIntervals.get(card2[3]));
    }
}