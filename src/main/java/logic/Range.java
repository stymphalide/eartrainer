package logic;


public class Range {
    String name;

    public Instrument(Range range) {
        this.name = range;
        this.range = range.split("-")
    }
}