package logic;

import java.util.*;

public class Level1 extends Level {

	private int correctAnswers;
	private int wrongAnswers;
	private int totalQuestions;
	private List<Instrument> allowedInstruments;
	private List<Order> allowedOrders;
	private List<Range> allowedRanges;
	private List<Interval> allowedIntervals;
	
	public Level1() {
		super(1);
		this.correctAnswers = 0;
		this.wrongAnswers = 0;
		this.totalQuestions = 10;
		this.allowedInstruments = instantiateInstruments("Piano", "Strings", "Brass");
		this.allowedOrders = instantiateOrders("upwards", "downwards", "chordal");
		this.allowedRanges = instantiateRanges("C2-B3", "C4-B5", "C6-B7");
		this.allowedIntervals = instantiateIntervals("perfect_fourth", "perfect_fifth", "perfect_octave");
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
	public List<Instrument> getAllowedInstruments(){
		return this.allowedInstruments;
	}
	
	@Override
	public List<Order> getAllowedOrders(){
		return this.allowedOrders;
	}
	
	@Override
	public List<Range> getAllowedRanges(){
		return this.allowedRanges;
	}
	
	@Override
	public List<Interval> getAllowedIntervals(){
		return this.allowedIntervals;
	}
	
	@Override
	public Question getQuestion(){
		return new Question(this.allowedInstruments, this.allowedOrders, this.allowedRanges, this.allowedIntervals);
	}
	
	@Override
	public void play(Card card) {
		// TODO Auto-generated method stub
	}

	@Override
	public Card correctAnswer(Question question, Card card) {
		return new Card(); // @TODO: Implementate this and delete constructor
	}

	@Override
	public boolean validateAnswer(Question question, Card card) {
		return true; // @TODO: Implementate this properly
	}

	private ArrayList<Instrument> instantiateInstruments(String instrument1, String instrument2, String instrument3){
		ArrayList<Instrument> instruments = new ArrayList();
		instruments.add(new Instrument(instrument1));
		instruments.add(new Instrument(instrument2));
		instruments.add(new Instrument(instrument3));
		return instruments;
	}
	
	private ArrayList<Order> instantiateOrders(String order1, String order2, String order3){
		ArrayList<Order> orders = new ArrayList();
		orders.add(new Order(order1));
		orders.add(new Order(order2));
		orders.add(new Order(order3));
		return orders;
	}
	
	private ArrayList<Range> instantiateRanges(String range1, String range2, String range3){
		ArrayList<Range> ranges = new ArrayList();
		ranges.add(new Range(range1));
		ranges.add(new Range(range2));
		ranges.add(new Range(range3));
		return ranges;
	}
	
	private ArrayList<Interval> instantiateIntervals(String interval1, String interval2, String interval3){
		ArrayList<Interval> intervals = new ArrayList();
		intervals.add(new Interval(interval1));
		intervals.add(new Interval(interval2));
		intervals.add(new Interval(interval3));
		return intervals;
	}
}
