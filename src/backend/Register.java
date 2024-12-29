package backend;

import backend.connectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Register {
    public static boolean registerUser(String role, String username, String password, String confirmPassword) {
        if (username == null || username.isEmpty()) {
            System.out.println("Tên tài khoản không được để trống.");
            return false;
        }
        if (password == null || password.isEmpty()) {
            System.out.println("Mật khẩu không được để trống.");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            System.out.println("Mật khẩu xác nhận không khớp.");
            return false;
        }

        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            // Kiểm tra tài khoản đã tồn tại
            String checkUserSql = "SELECT COUNT(*) FROM Users WHERE Username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkUserSql);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Tài khoản đã tồn tại.");
                return false;
            }

            // Thêm tài khoản mới
            String insertSql = "INSERT INTO Users (Username, Password, Role) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, username);
            insertStmt.setString(2, password); // Cần hash mật khẩu ở đây
            insertStmt.setString(3, role);

            int rowsAffected = insertStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đăng ký thành công!");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
