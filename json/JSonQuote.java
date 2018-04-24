package json;

public class JSonQuote {
Currencies currencies;
String quote;
    public JSonQuote(Currencies currencies) {
        this.currencies = currencies;
        this.quote="{\n" +
                "  \"ticks_history\": "+currencies.getSymbol()+",\n" +
                "  \"end\": \"latest\",\n" +
                "  \"count\": 1\n" +
                "}";
    }
}
