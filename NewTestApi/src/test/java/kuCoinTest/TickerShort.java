package kuCoinTest;

public class TickerShort {
    private String symbol;
    private Float changeRate;

    public TickerShort(String symbol, Float changeRate) {
        this.symbol = symbol;
        this.changeRate = changeRate;
    }

    public String getSymbol() {
        return symbol;
    }

    public Float getChangeRate() {
        return changeRate;
    }
}
