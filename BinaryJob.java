import json.Currencies;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import websocket.WebsocketClientEndpoint;

import java.net.URI;
import java.sql.Connection;


public class BinaryJob implements Job{
    public void execute(JobExecutionContext jobExecutionContext) {
        WebsocketClientEndpoint clientEndPoint=null;
        Connection conn=null;
      try {
          clientEndPoint = new WebsocketClientEndpoint(new URI("wss://ws.binaryws.com/websockets/v3?app_id=1089"));
      }catch (Exception e){e.printStackTrace();}

        try {
          conn=new DbConnector().getConnection();
        }catch (Exception e){e.printStackTrace();}



        try {
          new MySqlWriter(Currencies.EURUSD, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            new MySqlWriter(Currencies.GBPUSD, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            new MySqlWriter(Currencies.AUDUSD, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new MySqlWriter(Currencies.EURGBP, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new MySqlWriter(Currencies.EURCHF, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new MySqlWriter(Currencies.EURAUD, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new MySqlWriter(Currencies.EURCAD, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new MySqlWriter(Currencies.GBPAUD, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            new MySqlWriter(Currencies.USDCAD, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            new MySqlWriter(Currencies.GBPJPY, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            new MySqlWriter(Currencies.USDJPY, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            new MySqlWriter(Currencies.AUDJPY, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            new MySqlWriter(Currencies.EURJPY, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new MySqlWriter(Currencies.USDCHF, clientEndPoint, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }



try {
       conn.close();
        System.out.println("Соединение с БД закрыто!");
        clientEndPoint.userSession.close();}
        catch (Exception e){e.printStackTrace();
      }
    }
}
