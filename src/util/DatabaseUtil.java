package util;
import java.sql.*;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:sqlite:conversion.db";

    static {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS conversion_history (" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "binary_input TEXT NOT NULL, " +
                                    "decimal_output INTEGER NOT NULL, " +
                                    "conversion_time DATETIME DEFAULT CURRENT_TIMESTAMP)";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createTableSQL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveConversion(String binaryInput, int decimalOutput) {
        String insertSQL = "INSERT INTO conversion_history (binary_input, decimal_output) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {

            pstmt.setString(1, binaryInput);
            pstmt.setInt(2, decimalOutput);
            pstmt.executeUpdate();
            System.out.println("Conversion saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}