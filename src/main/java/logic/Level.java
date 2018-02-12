package logic;

import java.util.List;

public abstract class Level {
    int levelNumber;

    Level(int n) {
        this.levelNumber = n;
    }

    public abstract List<String> getAllowedRanges();

    public abstract List<String> getAllowedInstruments();

    public abstract List<String> getAllowedIntervals();

    public abstract List<String> getAllowedOrders();

    public abstract int getCorrectAnswers();

    public abstract int getWrongAnswers();

    public abstract int getTotalAnswers();

    public abstract int getTotalQuestions();

    public abstract logic.Question getQuestion();

    public abstract logic.Card correctAnswer(logic.Question question, logic.Card proposedAnswer);

    public abstract boolean validateAnswer(logic.Question question, logic.Card proposedAnswer);
}

