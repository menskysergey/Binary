import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;


public class WebSocket {
    private String price;

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    private String date;
    public WebSocket() throws Exception{
        // open websocket
        final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("wss://ws.binaryws.com/websockets/v3?app_id=1089"));
        // send message to websocket


        //Запрос котировки
        clientEndPoint.sendMessage("{\n" +
                "  \"ticks_history\": \"frxEURUSD\",\n" +
                "  \"end\": \"latest\",\n" +
                "  \"count\": 1\n" +
                "}");
        Thread.sleep(1000);
        String a=clientEndPoint.getMessage();
      //  System.out.println(a);
        JSONObject jsonObject =(JSONObject) new JSONParser().parse(a);
        JSONObject json1=(JSONObject)jsonObject.get("history");
        String value=json1.get("prices").toString().substring(2, 9);
     //   System.out.println(value);
        String bar50=String.valueOf(Float.parseFloat(value)+(float) 0.00500);
      //  System.out.println(bar50);


        // Запрос контракта
        clientEndPoint.sendMessage("{\n" +
                "  \"proposal\": 1,\n" +
                "  \"amount\": \"100\",\n" +
                "  \"basis\": \"payout\",\n" +
                "  \"contract_type\": \"NOTOUCH\",\n" +
                "  \"currency\": \"USD\",\n" +
                "  \"duration\": \"1\",\n" +
                "  \"duration_unit\": \"d\",\n" +
                "  \"barrier\": "+bar50+",\n"+
                "  \"symbol\": \"frxEURUSD\"\n" +
                "}");

        // wait 1 seconds for messages from websocket
        Thread.sleep(1000);
        String s=clientEndPoint.getMessage();

        JSONObject obj =(JSONObject) new JSONParser().parse(s);
        JSONObject json=(JSONObject)obj.get("proposal");
      //  System.out.println(obj);

        this.price=json.get("ask_price").toString();

    //    System.out.println(price);

        //Запрос даты
        this.date=(new Date(Long.parseLong(json.get("spot_time").toString())*1000)).toString();

        clientEndPoint.userSession.close();

     // System.out.println(date);



        //   Thread.sleep(30000);
    }

}