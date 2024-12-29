package backend.model;

import backend.connectDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Chutro {
    private String idChutro;
    private String userId;
    private String fullName;
    private String phone;
    private String cccd;

    // Constructor
    public Chutro(String idChutro, String userId, String fullName, String phone, String cccd) {
        this.idChutro = idChutro;
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.cccd = cccd;
    }

    // Getters
    public String getIdChutro() {
        return idChutro;
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

    public String getCccd() {
        return cccd;
    }

    // Static methods for database operations
    public static Chutro getChutroByUserId(String userId) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Chutro WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Chutro(
                        rs.getString("IDChutro"),
                        rs.getString("UserID"),
                        rs.getString("Hoten"),
                        rs.getString("Phone"),
                        rs.getString("CCCD")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateChutroInfo(String idChutro, String fullName, String phone, String cccd) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "UPDATE Chutro SET Hoten = ?, Phone = ?, CCCD = ? WHERE IDChutro = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fullName);
            pstmt.setString(2, phone);
            pstmt.setString(3, cccd);
            pstmt.setString(4, idChutro);

            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
