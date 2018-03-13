package logic;

import java.util.*;
import java.time.*;

public class Level {
    private int levelNumber;
    private int correctAnswers;
    private int wrongAnswers;
    private int totalQuestions;
    private List<String> allowedInstruments;
    private List<String> allowedOrders;
    private List<String> allowedRanges;
    private List<String> allowedIntervals;
    private Question activeQuestion;
    private Card answer;
    private Sound soundThread;
    private Instant startTime;
    private Instant endTime;

    public Level(int n) {
        this.levelNumber = n;
		this.correctAnswers = 0;
		this.wrongAnswers = 0;
		this.totalQuestions = 10;
		switch(levelNumber){
			case 1: this.allowedInstruments = instantiateFeatures("Piano", "Strings", "Brass");
					this.allowedIntervals = instantiateFeatures("Perfect Fourth", "Perfect Fifth", "Perfect Octave");
					break;
			case 2: this.allowedInstruments = instantiateFeatures("Piano", "Harpsichord", "Organ");
					this.allowedIntervals = instantiateFeatures("Minor Second", "Major Second", "Minor Third");
					break;
			case 3: this.allowedInstruments = instantiateFeatures("Saxophone", "Double Reed", "Brass");
					this.allowedIntervals = instantiateFeatures("Major Sixth", "Minor Seventh", "Major Seventh");
					break;
		}
		this.allowedOrders = instantiateFeatures("Upwards", "Downwards", "Chordal");
		this.allowedRanges = instantiateFeatures("Low", "Middle", "High");
		this.activeQuestion = getQuestion();

        // Instantiate the Sound Thread
        this.soundThread = new logic.Sound();
        this.soundThread.setDaemon(true);
        this.soundThread.start();
        this.startTime = Instant.now();
    }

    public void setAnswer(Card answer) {
		this.answer = answer;
	}

    public void nextQuestion() {
		System.out.println(this.activeQuestion.getCard1().getInstrument()+", "+
						   this.activeQuestion.getCard1().getOrder()+", "+
						   this.activeQuestion.getCard1().getRange()+", "+
						   this.activeQuestion.getCard1().getInterval());
		System.out.println(this.activeQuestion.getCard2().getInstrument()+", "+
				   		   this.activeQuestion.getCard2().getOrder()+", "+
				   		   this.activeQuestion.getCard2().getRange()+", "+
				   		   this.activeQuestion.getCard2().getInterval());
		System.out.println(this.correctAnswer(this.activeQuestion).getInstrument()+", "+
				   		   this.correctAnswer(this.activeQuestion).getOrder()+", "+
				   		   this.correctAnswer(this.activeQuestion).getRange()+", "+
				   		   this.correctAnswer(this.activeQuestion).getInterval());
		System.out.println(this.answer.getInstrument()+", "+
				   		   this.answer.getOrder()+", "+
				   		   this.answer.getRange()+", "+
				   		   this.answer.getInterval());
		if(this.validateAnswer(this.activeQuestion, this.answer)) {
			this.correctAnswers++;
		} else {
			this.wrongAnswers++;
		}
		this.activeQuestion = this.getQuestion();
	}

    public void play(Card card) {
        // Only play sound if the State is waiting
        System.out.println(this.soundThread.getState());
        if (this.soundThread.getState() == Thread.State.WAITING) {
            this.soundThread.play(card);
        } else {}
    }

    public Question getActiveQuestion() {
		return this.activeQuestion;
	}

    public List<String> getAllowedInstruments(){
		return this.allowedInstruments;
	}

    public List<String> getAllowedOrders(){
		return this.allowedOrders;
	}

    public List<String> getAllowedRanges(){
		return this.allowedRanges;
	}

    public List<String> getAllowedIntervals(){
		return this.allowedIntervals;
	}

    public int getCorrectAnswers() {
		return this.correctAnswers;
	}

    public int getWrongAnswers() {
		return this.wrongAnswers;
	}

    public int getTotalAnswers() {
		return this.correctAnswers + this.wrongAnswers;
	}

    public int getTotalQuestions() {
		return this.totalQuestions;
	}

    public int getLevelNumber() {
        return this.levelNumber;
    }

    public Question getQuestion(){
		return new Question(this.allowedInstruments, this.allowedOrders, this.allowedRanges, this.allowedIntervals);
	}

    public Card correctAnswer(Question question) {
		String answerInstrument;
		String answerOrder;
		String answerRange;
		String answerInterval;
		String[] card1 = {question.getCard1().getInstrument(),
						  question.getCard1().getOrder(),
						  question.getCard1().getRange(),
						  question.getCard1().getInterval()};
		String[] card2 = {question.getCard2().getInstrument(),
				  		  question.getCard2().getOrder(),
				  		  question.getCard2().getRange(),
				  		  question.getCard2().getInterval()};
		
		answerInstrument = correctFeature(card1[0], card2[0], this.allowedInstruments);
		answerOrder 	 = correctFeature(card1[1], card2[1], this.allowedOrders);
		answerRange 	 = correctFeature(card1[2], card2[2], this.allowedRanges);
		answerInterval   = correctFeature(card1[3], card2[3], this.allowedIntervals);
		
		return new Card(answerInstrument, answerOrder, answerRange, answerInterval);
	}

    public boolean validateAnswer(Question question, Card card) {
		Card correctAnswer = correctAnswer(this.activeQuestion);
		if (this.answer.getInstrument() .equals(correctAnswer.getInstrument()) &&
			this.answer.getOrder() .equals(correctAnswer.getOrder()) &&
			this.answer.getRange() .equals(correctAnswer.getRange()) &&
			this.answer.getInterval() .equals(correctAnswer.getInterval())) {
			return true;
		}
		else {
			return false;
		}

	}

    public Duration getLevelDuration() {
        if (this.endTime == null) {
            return Duration.between(this.startTime, Instant.now());
        } else {
            return Duration.between(this.startTime, this.endTime);
        }

    }

    public boolean isFinished() {
        if (this.getTotalAnswers() == this.totalQuestions) { // This -1 is required, since the query is done before the level is updated.
            soundThread.interrupt(); // Stop the sound Thread.
            soundThread = null; // is this correct? TODO
            this.endTime = Instant.now();
            return true;
        } else {
            return false;
        }
	}
    
    private ArrayList<String> instantiateFeatures(String feature1, String feature2, String feature3){
		ArrayList<String> features = new ArrayList();
		features.add(feature1);
		features.add(feature2);
		features.add(feature3);
		return features;
	}
    
    private String correctFeature(String feature1, String feature2, List<String> allowedFeatures) {
		if (feature1 == feature2) {
			return feature1;
		}
		else {
			int j = 3 - allowedFeatures.indexOf(feature1) - allowedFeatures.indexOf(feature2);
			return allowedFeatures.get(j);
		}
	}
    
}
