import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class DBlite  {
    public DBlite() throws Exception{

       WebSocket webSocket=new WebSocket();
     //   Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        Connection  conn = DriverManager.getConnection(
                "jdbc:sqlite:identifier.sqlite");
if (conn!=null){
    System.out.println("Соединение с БД успешно!");
}
        if (conn == null) {
            System.out.println("Нет соединения с БД!");
            System.exit(0);
        }
        String sql = "INSERT INTO main.pricess (date, price) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, webSocket.getDate());
   //     stmt.setString(2, webSocket.getPrice());

// Выполнение запроса
        stmt.executeUpdate();
        System.out.println("БД обновлена!");
        conn.close();
        System.out.println("Соединение с БД закрыто!");

    }
}