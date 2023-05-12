package kukoinApi;

public class NewTickerData {
    private String symbol;
    private String symbolName;
    private String buy;
    private String sell;
    private String changeRate;
    private String changePrice;
    private String high;
    private String low;
    private String vol;
    private String volValue;
    private String last;
    private String averagePrice;
    private String takerFeeRate;
    private String makerFeeRate;
    private String takerCoefficient;
    private String makerCoefficient;

    public NewTickerData(String symbol, String symbolName, String buy, String sell, String changeRate, String changePrice, String high, String low, String vol, String volValue, String last, String averagePrice, String takerFeeRate, String makerFeeRate, String takerCoefficient, String makerCoefficient) {
        this.symbol = symbol;
        this.symbolName = symbolName;
        this.buy = buy;
        this.sell = sell;
        this.changeRate = changeRate;
        this.changePrice = changePrice;
        this.high = high;
        this.low = low;
        this.vol = vol;
        this.volValue = volValue;
        this.last = last;
        this.averagePrice = averagePrice;
        this.takerFeeRate = takerFeeRate;
        this.makerFeeRate = makerFeeRate;
        this.takerCoefficient = takerCoefficient;
        this.makerCoefficient = makerCoefficient;
    }

    public NewTickerData() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public String getBuy() {
        return buy;
    }

    public String getSell() {
        return sell;
    }

    public String getChangeRate() {
        return changeRate;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getVol() {
        return vol;
    }

    public String getVolValue() {
        return volValue;
    }

    public String getLast() {
        return last;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public String getTakerFeeRate() {
        return takerFeeRate;
    }

    public String getMakerFeeRate() {
        return makerFeeRate;
    }

    public String getTakerCoefficient() {
        return takerCoefficient;
    }

    public String getMakerCoefficient() {
        return makerCoefficient;
    }

    @Override
    public String toString() {
        return "\n\nNewTickerData{" +
                "symbol='" + symbol + '\'' +
                "\n, symbolName='" + symbolName + '\'' +
                "\n, buy='" + buy + '\'' +
                "\n, sell='" + sell + '\'' +
                "\n, changeRate='" + changeRate + '\'' +
                "\n, changePrice='" + changePrice + '\'' +
                "\n, high='" + high + '\'' +
                "\n, low='" + low + '\'' +
                "\n, vol='" + vol + '\'' +
                "\n, volValue='" + volValue + '\'' +
                "\n, last='" + last + '\'' +
                "\n, averagePrice='" + averagePrice + '\'' +
                "\n, takerFeeRate='" + takerFeeRate + '\'' +
                "\n, makerFeeRate='" + makerFeeRate + '\'' +
                "\n, takerCoefficient='" + takerCoefficient + '\'' +
                "\n, makerCoefficient='" + makerCoefficient + '\'' +
                '}';
    }
}
