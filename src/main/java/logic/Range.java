package logic;


public class Range extends Feature {
    String[] ranges;

    public Range(String range) {
        super(range, "range");
        this.ranges = range.split("-");
    }
}