package backend.model;

import backend.connectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin {
    private int idAdmin;
    private int userId;
    private String fullName;

    // Constructor
    public Admin(int idAdmin, int userId, String fullName) {
        this.idAdmin = idAdmin;
        this.userId = userId;
        this.fullName = fullName;
    }

    // Getters
    public int getIdAdmin() {
        return idAdmin;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }


    // Static methods for database operations
    public static Admin getAdminByUserId(int userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Admins WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getInt("AdminID"),
                        rs.getInt("UserID"),
                        rs.getString("FullName")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
