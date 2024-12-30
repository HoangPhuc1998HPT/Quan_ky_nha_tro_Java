package backend;

import backend.connectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Register {
    public static boolean registerUser(String role, String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return false;
        }
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Users (Username, Password, Role) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Hash mật khẩu nếu cần
            pstmt.setString(3, role);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
