package logic;

import java.util.*;

public class Level1 extends Level {
	private int correctAnswers;
	private int wrongAnswers;
	private int totalQuestions;
	private List<String> allowedInstruments;
	private List<String> allowedOrders;
	private List<String> allowedRanges;
	private List<String> allowedIntervals;
	private Question activeQuestion;
	private Card answer;
	
	public Level1() {
		super(1);
		this.correctAnswers = 0;
		this.wrongAnswers = 0;
		this.totalQuestions = 10;
		this.allowedInstruments = instantiateFeatures("Piano", "Strings", "Brass");
		this.allowedOrders = instantiateFeatures("Upwards", "Downwards", "Chordal");
		this.allowedRanges = instantiateFeatures("Low", "Middle", "High");
		this.allowedIntervals = instantiateFeatures("Perfect Fourth", "Perfect Fifth", "Perfect Octave");
		this.activeQuestion = getQuestion();
        this.answer = correctAnswer(this.activeQuestion);
	}

	@Override
	public Question getActiveQuestion() {
		return this.activeQuestion;
	}

	@Override
	public int getCorrectAnswers() {
		return this.correctAnswers;
	}

	@Override
	public int getWrongAnswers() {
		return this.wrongAnswers;
	}

	@Override
	public int getTotalAnswers() {
		return this.correctAnswers + this.wrongAnswers;
	}

	@Override
	public int getTotalQuestions() {
		return this.totalQuestions;
	}

	@Override
	public List<String> getAllowedInstruments(){
		return this.allowedInstruments;
	}
	
	@Override
	public List<String> getAllowedOrders(){
		return this.allowedOrders;
	}
	
	@Override
	public List<String> getAllowedRanges(){
		return this.allowedRanges;
	}
	
	@Override
	public List<String> getAllowedIntervals(){
		return this.allowedIntervals;
	}
	
	@Override
	public void nextQuestion() {
		if(this.validateAnswer(this.activeQuestion, this.answer)) {
			this.correctAnswers++;
		} else {
			this.wrongAnswers++;
		}
		this.activeQuestion = this.getQuestion();
	}

	@Override
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

	@Override
	public boolean validateAnswer(Question question, Card card) {

		if (this.answer == correctAnswer(this.activeQuestion)) {
			return true;
		}
		else {
			return false;
		}

	}

	@Override
	public boolean isFinished() {
		return this.correctAnswers + this.wrongAnswers == this.totalQuestions;
	}
	
	public void setAnswer(Card answer) {
		this.answer = answer;
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
