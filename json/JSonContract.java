package json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Calendar;
import java.util.Date;

public class JSonContract {
   private String barrier;
   private Currencies currencies;
   private Type type;
   private String date;
   private String price;
   private int days;
   private boolean isFriday=(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)==6);

    public String getBarrier() {
        return barrier;
    }

    public Currencies getCurrencies() {
        return currencies;
    }

    public String getContract() {

        return contract;
    }

    private String contract;

    public JSonContract(String barrier, Currencies currencies, Type type) {
        this.days=isFriday?3:1;
        this.barrier = barrier;
        this.currencies = currencies;
        this.type=type;
        this.contract="{\n" +
                "  \"proposal\": 1,\n" +
                "  \"amount\": \"10\",\n" +
                "  \"basis\": \"payout\",\n" +
                "  \"contract_type\": \""+type+"\",\n" +
                "  \"currency\": \"USD\",\n" +
                "  \"duration\": \""+days+"\",\n" +
                "  \"duration_unit\": \"d\",\n" +
                "  \"barrier\": "+barrier+",\n"+
                "  \"symbol\": \""+currencies.getSymbol()+"\"\n" +
                "}";
                  }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public void contract_cal(String websock_mes) throws Exception{
               JSONObject obj =(JSONObject) new JSONParser().parse(websock_mes);
               JSONObject json=(JSONObject)obj.get("proposal");
               Thread.sleep(500);
               this.price=json.get("ask_price").toString();
               this.date=(new Date(Long.parseLong(json.get("spot_time").toString())*1000)).toString();
           }
}
