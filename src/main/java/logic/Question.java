package logic;

import java.util.*;

/**
 * This class generates the two cards that are given in a question.
 * It does this taking into account the allowed features of the current level.
 * Within these boundaries, the features of the cards are generated randomly; however, the two cards cannot be identical.
 * @author Tobias
 */
public class Question {
    private Card card1;
    private Card card2;
    
    /**
     * @param allowedInstruments
     * @param allowedOrders
     * @param allowedRanges
     * @param allowedIntervals
     */
    public Question(List<String> allowedInstruments,
    				List<String> allowedOrders,
    				List<String> allowedRanges,
    				List<String> allowedIntervals) {

        int length = 4;
        int bound = allowedInstruments.size(); // Assuming the size of every list is the same.
        int[] card1Indices = randomCardIndices(length, bound);
        int[] card2Indices;
    	do {
    		card2Indices = randomCardIndices(length, bound);
    	} while(Arrays.equals(card1Indices, card2Indices));
    	this.card1 = generateCard(card1Indices, allowedInstruments, allowedOrders, allowedRanges, allowedIntervals);
    	this.card2 = generateCard(card2Indices, allowedInstruments, allowedOrders, allowedRanges, allowedIntervals);
    }

    /**
     * @return the first card of the question.
     */
    public Card getCard1() {
        return this.card1;
    }
    /**
     * @return the second card of the question.
     */
    public Card getCard2() {
        return this.card2;
    }

    /**
     * @param length
     * @param bound
     * @return a random number used to generate a card.
     */
    private int[] randomCardIndices(int length, int bound) {
        Random random = new Random();
        int[] card = new int[length];

        for (int i = 0; i < length; i++) {
            card[i] = random.nextInt(bound);
        }
        return card;
    }
    /**
     * @param indices
     * @param allowedInstruments
     * @param allowedOrders
     * @param allowedRanges
     * @param allowedIntervals
     * @return a new randomly generated card.
     */
    private Card generateCard(int[] indices,
                              List<String> allowedInstruments,
                              List<String> allowedOrders,
                              List<String> allowedRanges,
                              List<String> allowedIntervals) {

    	String instrument = allowedInstruments.get(indices[0]);
    	String order      = allowedOrders.get(indices[1]);
    	String range      = allowedRanges.get(indices[2]);
    	String interval   = allowedIntervals.get(indices[3]);
        return new Card(instrument, order, range, interval);
    }

}