package logic;


public abstract class Feature {
    String name;
    String featureName;

    Feature(String name, String featureName) {
        this.name = name;
        this.featureName = featureName;
    }

    public String getNameString()
    {
        return this.name;
    }

    public String getFeatureName()
    {
        return this.featureName;
    }
}


