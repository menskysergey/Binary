package json;

public enum Currencies{
    EURUSD("frxEURUSD"), GBPUSD("frxGBPUSD"), AUDUSD("frxAUDUSD"), EURAUD("frxEURAUD"), EURCAD("frxEURCAD"), EURCHF("frxEURCHF"),
    EURGBP("frxEURGBP"), EURJPY("frxEURJPY"), GBPAUD("frxGBPAUD"), USDCAD("frxUSDCAD"), GBPJPY("frxGBPJPY"), USDJPY("frxUSDJPY"),
    AUDJPY("frxAUDJPY"), USDCHF("frxUSDCHF");
    private String symbol;
    Currencies(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol(){ return symbol;}
}



