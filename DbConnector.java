import java.sql.Connection;
import java.sql.DriverManager;

// подключение к базе данных mysql
public class DbConnector {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DbConnector() throws Exception{
       Class.forName("com.mysql.jdbc.Driver");
    this.connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/database",
            "root", "root");

        if (connection!=null){
        System.out.println("Соединение с БД успешно!");
    }

        if (connection == null) {
        System.out.println("Нет соединения с БД!");
        System.exit(0);
    }
}}
