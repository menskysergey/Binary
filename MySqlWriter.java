import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MySqlWriter {
    public MySqlWriter() throws Exception{
        WebSocket webSocket=new WebSocket();
           Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/database",
                "root", "root");

        if (conn!=null){
            System.out.println("Соединение с БД успешно!");
        }

        if (conn == null) {
            System.out.println("Нет соединения с БД!");
            System.exit(0);
        }



        String sql = "INSERT INTO eurusd (date, touch, notouch) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, webSocket.getEurusd().getDate());
        stmt.setString(2, webSocket.getEurusd().getTouch());
        stmt.setString(3, webSocket.getEurusd().getNotouch());

// Выполнение запроса
        stmt.executeUpdate();
        System.out.println("Таблица EURUSD обновлена!");


        sql = "INSERT INTO gbpusd (date, touch, notouch) VALUES (?, ?, ?)";
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, webSocket.getGbpusd().getDate());
        stmt.setString(2, webSocket.getGbpusd().getTouch());
        stmt.setString(3, webSocket.getGbpusd().getNotouch());

// Выполнение запроса
        stmt.executeUpdate();
        System.out.println("Таблица GBPUSD обновлена!");




        sql = "INSERT INTO audusd (date, touch, notouch) VALUES (?, ?, ?)";
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, webSocket.getAudusd().getDate());
        stmt.setString(2, webSocket.getAudusd().getTouch());
        stmt.setString(3, webSocket.getAudusd().getNotouch());

// Выполнение запроса
        stmt.executeUpdate();
        System.out.println("Таблица AUDUSD обновлена!");



        sql = "INSERT INTO eurgbp (date, touch, notouch) VALUES (?, ?, ?)";
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, webSocket.getEurgbp().getDate());
        stmt.setString(2, webSocket.getEurgbp().getTouch());
        stmt.setString(3, webSocket.getEurgbp().getNotouch());

// Выполнение запроса
        stmt.executeUpdate();
        System.out.println("Таблица EURGBP обновлена!");



        conn.close();
        System.out.println("Соединение с БД закрыто!");
    }
}
