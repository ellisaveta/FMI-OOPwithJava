public class Stock {
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;

    public Stock(String symbol, String name) {
        if(symbol == null) {
            this.symbol = "No symbol";
        }
        else {
            this.symbol = symbol;
        }
        if(name == null) {
            this.name = "No name";
        }
        else {
            this.name = name;
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPreviousClosingPrice() {
        return previousClosingPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setPreviousClosingPrice(double previousClosingPrice) {
        if(previousClosingPrice < 0) {
            this.previousClosingPrice = 0.0;
        }
        else {
            this.previousClosingPrice = previousClosingPrice;
        }
    }

    public void setCurrentPrice(double currentPrice) {
        if(currentPrice < 0) {
            this.currentPrice = 0;
        }
        else {
            this.currentPrice = currentPrice;
        }
    }

    public double changePercent()
    {
        return ((currentPrice - previousClosingPrice) / previousClosingPrice) * 100;
    }

    @Override
    public String toString() {
        return String.format("Symbol: %s\nName: %s\nPrevious closing price: %.2f\nCurrent price: %.2f\n",
                symbol, name, previousClosingPrice, currentPrice);
    }
}
