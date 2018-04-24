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
       // System.out.println(conn.isClosed());

        String sql = "INSERT INTO eurusd (date, touch, notouch) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, webSocket.getDate());
        stmt.setString(2, webSocket.getTouch());
        stmt.setString(3, webSocket.getNotouch());

// Выполнение запроса
        stmt.executeUpdate();

        System.out.println("БД обновлена!");
        conn.close();
        System.out.println("Соединение с БД закрыто!");
    }
}
