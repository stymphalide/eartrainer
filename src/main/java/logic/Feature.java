package logic;


public abstract class Feature {
    String name;
    String feature_name;

    Feature(String name, String feature_name) {
        this.name = name;
        this.feature_name;
    }

    public String getNameString()
    {
        return this.name;
    }

    public String getFeatureName()
    {
        return this.feature_name;
    }

    public abstract String getStaccatoString();
}


