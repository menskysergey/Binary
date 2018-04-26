import json.Currencies;
import json.JSonContract;
import json.JSonQuote;
import json.Type;

public class CurWriter {


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

    public CurWriter(WebsocketClientEndpoint clientEndPoint, Currencies currencies) throws Exception{
     //   this.clientEndPoint = clientEndPoint;

        // send message to websocket
        //формирование предварительного json запроса

    JSonQuote jSonQuote=new JSonQuote(currencies);
    //Запрос котировки
        clientEndPoint.sendMessage(jSonQuote.getQuote());
        Thread.sleep(1500);

    //расчет барьера
    String barrier=jSonQuote.bar_cal(clientEndPoint.getMessage());

    //формирование контрактного запроса
    JSonContract jSonContract=new JSonContract(barrier, currencies, Type.ONETOUCH);
    //запрос контракта
        clientEndPoint.sendMessage(jSonContract.getContract());

    // wait 1 seconds for messages from websocket
        Thread.sleep(1500);

//обработка json под расчет цены и даты
jSonContract.contract_cal(clientEndPoint.getMessage());

//Получение даты
        this.date=jSonContract.getDate();

    //Получение цены
        this.touch=jSonContract.getPrice();


    //дублируем противоположный контракт
    //формирование контрактного запроса
    JSonContract jSonContract1=new JSonContract(barrier, currencies, Type.NOTOUCH);
    //запрос контракта
        clientEndPoint.sendMessage(jSonContract1.getContract());

    // wait 1 seconds for messages from websocket
        Thread.sleep(1500);

//обработка json под расчет цены и даты
        jSonContract1.contract_cal(clientEndPoint.getMessage());

       this.notouch=jSonContract1.getPrice();

}}
