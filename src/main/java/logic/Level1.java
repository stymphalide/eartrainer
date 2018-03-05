package logic;

public class Level1 extends Level {
	
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
	}

}
