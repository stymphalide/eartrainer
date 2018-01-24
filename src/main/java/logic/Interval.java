package logic;


import java.util.HashMap;

public class Interval extends Feature {
    HashMap<String, Integer> intervalSizes;
    int size;


    public Interval(String interval) {
        super(interval, "interval");
        setIntervalSizes();
        this.size = this.intervalSizes.get(interval);
    }
    
    public String getOrderName() {
        return this.name;
    }

    private void setIntervalSizes() {
        this.intervalSizes.put("perfect_fourth", 5);
        this.intervalSizes.put("perfect_fifth", 7);
        this.intervalSizes.put("perfect_octave", 12);
    }
}