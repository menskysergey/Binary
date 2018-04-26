import json.Currencies;
import json.JSonContract;
import json.JSonQuote;
import json.Type;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;


public class WebSocket {
private CurWriter eurusd;
private CurWriter gbpusd;
private CurWriter audusd;
private CurWriter eurgbp;

    public CurWriter getAudusd() {
        return audusd;
    }

    public CurWriter getEurgbp() {
        return eurgbp;
    }

    public CurWriter getGbpusd() {
        return gbpusd;
    }

    public CurWriter getEurusd() {
        return eurusd;
    }

    public WebSocket() throws Exception{
        // open websocket
        final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("wss://ws.binaryws.com/websockets/v3?app_id=1089"));

        //получение данных по валюте
        this.eurusd=new CurWriter(clientEndPoint, Currencies.EURUSD);
        this.gbpusd=new CurWriter(clientEndPoint, Currencies.GBPUSD);
        this.audusd=new CurWriter(clientEndPoint, Currencies.AUDUSD);
        this.eurgbp=new CurWriter(clientEndPoint, Currencies.EURGBP);
        //закрытие сокета
        clientEndPoint.userSession.close();
      }

}