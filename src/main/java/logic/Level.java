package logic;

import java.util.List

public interface Level {

    int correctAnswers;
    int wrongAnswers;

    public Level(int n);

    public List<logic.Range> getAllowedRanges()

    public List<logic.Instrument> getAllowedInstruments()

    public List<logic.Interval> getAllowedIntervals()

    public List<logic.Order> getAllowedOrders()

    public int getCorrectAnswers()

    public int getWrongAnswers()

    public int getTotalAnswers()

    public int getTotalQuestions()

    public logic.Question getQuestion()

    public void play()

    public logic.Card validateAnswer(logic.Question question, logic.Card proposedAnswer)

    public boolean validateAnswer(logic.Question question, logic.Card proposedAnswer)
}




