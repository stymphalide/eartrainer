package logic;


public class Range extends Feature {
    private String[] ranges;

    public Range(String range) {
        super(range, "range");
        this.ranges = range.split("-");
    }
    
    public String getLowerNote() {
    	return ranges[0];
    }
    
    public String getUpperNote() {
    	return ranges[1];
    }
}