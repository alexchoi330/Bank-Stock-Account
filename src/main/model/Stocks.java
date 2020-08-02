package model;

public class Stocks {
    String name;
    double stockWorth;


    // REQUIRES:
    //EFFECTS: creates a stock with its name and how much its worth
    public Stocks(String sname, double stocWorth) {
        name = sname;
        stockWorth = stocWorth;

    }

    public String getStockName() {
        return name;
    }

    public double getStockWorth() {
        return stockWorth;
    }


}