package logic;

import java.util.*;

public class Question {
    private Card card1;
    private Card card2;
    
    // @TODO: Maybe add another variable in the level: allowed Features.
    public Question(List<Instrument> allowedInstruments,
    				List<Order> allowedOrders,
    				List<Range> allowedRanges,
    				List<Interval> allowedIntervals) {

        int length = 4;
        int bound = allowedInstruments.size(); // Assuming the size of every list is the same.
    	int[] card1Indices = randomCardIndices(length, bound);
        int[] card2Indices;
    	do {
    		card2Indices = randomCardIndices(length, bound);
    	} while(card1Indices == card2Indices);

    	this.card1 = generateCard(card1Indices, allowedInstruments, allowedOrders, allowedRanges, allowedIntervals);
    	this.card2 = generateCard(card2Indices, allowedInstruments, allowedOrders, allowedRanges, allowedIntervals);
    }

    private int[] randomCardIndices(int length, int bound) {
        
        Random random = new Random();
        int[] card = new int[length];

        for (int i = 0; i < length; ) {
            card[i] = random.nextInt(bound);
        }
        return card;
    }
    private Card generateCard(int[] indices,
                              List<Instrument> allowedInstruments,
                              List<Order> allowedOrders,
                              List<Range> allowedRanges,
                              List<Interval> allowedIntervals) {

        Instrument instrument = allowedInstruments.get(indices[0]);
        Order order           = allowedOrders.get(indices[1]);
        Range range           = allowedRanges.get(indices[2]);
        Interval interval     = allowedIntervals.get(indices[3]);
        return new Card(instrument, order, range, interval);
    }

}