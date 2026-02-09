package zona_fit.connection;

import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionJdbc {
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = ConnectionJdbc.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {
            properties.load(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("Error loading application.properties", e);
        }
    }

    public static java.sql.Connection getConnection() {
        java.sql.Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = properties.getProperty("db.url");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");

            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error al conectar la base de datos" + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        var connection = ConnectionJdbc.getConnection();
        if (connection != null) {
            System.out.println("conexion ok: \n" + connection);
        } else {
            System.out.println("Error conexion");
        }
    }
}