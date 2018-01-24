package logic;


public class Range extends Feature {
    private String[] ranges;

    public Range(String range) {
        super(range, "range");
        this.ranges = range.split("-");
    }
}