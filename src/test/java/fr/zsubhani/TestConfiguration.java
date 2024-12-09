package fr.zsubhani;

import fr.zsubhani.quizService.Configuration;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestConfiguration {

    @Test
    public void testConfigurationPropertiesLoaded() {
        // Check if the properties are correctly loaded
        try {
            Connection connection = Configuration.getConnection();
            assertNotNull(connection, "The connection should not be null");
            assertTrue(connection.isValid(2), "The connection should be valid");
            connection.close();
        } catch (SQLException e) {
            fail("SQLException occurred while testing configuration: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidDatabaseConnection() {
        // Override properties for invalid connection scenario
        Configuration.setProperty("jdbc.JDBC_URL", "invalid_url");
        Configuration.setProperty("jdbc.User", "invalid_user");
        Configuration.setProperty("jdbc.PASSWORD", "invalid_password");

        SQLException exception = assertThrows(SQLException.class, () -> {
            Configuration.getConnection();
        });

        assertNotNull(exception, "SQLException should be thrown for invalid configuration");
    }
}
