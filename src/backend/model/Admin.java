package backend.model;

import backend.connectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin {
    private String idAdmin;
    private String userId;
    private String fullName;
    private String phone;

    // Constructor
    public Admin(String idAdmin, String userId, String fullName, String phone) {
        this.idAdmin = idAdmin;
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    // Static methods for database operations
    public static Admin getAdminByUserId(String userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Admin WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getString("IDAdmin"),
                        rs.getString("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Phone")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
