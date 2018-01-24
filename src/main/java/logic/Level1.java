package logic;

public class Level1 extends Level {

	private int correctAnswers;
	private int wrongAnswers;
	private int totalQuestions;
	
	public Level1() {
		super(1);
		this.correctAnswers = 0;
		this.wrongAnswers = 0;
		this.totalQuestions = 10;
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
	public void play() {
		// TODO Auto-generated method stub
	}

}
