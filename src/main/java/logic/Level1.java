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
		this.allowedOrders = instantiateFeatures("upwards", "downwards", "chordal");
		this.allowedRanges = instantiateFeatures("low", "middle", "high");
		this.allowedIntervals = instantiateFeatures("perfect_fourth", "perfect_fifth", "perfect_octave");
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
		return new Card(this.allowedInstruments.get(0), 
						this.allowedOrders.get(0),
						this.allowedRanges.get(0),
						this.allowedIntervals.get(0)); // @TODO: Implementate this and delete constructor
	}

	@Override
	public boolean validateAnswer(Question question, Card card) {
		return true; // @TODO: Implement this properly
	}

	@Override
	public boolean isFinished() {
		return this.correctAnswers + this.wrongAnswers == this.totalQuestions;
	}
	
	private ArrayList<String> instantiateFeatures(String feature1, String feature2, String feature3){
		ArrayList<String> features = new ArrayList();
		features.add(feature1);
		features.add(feature2);
		features.add(feature3);
		return features;
	}
}
