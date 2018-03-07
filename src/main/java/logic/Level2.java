package logic;

public class Level2 extends Level {
	
	public Level2() {
		super(2);
		this.correctAnswers = 0;
		this.wrongAnswers = 0;
		this.totalQuestions = 10;
		this.allowedInstruments = instantiateFeatures("Piano", "Harpsichord", "Organ");
		this.allowedOrders = instantiateFeatures("Upwards", "Downwards", "Chordal");
		this.allowedRanges = instantiateFeatures("Low", "Middle", "High");
		this.allowedIntervals = instantiateFeatures("Minor Second", "Major Second", "Minor Third");
		this.activeQuestion = getQuestion();
	}

}
