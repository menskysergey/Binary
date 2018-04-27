package json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSonQuote {
private Currencies currencies;
private String quote;
    public JSonQuote(Currencies currencies) {
        this.currencies = currencies;
        this.quote="{\n" +
                "  \"ticks_history\": \""+currencies.getSymbol()+"\",\n" +
                "  \"end\": \"latest\",\n" +
                "  \"count\": 1\n" +
                "}";
    }

    public Currencies getCurrencies() {
        return currencies;
    }

    public String getQuote() {
        return quote;
    }
// расчет барьера
    public String bar_cal(String websock_mes) throws ParseException{JSONObject jsonObject =(JSONObject) new JSONParser().parse(websock_mes);
        JSONObject json1=(JSONObject)jsonObject.get("history");
        String value=json1.get("prices").toString().substring(2, json1.get("prices").toString().length()-2);
        return String.valueOf(Float.parseFloat(value)+(float) 0.00500);}
}
