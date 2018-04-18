import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class DBwriter  {
    public DBwriter() throws Exception{

WebSocket webSocket=new WebSocket();
           Connection conn = null;

        conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres", "postgres");

        if (conn == null) {
            System.out.println("Нет соединения с БД!");
            System.exit(0);
        }
       System.out.println(conn.isClosed());

        String sql = "INSERT INTO prices (date, price) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, webSocket.getDate());
        stmt.setString(2, webSocket.getPrice());

// Выполнение запроса
        stmt.executeUpdate();

    }
}