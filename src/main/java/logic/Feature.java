package logic;


public abstract class Feature {
    String name;
    String feature_name;

    Feature(String name, String feature_name) {
        this.name = name;
        this.feature_name;
    }

    public abstract String getNameString();

    public abstract String getFeatureName();

    public abstract String getStaccatoString();
}


