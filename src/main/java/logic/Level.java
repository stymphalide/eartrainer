package logic;

public class Level {
    int level;
    boolean isFinished;
    int n;

    public Level(int n) {
        this.level = n;
        this.isFinished = false;
        this.n = 0;
    }
    public void nextQuestion() {
        n++;
    }

    public boolean isFinished() {
        return n >= 10;
    }
    
    public int getN() {
        return n;
    }

    public int getLevel() {
        return level;
    }

}