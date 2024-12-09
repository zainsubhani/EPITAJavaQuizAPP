package fr.zsubhani.quizService;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Configuration {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }

    // Utility method to override properties for testing
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    // Utility method to acquire a database connection
    public static Connection getConnection() throws SQLException {
        String jdbcUrl = properties.getProperty("jdbc.JDBC_URL");
        String user = properties.getProperty("jdbc.User");
        String password = properties.getProperty("jdbc.PASSWORD");

        return DriverManager.getConnection(jdbcUrl, user, password);
    }
}
