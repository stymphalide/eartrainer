package logic;

import java.util.List;

public abstract class Level {
    int levelNumber;

    Level(int n) {
        this.levelNumber = n;
    }

    public abstract List<logic.Range> getAllowedRanges();

    public abstract List<logic.Instrument> getAllowedInstruments();

    public abstract List<logic.Interval> getAllowedIntervals();

    public abstract List<logic.Order> getAllowedOrders();

    public abstract int getCorrectAnswers();

    public abstract int getWrongAnswers();

    public abstract int getTotalAnswers();

    public abstract int getTotalQuestions();

    public int getLevelNumber() {
        return level;
    }

    public abstract logic.Question getQuestion();

    public abstract void play(Card card);

    public abstract logic.Card correctAnswer(logic.Question question, logic.Card proposedAnswer);

    public abstract boolean validateAnswer(logic.Question question, logic.Card proposedAnswer);

    public abstract boolean isFinished();
}

