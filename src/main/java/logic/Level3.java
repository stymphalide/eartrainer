package logic;

public class Level3 extends Level {
	
	public Level3() {
		super(3);
		this.correctAnswers = 0;
		this.wrongAnswers = 0;
		this.totalQuestions = 10;
		this.allowedInstruments = instantiateFeatures("Saxophone", "Double Reed", "Brass");
		this.allowedOrders = instantiateFeatures("Upwards", "Downwards", "Chordal");
		this.allowedRanges = instantiateFeatures("Low", "Middle", "High");
		this.allowedIntervals = instantiateFeatures("Major Sixth", "Minor Seventh", "Major Seventh");
		this.activeQuestion = getQuestion();
	}

}
