package logic;


public class Range {
    String name;
    String[] range;

    public Range(String range) {
        this.name = range;
        this.range = range.split("-");
    }
}