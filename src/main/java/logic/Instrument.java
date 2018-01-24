package logic;

// @Todo: Make this into an abstract class ==> Strings are several Instruments
public class Instrument extends Feature {

    public Instrument(String instrument) {
        super(instrument, "instrument");
    }

    public String getStaccatoString(Range range) {
        return "Some Staccato String";
    }
    

}