package kukoinApi;

public class ShortTicker {
    private String name;
    private float rate;

    public ShortTicker(String name, float rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public float getRate() {
        return rate;
    }
}
