package AppDev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import AppDev.AdminForm.DatabaseUtil;

public class MessageUtil {

    public static void addMessageToDatabase(String username, String message) {
        String insertSQL = "INSERT INTO msgs (username, message) VALUES (?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, username);
            pstmt.setString(2, message);

            pstmt.executeUpdate();
            System.out.println("Message added to the database successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
