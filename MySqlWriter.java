import json.Currencies;
import websocket.WebsocketClientEndpoint;

import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;

//Писатель в базу конкретной валюты в ее базу
public class MySqlWriter {
    private WebsocketClientEndpoint clientEndPoint;
    private Connection conn;
    public MySqlWriter(Currencies currencies, WebsocketClientEndpoint clientEndPoint, Connection conn) throws Exception{

        //формируем запрос для получения данных по свече конкретной валюты
        CandleHistoryReaderJson candleHistoryReaderJson=new CandleHistoryReaderJson(currencies);
        //отправляем запрос
        clientEndPoint.sendMessage(candleHistoryReaderJson.getQuote());
Thread.sleep(1500);
//получаем ответ
String websocket_message=clientEndPoint.getMessage();

        //ответ в нужном формате
candleHistoryReaderJson.getHistory(websocket_message);
        System.out.println(candleHistoryReaderJson.getDate());
        System.out.println(candleHistoryReaderJson.getHigh());
        System.out.println(candleHistoryReaderJson.getLow());
        System.out.println(candleHistoryReaderJson.getOpen());
        System.out.println(candleHistoryReaderJson.getClose());



//определяем название БД конкретной валюты
        String table_name=currencies.toString().toLowerCase()+"_stat";
        //Формируем запрос на запись данных в БД
        String sql = "INSERT INTO "+table_name+" (date, high, low, open, close, volatility) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, candleHistoryReaderJson.getDate());
            stmt.setFloat(2, candleHistoryReaderJson.getHigh());
            stmt.setFloat(3, candleHistoryReaderJson.getLow());
            stmt.setFloat(4, candleHistoryReaderJson.getOpen());
            stmt.setFloat(5, candleHistoryReaderJson.getClose());
            stmt.setFloat(6, 0);

// Выполнение запроса
            stmt.executeUpdate();
            System.out.println("Таблица "+table_name+" обновлена!");

            // рассчитываем волу
            MySqlReader mySqlReader=new MySqlReader(currencies, 50, conn);
          //  "UPDATE `database`.`eurgbp_stat` SET `volatility`='0.9' order by id desc limit 1"
            sql="UPDATE "+table_name+" SET `volatility`="+mySqlReader.getVol()+" ORDER BY id DESC LIMIT 1";

            stmt=conn.prepareStatement(sql);
            stmt.executeUpdate();

            System.out.println("Медиана "+currencies.toString()+" составляет "+mySqlReader.getVol()+"!");


        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Таблица "+table_name+" НЕ обновлена!");}


    }

}
