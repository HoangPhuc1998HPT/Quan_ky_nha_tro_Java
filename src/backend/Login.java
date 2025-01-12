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
                int userId = rs.getInt("UserID");
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

    public static boolean usernameCheck(String username) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Username FROM Users WHERE Username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean passwordCheck(String password) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT Password FROM Users WHERE Password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean isActive(String username, String password) {
        try (Connection conn = connectDatabase.DatabaseConnection.getConnection()) {
            String sql = "SELECT UserID, Role FROM Users WHERE Username = ? AND Password = ? AND is_active = 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Mã hóa nếu cần thiết
            ResultSet rs = pstmt.executeQuery();
            System.out.println(rs);

            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    public static class LoginResult {
        private final int userId;
        private final String role;

        public LoginResult(int userId, String role) {
            this.userId = userId;
            this.role = role;
        }

        public int getUserId() {
            return userId;
        }

        public String getRole() {
            return role;
        }
    }
}
