package logic;


import java.util.HashMap

public class Interval {
    String name;
    HashMap<String, Int> intervalSizes;


    Interval(String interval) {
        this.name = interval;
        this.intervalSizes = getIntervalSizes();
        this.size = this.intervalSizes.get(interval);
    }
    public String getOrderName() {
        return this.name;
    }

    private HashMap<String, Int> getIntervalSizes() {
        this.intervalSizes.put("perfect_fourth", 5);
        this.intervalSizes.put("perfect_fifth", 7);
        this.intervalSizes.put("perfect_octave", 12);
    }
}