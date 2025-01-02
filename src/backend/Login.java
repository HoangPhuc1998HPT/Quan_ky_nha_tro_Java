package backend;

import backend.connectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    public static LoginResult checkLogin(String username, String password) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT UserID, Role FROM Users WHERE Username = ? AND Password = ? AND is_active = 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Mã hóa nếu cần thiết
            ResultSet rs = pstmt.executeQuery();
            System.out.println(rs);

            if (rs.next()) {
                String userId = rs.getString("UserID");
                String role = rs.getString("Role");
                System.out.println(userId);
                System.out.println(role);
                return new LoginResult(userId, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Đăng nhập thất bại
    }

    public static class LoginResult {
        private final String userId;
        private final String role;

        public LoginResult(String userId, String role) {
            this.userId = userId;
            this.role = role;
        }

        public String getUserId() {
            return userId;
        }

        public String getRole() {
            return role;
        }
    }
}
