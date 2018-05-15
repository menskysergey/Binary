

import json.Currencies;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//запрос крайней дневной свечи и получение данных по ней
public class CandleHistoryReaderJson {

    private Currencies currencies;
    private String quote;

    private float high;
    private float low;
    private float open;
    private float close;
    private String date;
    private boolean isSaturday=(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)==1);
    private int granularity=isSaturday?28800:86400;


    public String getDate() {
        return date;
    }

    public String getQuote() {
        return quote;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public float getOpen() {
        return open;
    }

    public float getClose() {
        return close;
    }

    public CandleHistoryReaderJson(Currencies currencies) {
        this.currencies = currencies;
        this.quote="{\n" +
                "  \"ticks_history\": \""+currencies.getSymbol()+"\",\n" +
                "  \"end\": \"latest\",\n" +
                "  \"start\": 1,\n" +
                "  \"style\": \"candles\",\n" +
                "  \"adjust_start_time\": 1,\n" +
                "\"granularity\": "+granularity+",\n" +
                "  \"count\": 3\n" +
                "}";
    }

    public void getHistory(String websock_mes) throws ParseException {
        JSONObject jsonObject =(JSONObject) new JSONParser().parse(websock_mes);
        if (isSaturday=true)
        {
JSONArray jsonArray=(JSONArray) jsonObject.get("candles");
            ArrayList<Float> h=new ArrayList<Float>();
            ArrayList <Float> l=new ArrayList<Float>();

            JSONObject json1 = (JSONObject) jsonArray.get(0);

            this.date = new Date(Long.valueOf(json1.get("epoch").toString()) * 1000).toString();

            h.add(Float.valueOf(json1.get("high").toString()));
            l.add(Float.valueOf(json1.get("low").toString()));

            this.open=Float.valueOf(json1.get("open").toString());

            JSONObject json2 = (JSONObject) jsonArray.get(2);

            this.close=Float.valueOf(json2.get("close").toString());

            h.add(Float.valueOf(json2.get("high").toString()));
            l.add(Float.valueOf(json2.get("low").toString()));

            JSONObject json3 = (JSONObject) jsonArray.get(1);

            h.add(Float.valueOf(json3.get("high").toString()));
            l.add(Float.valueOf(json3.get("low").toString()));

            this.high=Math.max(h.get(0), Math.max(h.get(1), h.get(2)));
            this.low=Math.min(l.get(0), Math.min(l.get(1), l.get(2)));
        }
        else {
            JSONObject json1 = (JSONObject) ((JSONArray) jsonObject.get("candles")).get(0);
            this.high = Float.valueOf(json1.get("high").toString());
            this.low = Float.valueOf(json1.get("low").toString());
            this.open = Float.valueOf(json1.get("open").toString());
            this.close = Float.valueOf(json1.get("close").toString());
            this.date = new Date(Long.valueOf(json1.get("epoch").toString()) * 1000).toString();
        }
    }

}
