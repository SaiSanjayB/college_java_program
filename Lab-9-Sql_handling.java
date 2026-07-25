import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection.java
 * Centralized JDBC connection utility.
 * Update the URL, USER, and PASSWORD to match your local MySQL setup.
 */
public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password_here";

    // Prevent instantiation
    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            // Not strictly required with modern JDBC 4+ drivers (auto-loaded via SPI),
            // but kept for clarity / compatibility with older setups.
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Add mysql-connector-j to your classpath.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
