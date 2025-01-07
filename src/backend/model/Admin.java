package backend.model;

import backend.connectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin {
    private String idAdmin;
    private String userId;
    private String fullName;

    // Constructor
    public Admin(String idAdmin, String userId, String fullName) {
        this.idAdmin = idAdmin;
        this.userId = userId;
        this.fullName = fullName;
    }

    // Getters
    public String getIdAdmin() {
        return idAdmin;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }


    // Static methods for database operations
    public static Admin getAdminByUserId(String userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Admins WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getString("AdminID"),
                        rs.getString("UserID"),
                        rs.getString("FullName")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
