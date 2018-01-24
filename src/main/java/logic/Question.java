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
        int[] card2Indices = int[length];
    	do {
    		card2Indices = randomCardIndices(length, bound);
    	} while(card1Indices == card2Indices);

        List<Feature>[] allowedFeatures = {allowedInstruments,
                                           allowedOrders,
                                           allowedRanges,
                                           allowedIntervals};

    	this.card1 = generateCard(card1Indices, allowedFeatures);
    	this.card2 = generateCard(card2Indices, allowedFeatures);
    }

    private int[] randomCardIndices(int length, int bound) {
        
        Random random = new Random();
        int[] card = new int[length];

        for (int i = 0; i < length; ) {
            card[i] = random.nextInt(bound);
        }
        return card;
    }
    private Card generateCard(int[] indices, List<Feature>[] allowedFeatures) {
        length = indices.length;
        Feature[] features = Feature[length];
        for (int i = 0; i < length; i++) {
            features[i] = allowedFeatures[i].get(indices[i]);
        }
        // @TODO: Create Constructor from array.
        return new Card(features[0], features[1], features[2], features[3]);
    }

}