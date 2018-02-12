package logic;

import java.util.List;

public abstract class Level {
    int levelNumber;

    Level(int n) {
        this.levelNumber = n;
    }

    public abstract void nextQuestion();

    public abstract logic.Question getActiveQuestion();

    public abstract List<String> getAllowedRanges();

    public abstract List<String> getAllowedInstruments();

    public abstract List<String> getAllowedIntervals();

    public abstract List<String> getAllowedOrders();

    public abstract int getCorrectAnswers();

    public abstract int getWrongAnswers();

    public abstract int getTotalAnswers();

    public abstract int getTotalQuestions();

    public int getLevelNumber() {
        return this.levelNumber;
    }

    public abstract logic.Question getQuestion();

    public abstract logic.Card correctAnswer(logic.Question question);

    public abstract boolean validateAnswer(logic.Question question, logic.Card proposedAnswer);

    public abstract boolean isFinished();
    
    public abstract void setAnswer(Card answer);
}

