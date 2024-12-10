package fr.zsubhani;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestConfigurationBean {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Connection connection;

    @Test
    public void testConfigurationPropertiesLoaded() {
        // Check if the properties are correctly loaded
        assertNotNull(dataSource, "The DataSource bean should not be null");
        assertNotNull(connection, "The Connection bean should not be null");

        try {
            assertTrue(connection.isValid(2), "The connection should be valid");
        } catch (SQLException e) {
            fail("SQLException occurred while testing configuration: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidDatabaseConnection() {
        // Simulate invalid connection (You can mock this scenario in more advanced tests)
        try (Connection invalidConnection = dataSource.getConnection()) {
            invalidConnection.close();
            fail("Expected an exception for an invalid connection, but none was thrown.");
        } catch (SQLException e) {
            assertNotNull(e, "SQLException should be thrown for invalid configuration");
        }
    }
}
