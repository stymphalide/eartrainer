package logic;


public abstract class Feature {
    String name;
    String featureName;

    Feature(String name, String feature_name) {
        this.name = name;
        this.featureName;
    }

    public String getNameString()
    {
        return this.name;
    }

    public String getFeatureName()
    {
        return this.featureName;
    }

    public abstract String getStaccatoString();
}


