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


    private String date;

    private String touch;

    private String notouch;

    public String getDate() {
        return date;
    }
    public String getTouch() {
        return touch;
    }
        public String getNotouch() {
        return notouch;
    }


    public WebSocket() throws Exception{
        // open websocket
        final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("wss://ws.binaryws.com/websockets/v3?app_id=1089"));
        // send message to websocket
        //формирование предварительного json запроса
JSonQuote jSonQuote=new JSonQuote(Currencies.EURUSD);
        //Запрос котировки
        clientEndPoint.sendMessage(jSonQuote.getQuote());
        Thread.sleep(1000);

            //расчет барьера
        String barrier=jSonQuote.bar_cal(clientEndPoint.getMessage());

        //формирование контрактного запроса
        JSonContract jSonContract=new JSonContract(barrier, Currencies.EURUSD, Type.ONETOUCH);
        //запрос контракта
        clientEndPoint.sendMessage(jSonContract.getContract());

        // wait 1 seconds for messages from websocket
        Thread.sleep(1000);

//обработка json под расчет цены и даты
jSonContract.contract_cal(clientEndPoint.getMessage());

//Получение даты
        this.date=jSonContract.getDate();

        //Получение цены
        this.touch=jSonContract.getPrice();


//дублируем противоположный контракт
        //формирование контрактного запроса
        JSonContract jSonContract1=new JSonContract(barrier, Currencies.EURUSD, Type.NOTOUCH);
        //запрос контракта
        clientEndPoint.sendMessage(jSonContract1.getContract());

        // wait 1 seconds for messages from websocket
        Thread.sleep(1000);

//обработка json под расчет цены и даты
        jSonContract1.contract_cal(clientEndPoint.getMessage());

       this.notouch=jSonContract1.getPrice();

        //закрытие сокета
        clientEndPoint.userSession.close();
        }

}