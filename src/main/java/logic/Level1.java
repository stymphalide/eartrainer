package logic;

import java.util.*;

public class Level1 extends Level {

	private int correctAnswers;
	private int wrongAnswers;
	private int totalQuestions;
	private List<Instrument> allowedInstruments;
	
	public Level1() {
		super(1);
		this.correctAnswers = 0;
		this.wrongAnswers = 0;
		this.totalQuestions = 10;
		this.allowedInstruments = instantiateInstruments("Piano", "Strings", "Brass");
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
	public void play() {
		// TODO Auto-generated method stub
	}

	private ArrayList<Instrument> instantiateInstruments(String instrument1, String instrument2, String instrument3){
		ArrayList<Instrument> instruments = new ArrayList();
		instruments.add(new Instrument(instrument1));
		instruments.add(new Instrument(instrument2));
		instruments.add(new Instrument(instrument3));
		return instruments;
	}
}
